package com.docker.demo.web.controller;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 消费者
 *
 * @author DUCHONG
 * @since 2020-09-02 21:33
 **/
@Component
@Slf4j
public class ReplyConsumerController {


    /**
     * 邮件发送 ack 之后返回消息到demo.reply-to
     * @param message
     * @param channel
     * @param headers
     * @throws IOException
     */
    @RabbitListener(queues ="demo.sms")
    @RabbitHandler
    @SendTo("demo.reply-to")
    public String handleEmailMessage(Message message, Channel channel, @Headers Map<String,Object> headers) throws IOException {

        try {

            String msg=new String(message.getBody(), CharEncoding.UTF_8);
            log.info("---consumer接收到消息----{}",msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return msg;
        }
        catch (Exception e) {
            log.info("ReplyConsumerController.handleEmailMessage error",e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);

        }
        return null;
    }
}
