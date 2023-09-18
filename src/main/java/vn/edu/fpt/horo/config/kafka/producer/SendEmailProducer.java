package vn.edu.fpt.horo.config.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.event.SendEmailEvent;
import vn.edu.fpt.horo.exception.BusinessException;

import java.util.UUID;

/**
 * vn.edu.fpt.accounts.config.kafka.producer
 **/

@Service
@Slf4j
public class SendEmailProducer extends Producer{

    private static final String TOPIC = "horo.notification.send_email";
    private final ObjectMapper objectMapper;

    @Autowired
    public SendEmailProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        super(kafkaTemplate);
        this.objectMapper = objectMapper;
    }

    public void sendMessage(SendEmailEvent event) {
        try {
            String value = objectMapper.writeValueAsString(event);
            super.sendMessage(TOPIC, UUID.randomUUID().toString(), value);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't send message to topic "+ TOPIC+" : "+ex.getMessage());
        }
    }
}
