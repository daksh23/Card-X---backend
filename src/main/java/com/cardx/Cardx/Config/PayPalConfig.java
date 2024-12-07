package com.cardx.Cardx.Config;

import com.paypal.base.rest.APIContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {
    private final String clientId = "AfXrsMWAqkukWC71wy7_OuUCA84joOhxc2tlfTK8fb5sq6IptI1in35n1lPZ2ZMh1LFWzIDRgYVjRyQ2";
    private final String clientSecret = "EJ7BEeXX5EjmLQMnQoiCH-u-PplXxrhXhmfoQDEBvLFruPzQWoMe56cJpj614Hh1PJrFYYbM73_I6RH6";
    private final String mode = "sandbox"; // or "live" for production

    @Bean
    public APIContext apiContext() {
        return new APIContext(clientId, clientSecret, mode);
    }
}
