package com.foo.messaging.rocket.test;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDemo {

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='TagA'")
    public void rocketmqMsgListen(RocketmqEvent event) {
        try {
            System.out.println("com.guosen.client.controller.consumerDemo监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

