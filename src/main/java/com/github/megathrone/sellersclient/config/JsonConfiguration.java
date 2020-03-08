package com.github.megathrone.sellersclient.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import processor.SellersProcessor;

@Component
public class JsonConfiguration {

    @Bean
    public SellersProcessor getProcessor() {
        return new SellersProcessor();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }
}
