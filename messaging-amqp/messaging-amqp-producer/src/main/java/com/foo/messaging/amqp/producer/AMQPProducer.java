package com.foo.messaging.amqp.producer;

import com.foo.messaging.base.DomainAndMessage;
import com.foo.messaging.base.MessageProducer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AMQPProducer implements MessageProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void send(DomainAndMessage dm) {
		this.amqpTemplate.convertAndSend(dm.getDomain(), "", dm.getMessageBody());
	}

}
