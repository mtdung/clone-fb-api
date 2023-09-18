package vn.edu.fpt.horo.config.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * vn.edu.fpt.accounts.config.kafka.producer
 **/

@Service
@Slf4j
public class CreateAccountActivityProducer extends Producer{

    private static final String TOPIC = "create_account_horo";
    private final ObjectMapper objectMapper;

    @Autowired
    public CreateAccountActivityProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        super(kafkaTemplate);
        this.objectMapper = objectMapper;
    }
}
