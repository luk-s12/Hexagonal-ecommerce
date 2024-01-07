package org.example.hexagonal.ecommerce.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
	private final WebClientProperties webClientProperties;

    public WebClientConfig(WebClientProperties webClientProperties) {
        this.webClientProperties = webClientProperties;
    }
    
    @Bean
    public WebClient webclient() {
        return WebClient.builder()
                .baseUrl( webClientProperties.getBaseUrl() )
                .build();
    }

}
