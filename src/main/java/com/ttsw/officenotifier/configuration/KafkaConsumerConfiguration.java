package com.ttsw.officenotifier.configuration;

import com.ttsw.officenotifier.api.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Value("${office-core.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<Integer, Event> consumerFactory() {
        Map<String, Object> conumerProperties = new HashMap<>();

        conumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        conumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        conumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        conumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.ttsw.officenotifier.api");
        conumerProperties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");

        return new DefaultKafkaConsumerFactory<>(conumerProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, Event> concurrentKafkaListenerContainerFactory(){
        var concurrentKafkaListenerContainerFactory =  new ConcurrentKafkaListenerContainerFactory<Integer, Event>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }
}
