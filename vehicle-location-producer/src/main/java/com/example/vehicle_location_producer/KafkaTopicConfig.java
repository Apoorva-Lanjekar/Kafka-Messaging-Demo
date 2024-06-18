package com.example.vehicle_location_producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
    public NewTopic saveVehicletopic() {
        return TopicBuilder
                .name("vehicle-location")
                .build();
    }
	
	@Bean
    public NewTopic getVehicleByIdTopic() {
        return TopicBuilder
                .name("vehicle-locatio-id")
                .build();
    }
	
	@Bean
    public NewTopic getAllVehiclesTopic() {
        return TopicBuilder
                .name("vehicle-location-all")
                .build();
    }
}