package com.foo.messaging.kafka.producer;

import com.foo.messaging.base.DomainAndMessage;
import com.foo.messaging.base.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements MessageProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(DomainAndMessage dm) {

        kafkaTemplate.send("test", dm.getMessageBody());
    }
}
