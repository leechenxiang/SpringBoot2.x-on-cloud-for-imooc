package com.imooc.controller;

import com.imooc.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("port")
    public String port(){
        return "当前运行的服务端口为：" + port;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${alibaba.config.limit}")
    private Integer limit;
    @Value("${alibaba.config.name}")
    private String name;

    @GetMapping("getConfig")
    public String getConfig(){
        return name + ":" + limit;
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello IMOOC.COM";
    }

    @GetMapping("producer")
    public String producer(){

        /**
         * RabbitMQ 的路由规则 routing Key
         * imooc.msg.*.*  -> * 代表1个占位符
         *  比如：
         *      imooc.msg.do.display    匹配
         *      imooc.msg.display.send  匹配
         *      imooc.msg.send.and.update  不匹配
         *
         * imooc.msg.#  -> # 代表多个占位符
         *  比如：
         *      imooc.msg.do.display    匹配
         *      imooc.msg.display.send  匹配
         *      imooc.msg.send.and.update  匹配
         *      imooc.msg.do.send.and.update  匹配
         */

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_MSG,
                "imooc.msg.send",
                "我发了一个消息~");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_MSG,
                "imooc.msg.delete",
                "我删除了一个消息~");

        return "OK";
    }

}
