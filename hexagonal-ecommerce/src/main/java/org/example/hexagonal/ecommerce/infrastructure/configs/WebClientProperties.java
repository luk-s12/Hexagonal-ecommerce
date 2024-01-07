package org.example.hexagonal.ecommerce.infrastructure.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "webclient")
public class WebClientProperties {
	 private String baseUrl;	
}
