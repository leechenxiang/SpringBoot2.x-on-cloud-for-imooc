package com.imooc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = {RabbitMQConfig.QUEUE_MSG})
    public void watchQueue(String payload, Message message) {

        // payload 消息的载体：消息内容
        System.out.println(payload);

        // 路由规则
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        System.out.println(routingKey);
        if (routingKey.equalsIgnoreCase("imooc.msg.send")) {
            // fixme：处理发送类的业务
        } else if (routingKey.equalsIgnoreCase("imooc.msg.delete")) {
            // fixme：处理删除类的业务
        }
    }

}
