package org.example.hexagonal.ecommerce.infrastructure.adapters.services;

import java.util.Collections;
import java.util.List;


import org.example.hexagonal.ecommerce.application.services.MockServerClientService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class MockServerClientImpl implements MockServerClientService {

    private final WebClient webClient;

    public MockServerClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Integer> getSimilarProductIds(String productId) {
        return webClient.get()
                .uri("/product/{productId}/similarids", productId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Integer>>() {})
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode().is4xxClientError()) {
                        return Mono.just(Collections.emptyList());
                    } else {
                        return Mono.error(new RuntimeException("Error retrieving the data", ex));
                    }
                })
                .block();

    }

}
