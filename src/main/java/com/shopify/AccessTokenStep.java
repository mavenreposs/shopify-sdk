package com.shopify;

public interface AccessTokenStep {
    OptionalsStep withAccessToken(final String accessToken);

    ClientSecretStep withClientId(final String clientId);
}
