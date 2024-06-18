package com.example.user_location_client;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.user_location_client.entity.VehicleLocation;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    //Config for String Data
    @Bean
    public ConsumerFactory<? super String, ? super VehicleLocation> consumerFactory() {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "location_group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // Returning message
        return new DefaultKafkaConsumerFactory(config, new StringDeserializer(), new JsonDeserializer<>(VehicleLocation.class));
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VehicleLocation> vehicleListener() {
    	ConcurrentKafkaListenerContainerFactory<String, VehicleLocation> factory = new ConcurrentKafkaListenerContainerFactory<>();
    	factory.setConsumerFactory(consumerFactory());
    	return factory;
    }
}