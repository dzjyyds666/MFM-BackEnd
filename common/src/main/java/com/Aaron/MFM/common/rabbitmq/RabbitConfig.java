package com.Aaron.MFM.common.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {
    public static final String DELAYED_QUEUE_NAME = "delay.queue.delay.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delay.queue.delay.exchange";
    public static final String DELAYED_ROUTING_KEY = "delay.queue.delay.routingkey";

    public static final String CHAT_QUEUE = "chat.queue";

    public static final String CHAT_EXCHANGE = "chat.exchange";

    public static final String CHAT_ROUTING_KEY = "chat.routingkey";

    public static final String ORDER_QUEUE = "order.queue";

    public static final String ORDER_EXCHANGE = "order.exchange";

     public static final String ORDER_ROUTING_KEY = "order.routingkey";

     @Bean
     public Queue orderQueue() {
         return new Queue(ORDER_QUEUE, true);
     }

     @Bean
     public DirectExchange orderExchange() {
         return new DirectExchange(ORDER_EXCHANGE, true, false);
     }
     @Bean
     public Binding bindingOrder(@Qualifier("orderQueue") Queue queue,
                                   @Qualifier("orderExchange") DirectExchange exchange) {
         return BindingBuilder.bind(queue).to(exchange).with(ORDER_ROUTING_KEY);
     }

    @Bean
    public Queue ChatQueue() {
        return new Queue(CHAT_QUEUE, true);
    }

    @Bean
    public DirectExchange chatExchange() {
        return new DirectExchange(CHAT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingDelayed(@Qualifier("ChatQueue") Queue queue,
                                  @Qualifier("chatExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CHAT_ROUTING_KEY);
    }

    @Bean
    public Queue immediateQueue() {
        return new Queue(DELAYED_QUEUE_NAME,true);
    }

    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("immediateQueue") Queue queue,
                                 @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }


}
