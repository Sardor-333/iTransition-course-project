package com.example.itransitioncourseproject.configs.search;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SearchConfig {

    private final Indexer indexer;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> indexer.indexSchema();
    }
}
