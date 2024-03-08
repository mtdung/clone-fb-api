package vn.edu.fpt.fb.config.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * vn.edu.fpt.accounts.config.kafka.producer
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    protected void sendMessage(String topic, String key, String value){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info(String.format("Produced event to topic %s: key = %-10s value = %s", topic, key, value));
            }
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }
        });
    }
}
