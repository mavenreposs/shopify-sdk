package com.shopify;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.shopify.mappers.ResponseEntityToStringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

public class ShopifySdkRetryListener implements RetryListener {

    private static final String RETRY_EXCEPTION_ATTEMPT_MESSAGE = "An exception occurred while making an API call to shopify: {} on attempt number {} and {} seconds since first attempt";
    private static final String RETRY_INVALID_RESPONSE_ATTEMPT_MESSAGE = "Waited {} seconds since first retry attempt. This is attempt {}. Please review the following failed request information.\nRequest Location of {}\nResponse Status Code of {}\nResponse Headers of:\n{}\nResponse Body of:\n{}";

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopifySdk.class);


    @Override
    public <V> void onRetry(final Attempt<V> attempt) {
        if (attempt.hasResult()) {
            final Response response = (Response) attempt.getResult();

            final String responseBody = ResponseEntityToStringMapper.map(response);

            if (LOGGER.isWarnEnabled() && !ShopifyWebTarget.hasExceededRateLimit(response) && ShopifyWebTarget.shouldRetryResponse(response)) {

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
