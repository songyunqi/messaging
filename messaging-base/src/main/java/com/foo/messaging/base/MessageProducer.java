package com.foo.messaging.base;

public interface MessageProducer {
    void send(DomainAndMessage dm);
}
