package com.news.queue;

import com.news.domain.Categories;
import com.news.domain.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;


@Component
public class MessagePublisher {

    @Autowired
    private JmsTemplate jmsTempalte;

    public void sendMessage(Feed feed) {

        MessagePostProcessor messagePostProcessor = message -> {
            Integer integer = Categories.getFeedCategoryByUser(feed.getPublisher());
            message.setIntProperty("Category", integer);
            return message;
        };
        jmsTempalte.convertAndSend("processor_queue", JsonConvertor.convertToJson(feed), messagePostProcessor);

    }

}