package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    //Crear el topic de envio.
    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // Delete (Borra el mensaje), compact (Mantiene el mensaje mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //Tiempo de retencion de los mensajes dentro del topic - defecto -1 (no se borra nunca)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); //Tamaño maximo de cada segmento dentro del Topic - defecto 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); //Tamaño maximo de cada mensaje - defecto 1Mb
        return TopicBuilder.name("maen-Topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
