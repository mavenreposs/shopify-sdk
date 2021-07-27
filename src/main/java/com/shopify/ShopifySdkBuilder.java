package com.shopify;

import java.util.concurrent.TimeUnit;

public class ShopifySdkBuilder {

    public static SubdomainStep newBuilder() {
        return new Steps();
    }

    public static interface SubdomainStep {
        AccessTokenStep withSubdomain(final String subdomain);

        AccessTokenStep withApiUrl(final String apiUrl);
    }

    public static interface OptionalsStep {

        /**
         * The Shopify SDK uses random waits in between retry attempts. Minimum duration
         * time to wait before retrying a failed request. Value must also be less than
         * {@link #withMaximumRequestRetryRandomDelay(int, TimeUnit) Maximum Request
         * Retry Random Delay}.<br>
         * Default value is: 1 second.
         *
         * @param duration int
         * @param timeUnit TimeUnit
         * @return {@link OptionalsStep}
         */
        OptionalsStep withMinimumRequestRetryRandomDelay(int duration, TimeUnit timeUnit);

        /**
         * The Shopify SDK uses random waits in between retry attempts. Maximum duration
         * time to wait before retrying a failed request. Value must also be more than
         * {@link #withMinimumRequestRetryRandomDelay(int, TimeUnit) Minimum Request
         * Retry Random Delay}.<br>
         * Default value is: 5 seconds.
         *
         * @param duration int
         * @param timeUnit TimeUnit
         * @return {@link OptionalsStep}
         */
        OptionalsStep withMaximumRequestRetryRandomDelay(int duration, TimeUnit timeUnit);

        /**
         * Maximum duration time to keep attempting requests <br>
         * Default value is: 3 minutes.
         *
         * @param duration int
         * @param timeUnit TimeUnit
         * @return {@link OptionalsStep}
         */
        OptionalsStep withMaximumRequestRetryTimeout(int duration, TimeUnit timeUnit);

        /**
         * The duration to wait when connecting to Shopify's API. <br>
         * Default value is: 1 minute.
         *
         * @param duration int
         * @param timeUnit TimeUnit
         * @return {@link OptionalsStep}
         */
        OptionalsStep withConnectionTimeout(int duration, TimeUnit timeUnit);

        /**
         * The duration to attempt to read a response from Shopify's API. <br>
         * Default value is: 15 seconds.
         *
         * @param duration int
         * @param timeUnit TimeUnit
         * @return {@link OptionalsStep}
         */
        OptionalsStep withReadTimeout(int duration, TimeUnit timeUnit);

        /**
         * String representation of the version you want to use. If not populated, this
         * will use shopify oldest stable version. Although this is not recommended so
         * you can target a set of shopify features. Ex: '2020-10' '2020-07' '2020-04'.
         * If you are specifying the API URL ensure you leave off the version if you are
         * using this.
         *
         * @param apiVersion String
         * @return OptionalsStep
         */
        OptionalsStep withApiVersion(final String apiVersion);

        ShopifySdk build();

    }

    public static interface ClientSecretStep {
        AuthorizationTokenStep withClientSecret(final String clientSecret);

    }

    public static interface AuthorizationTokenStep {
        OptionalsStep withAuthorizationToken(final String authorizationToken);

    }

    public static interface AccessTokenStep {
        OptionalsStep withAccessToken(final String accessToken);

        ClientSecretStep withClientId(final String clientId);
    }

    static class Steps implements SubdomainStep, ClientSecretStep, AuthorizationTokenStep, AccessTokenStep, OptionalsStep {

        private String subdomain;
        private String accessToken;
        private String clientId;
        private String clientSecret;
        private String authorizationToken;
        private String apiUrl;
        private String apiVersion;

        private long minimumRequestRetryRandomDelayMilliseconds = ShopifySdk.DEFAULT_MINIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS;
        private long maximumRequestRetryRandomDelayMilliseconds = ShopifySdk.DEFAULT_MAXIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS;
        private long maximumRequestRetryTimeoutMilliseconds = ShopifySdk.DEFAULT_MAXIMUM_REQUEST_RETRY_TIMEOUT_IN_MILLISECONDS;
        private long connectionTimeoutMilliseconds = ShopifySdk.DEFAULT_CONNECTION_TIMEOUT_IN_MILLISECONDS;
        private long readTimeoutMilliseconds = ShopifySdk.DEFAULT_READ_TIMEOUT_IN_MILLISECONDS;

        @Override
        public ShopifySdk build() {
            return new ShopifySdk(this);
        }

        @Override
        public OptionalsStep withAccessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        @Override
        public AccessTokenStep withSubdomain(final String subdomain) {
            this.subdomain = subdomain;
            return this;
        }

        @Override
        public AccessTokenStep withApiUrl(final String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        @Override
        public ClientSecretStep withClientId(final String clientId) {
            this.clientId = clientId;
            return this;
        }

        @Override
        public OptionalsStep withAuthorizationToken(final String authorizationToken) {
            this.authorizationToken = authorizationToken;
            return this;
        }

        @Override
        public AuthorizationTokenStep withClientSecret(final String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        @Override
        public OptionalsStep withMinimumRequestRetryRandomDelay(final int duration, final TimeUnit timeUnit) {
            this.minimumRequestRetryRandomDelayMilliseconds = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public OptionalsStep withMaximumRequestRetryRandomDelay(final int duration, final TimeUnit timeUnit) {
            this.maximumRequestRetryRandomDelayMilliseconds = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public OptionalsStep withMaximumRequestRetryTimeout(final int duration, final TimeUnit timeUnit) {
            this.maximumRequestRetryTimeoutMilliseconds = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public OptionalsStep withConnectionTimeout(final int duration, final TimeUnit timeUnit) {
            this.connectionTimeoutMilliseconds = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public OptionalsStep withReadTimeout(final int duration, final TimeUnit timeUnit) {
            this.readTimeoutMilliseconds = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public OptionalsStep withApiVersion(final String apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public String getSubdomain() {
            return subdomain;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public String getAuthorizationToken() {
            return authorizationToken;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public long getMinimumRequestRetryRandomDelayMilliseconds() {
            return minimumRequestRetryRandomDelayMilliseconds;
        }

        public long getMaximumRequestRetryRandomDelayMilliseconds() {
            return maximumRequestRetryRandomDelayMilliseconds;
        }

        public long getMaximumRequestRetryTimeoutMilliseconds() {
            return maximumRequestRetryTimeoutMilliseconds;
        }

        public long getConnectionTimeoutMilliseconds() {
            return connectionTimeoutMilliseconds;
        }

        public long getReadTimeoutMilliseconds() {
            return readTimeoutMilliseconds;
        }
    }
}
