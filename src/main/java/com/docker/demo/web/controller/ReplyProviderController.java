package com.docker.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 生产者
 *
 * @author DUCHONG
 * @since 2020-09-02 21:32
 **/
@RestController
@Slf4j
public class ReplyProviderController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${reply.exchange.name}")
    private String replyExchange;

    @GetMapping("/sendReplyMessage")
    public void sendReplyMessage() {

        String msgId = UUID.randomUUID().toString().replace("-","").toUpperCase();
        JSONObject reply=new JSONObject();
        reply.put("messageId",msgId);
        reply.put("content","this is a reply demo message");
        CorrelationData correlationData=new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(replyExchange,"demo.sms.x",reply.toJSONString(),correlationData);
        log.info("---provider发送消息---{}",reply);
    }

    /**
     * 监听demo.reply-to队列，接收consumer的反馈
     * @param message
     * @param channel
     * @param headers
     * @throws IOException
     */
    @RabbitListener(queues ="demo.reply-to")
    @RabbitHandler
    public void handleReplyMessage(Message message, Channel channel, @Headers Map<String,Object> headers) throws IOException {

        try {

            String msg=new String(message.getBody(), CharEncoding.UTF_8);
            log.info("---provider接收到reply消息----{}",msg);
            //业务逻辑代码
            //.....
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        catch (Exception e) {
            log.info("ReplyConsumerController.handleReplyMessage error",e);
        }
    }

}
