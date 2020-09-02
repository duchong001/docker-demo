package com.docker.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DUCHONG
 * @since 2020-09-02 21:24
 **/
@Configuration
public class RabbitConfig {


    @Value("${sms.queue.name}")
    private String smsQueue;
    @Value("${reply.queue.name}")
    private String replyQueue;
    @Value("${reply.exchange.name}")
    private String replyExchange;


    @Bean
    public Queue smsQueue() {
        return new Queue(smsQueue);
    }
    @Bean
    public Queue replyQueue() {
        return new Queue(replyQueue);
    }

    @Bean
    TopicExchange replyExchange() {
        return new TopicExchange(replyExchange);
    }


    @Bean
    Binding bindingReplyQueue() {
        return BindingBuilder.bind(smsQueue()).to(replyExchange()).with(smsQueue+".#");
    }


}
