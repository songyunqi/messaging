package com.foo.messaging.rocket.test;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerDemo {
    @Autowired
    private DefaultMQProducer defaultProducer;

    @Autowired
    private TransactionMQProducer transactionProducer;

    private int i = 0;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public void sendMsg() {
        Message msg = new Message("TopicTest1", // topic
                "TagA", // tag
                "OrderID00" + i, // key
                ("Hello zebra mq" + i).getBytes());// body
        try {
            defaultProducer.send(msg, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                    // TODO 发送成功处理
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e);
                    // TODO 发送失败处理
                }
            });
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/sendTransactionMsg", method = RequestMethod.GET)
    public String sendTransactionMsg() {
        SendResult sendResult = null;
        try {
            // 构造消息
            Message msg = new Message("TopicTest1", // topic
                    "TagA", // tag
                    "OrderID001", // key
                    ("Hello zebra mq").getBytes());// body

            sendResult = transactionProducer.sendMessageInTransaction(msg, 4);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult.toString();
    }

    @RequestMapping(value = "/sendMsgOrder", method = RequestMethod.GET)
    public void sendMsgOrder() {
        Message msg = new Message("TopicTest1", // topic
                "TagA", // tag
                "OrderID00" + i, // key
                ("Hello zebra mq" + i).getBytes());// body
        try {
            defaultProducer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("MessageQueue" + arg);
                    int index = ((Integer) arg) % mqs.size();
                    return mqs.get(index);
                }
            }, i);// i==arg
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

