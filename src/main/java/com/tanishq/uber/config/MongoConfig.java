package com.tanishq.uber.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

  @Bean
  public MongoClient mongoClient() {
    ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://jaintanishq4141:kedBu0-kaggog-bexzow@cluster0.mdystwa.mongodb.net/?appName=Cluster0");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build();
    return MongoClients.create(settings);
  }
}
