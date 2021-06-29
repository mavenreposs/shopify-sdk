package com.shopify;

import com.github.rholder.retry.*;
import com.shopify.mappers.ResponseEntityToStringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

public class ShopifyResponseRetyer {

    static final String RETRY_AFTER_HEADER = "Retry-After";

    private static final String COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE = "could not successfully be saved";

    private static final int DEFAULT_REQUEST_LIMIT = 50;
    private static final int TOO_MANY_REQUESTS_STATUS_CODE = 429;
    private static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;
    private static final int LOCKED_STATUS_CODE = 423;

    private long minimumRequestRetryRandomDelayMilliseconds;
    private long maximumRequestRetryRandomDelayMilliseconds;
    private long maximumRequestRetryTimeoutMilliseconds;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopifyResponseRetyer.class);


    public Retryer<Response> buildResponseRetyer() {
        return RetryerBuilder.<Response>newBuilder().retryIfResult(ShopifyResponseRetyer::shouldRetryResponse).retryIfException()
                .withWaitStrategy(WaitStrategies.randomWait(minimumRequestRetryRandomDelayMilliseconds,
                        TimeUnit.MILLISECONDS, maximumRequestRetryRandomDelayMilliseconds, TimeUnit.MILLISECONDS))
                .withStopStrategy(
                        StopStrategies.stopAfterDelay(maximumRequestRetryTimeoutMilliseconds, TimeUnit.MILLISECONDS))
                .withRetryListener(new ShopifyResponseRetyer.ShopifySdkRetryListener()).build();
    }


    private static boolean shouldRetryResponse(final Response response) {
        return isServerError(response) || hasExceededRateLimit(response) || hasNotBeenSaved(response);
    }

    private static boolean hasExceededRateLimit(final Response response) {
        return TOO_MANY_REQUESTS_STATUS_CODE == response.getStatus()
                && response.getHeaders().containsKey(RETRY_AFTER_HEADER);
    }

    private static boolean isServerError(final Response response) {
        return (Response.Status.Family.SERVER_ERROR == Response.Status.Family.familyOf(response.getStatus()))
                || (LOCKED_STATUS_CODE == response.getStatus());
    }

    private static boolean hasNotBeenSaved(final Response response) {
        if ((UNPROCESSABLE_ENTITY_STATUS_CODE == response.getStatus()) && response.hasEntity()) {
            final String shopifyErrorResponse = ResponseEntityToStringMapper.map(response);
            LOGGER.debug(shopifyErrorResponse);
            return shopifyErrorResponse.contains(COULD_NOT_BE_SAVED_SHOPIFY_ERROR_MESSAGE);
        }
        return false;
    }

    public class ShopifySdkRetryListener implements RetryListener {

        private static final String RETRY_EXCEPTION_ATTEMPT_MESSAGE = "An exception occurred while making an API call to shopify: {} on attempt number {} and {} seconds since first attempt";
        private static final String RETRY_INVALID_RESPONSE_ATTEMPT_MESSAGE = "Waited {} seconds since first retry attempt. This is attempt {}. Please review the following failed request information.\nRequest Location of {}\nResponse Status Code of {}\nResponse Headers of:\n{}\nResponse Body of:\n{}";

        @Override
        public <V> void onRetry(final Attempt<V> attempt) {
            if (attempt.hasResult()) {
                final Response response = (Response) attempt.getResult();

                final String responseBody = ResponseEntityToStringMapper.map(response);

                if (LOGGER.isWarnEnabled() && !hasExceededRateLimit(response) && shouldRetryResponse(response)) {

                    final long delaySinceFirstAttemptInSeconds = convertMillisecondsToSeconds(
                            attempt.getDelaySinceFirstAttempt());
                    LOGGER.warn(RETRY_INVALID_RESPONSE_ATTEMPT_MESSAGE, delaySinceFirstAttemptInSeconds,
                            attempt.getAttemptNumber(), response.getLocation(), response.getStatus(),
                            response.getStringHeaders(), responseBody);

                }

            } else if (LOGGER.isWarnEnabled() && attempt.hasException()) {

                final long delaySinceFirstAttemptInSeconds = convertMillisecondsToSeconds(
                        attempt.getDelaySinceFirstAttempt());
                LOGGER.warn(RETRY_EXCEPTION_ATTEMPT_MESSAGE, attempt.getAttemptNumber(),
                        delaySinceFirstAttemptInSeconds, attempt.getExceptionCause());
            }
        }

        private long convertMillisecondsToSeconds(final long milliiseconds) {
            return TimeUnit.SECONDS.convert(milliiseconds, TimeUnit.MILLISECONDS);
        }

    }

}
