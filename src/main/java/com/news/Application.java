package com.news;

import com.news.queue.MessagePublisher;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import twitter4j.*;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
public class Application {


    @Bean
    public Queue queue() {
        return new ActiveMQQueue("processor_queue");
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        MessagePublisher messagePublisher = (MessagePublisher)context.getBean("messagePublisher");

        Twitter twitter = TwitterFactory.getSingleton();

        while (true) {
            try {
                Query query = new Query("JP Morgan");
                QueryResult result = twitter.search(query);
                for (
                        Status status : result.getTweets()) {

                    System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText() );
                    messagePublisher.sendMessage(status.getText());

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }



}
