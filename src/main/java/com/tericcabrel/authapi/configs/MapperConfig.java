package com.tericcabrel.authapi.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
