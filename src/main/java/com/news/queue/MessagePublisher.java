package com.news.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class MessagePublisher {

    @Autowired
    private JmsTemplate jmsTempalte;

    public void sendMessage(String message) {

        jmsTempalte.convertAndSend("processor_queue", message);

    }

}