package com.bibro.flight_stats_service.app.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        ConnectionString connString = new ConnectionString("mongodb://127.0.0.1:27017");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        return new ReactiveMongoTemplate(MongoClients.create(settings), "db");
    }
}
