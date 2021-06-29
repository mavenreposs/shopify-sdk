package com.shopify;

public interface SubdomainStep {
    AccessTokenStep withSubdomain(final String subdomain);

    AccessTokenStep withApiUrl(final String apiUrl);
}
