package com.foo.messaging.amqp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration()
public class ConsumerConfig {

	@RabbitListener(queues = "fanout.A")
	public class FanoutReceiveA {
		
		@RabbitHandler
		public void process(String message) {
			System.out.println("fanout Receiver A  : " + message);
		}
	}

	/*
	@Component
	@RabbitListener(queues = "fanout.B")
	public class FanoutReceiverB {
		@RabbitHandler
		public void process(String message) {
			System.out.println("fanout Receiver B: " + message);
		}
	}

	@Component
	@RabbitListener(queues = "fanout.C")
	public class FanoutReceiverC {
		@RabbitHandler
		public void process(String message) {
			System.out.println("fanout Receiver C: " + message);
		}
	}
	*/

}
