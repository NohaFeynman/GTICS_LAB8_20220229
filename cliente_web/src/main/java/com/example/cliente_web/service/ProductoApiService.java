package com.example.cliente_web.service;

import com.example.cliente_web.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductoApiService {

    private final WebClient webClient;

    public ProductoApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Product> listar() {
        Product[] arr = webClient.get()
                .uri("/product")
                .retrieve()
                .bodyToMono(Product[].class)
                .onErrorReturn(new Product[0])
                .block();
        return Arrays.asList(arr == null ? new Product[0] : arr);
    }

    public Product obtenerPorId(Integer id) {
        return webClient.get()
                .uri("/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> Mono.empty())
                .block();
    }
}
