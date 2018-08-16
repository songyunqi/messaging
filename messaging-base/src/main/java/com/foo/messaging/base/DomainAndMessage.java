package com.foo.messaging.base;

import lombok.Data;

import java.util.Map;

@Data
public class DomainAndMessage {

    private String domain;
    private Map<String, Object> messageBody;

    public boolean isValid() {
        return (null != domain && null != messageBody);
    }
}
