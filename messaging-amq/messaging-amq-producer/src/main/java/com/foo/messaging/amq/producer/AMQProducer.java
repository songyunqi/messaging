package com.foo.messaging.amq.producer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.foo.messaging.base.DomainAndMessage;
import com.foo.messaging.base.MessageProducer;

@Component("AMQProducer")
public class AMQProducer implements MessageProducer {

	/**
	 * 消息发送模板
	 */
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	/**
	 * 默认的消息域
	 */
	@Value("${amq.destination}")
	public String destination;

	/**
	 * 向默认消息域发送消息
	 * 
	 * @param payload
	 * @param header
	 */
	public void sendMessage(Object payload, Map<String, Object> header) {
		jmsMessagingTemplate.convertAndSend(destination, payload, header);
	}

	/**
	 * 向指定消息域发送消息
	 * 
	 * @param destination
	 * @param payload
	 * @param header
	 */
	public void sendMessage(String destination, Object payload, Map<String, Object> header) {
		jmsMessagingTemplate.convertAndSend(destination, payload, header);
	}

	@Override
	public void send(DomainAndMessage dm) {
		if (null == dm || !dm.isValid()) {
			return;
		}
		sendMessage(dm.getDomain(), null, dm.getMessageBody());
	}

}
