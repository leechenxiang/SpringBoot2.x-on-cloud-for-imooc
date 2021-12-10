package com.imooc;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 1. 定义交换机的名称
     * 2. 定义队列的名称
     * 3. 创建交换机
     * 4. 创建队列
     * 5. 绑定队列和交换机（把队列绑定到交换机）
     */

    // 定义交换机的名称
    public static final String EXCHANGE_MSG = "exchange_msg";

    // 定义队列的名称
    public static final String QUEUE_MSG = "queue_msg";

    // 创建交换机
    @Bean(EXCHANGE_MSG)
    public Exchange exchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_MSG)
                .durable(true)
                .build();
    }

    // 创建队列
    @Bean(QUEUE_MSG)
    public Queue queue() {
        return new Queue(QUEUE_MSG);
    }

    // 绑定队列和交换机
    @Bean
    public Binding binding(
            @Qualifier(QUEUE_MSG) Queue queue,
            @Qualifier(EXCHANGE_MSG) Exchange exchange) {

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("imooc.msg.*")
                .noargs();
    }
}
