package kg.producerservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic loanRequestsTopic() {
        return TopicBuilder.name("loan-requests")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic loanResultsTopic() {
        return TopicBuilder.name("loan-results")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
