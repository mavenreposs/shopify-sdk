package com.shopify;

import java.util.concurrent.TimeUnit;

public interface OptionalsStep {

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
