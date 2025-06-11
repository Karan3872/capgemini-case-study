//package com.carwash.payment_service.config;
//
//import com.paypal.base.rest.APIContext;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class PayPalConfig {
//
//    @Value("${paypal.client.id}")
//    private String clientId;
//
//    @Value("${paypal.client.secret}")
//    private String clientSecret;
//
//    @Value("${paypal.mode}")
//    private String mode;
//
//    // Create a configuration map with PayPal environment mode
//    @Bean
//    public Map<String, String> payPalSDKConfig() {
//        Map<String, String> config = new HashMap<>();
//        config.put("mode", mode); // "sandbox" or "live"
//        return config;
//    }
//
//    // Create APIContext using recommended constructor (clientId, clientSecret, mode)
//    @Bean
//    public APIContext apiContext() {
//        APIContext context = new APIContext(clientId, clientSecret, mode);
//        context.setConfigurationMap(payPalSDKConfig());
//        return context;
//    }
//}
