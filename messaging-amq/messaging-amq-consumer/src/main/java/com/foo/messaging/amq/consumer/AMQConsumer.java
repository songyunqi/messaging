package com.foo.messaging.amq.consumer;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.foo.messaging.amq.consumer.handler.Handler;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 */
@EnableJms
@Component
@Slf4j
public class AMQConsumer {

	@Autowired
	ApplicationContext context;

	/**
	 * 消息处理
	 *
	 * @param message
	 */
	@JmsListener(containerFactory = "jmsListenerContainerFactory", destination = "${amq.destination}")
	public void onMessage(Message message) {
		Map<String, Handler> handlers = context.getBeansOfType(Handler.class);
		if (CollectionUtils.isEmpty(handlers)) {
			log.debug(" No message handler,activemq message discard.");
			return;
		}
		for (Map.Entry<String, Handler> entry : handlers.entrySet()) {
			Handler tHandler = entry.getValue();
			if (tHandler == null) {
				continue;
			}
			try {
				tHandler.handle(message);
			} catch (JMSException e) {
				log.error("tHandler.handle(message) error.", e);
			}
		}
	}
}
