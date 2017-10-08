package com.hun.blog.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * The type Jms config.
 */
@Configuration
public class JMSConfig {
    private static final String textMsgQueue = "text.messagequeue";

    @Bean
    public Queue textMessageQueue(){
        return new ActiveMQQueue(textMsgQueue);
    }
}
