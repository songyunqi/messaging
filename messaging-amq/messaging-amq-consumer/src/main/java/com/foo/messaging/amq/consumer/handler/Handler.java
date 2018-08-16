package com.foo.messaging.amq.consumer.handler;

import javax.jms.JMSException;
import javax.jms.Message;

public interface Handler {
	void handle(Message t) throws JMSException;
}
