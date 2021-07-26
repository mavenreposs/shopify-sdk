package com.shopify;

import java.util.concurrent.TimeUnit;

class Steps implements SubdomainStep, ClientSecretStep, AuthorizationTokenStep, AccessTokenStep, OptionalsStep {

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
