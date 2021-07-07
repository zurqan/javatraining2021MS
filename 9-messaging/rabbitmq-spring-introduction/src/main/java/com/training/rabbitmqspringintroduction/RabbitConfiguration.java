package com.training.rabbitmqspringintroduction;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue queue(){
        return new Queue("spring-queue");
    }
    @Bean
    public Queue queue1(){
        return new Queue("spring-queue-arabic");
    }
    @Bean
    public Queue queue2(){
        return new Queue("spring-queue-english");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("spring-topic");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(topicExchange())
                .with("spring.greeting.#");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder
                .bind(queue2())
                .to(topicExchange())
                .with("spring.greeting.english");
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder
                .bind(queue1())
                .to(topicExchange())
                .with("spring.greeting.arabic");
    }


    @Bean
    public SimpleMessageListenerContainer q1Receiver(ConnectionFactory connectionFactory){

        SimpleMessageListenerContainer list = new SimpleMessageListenerContainer(connectionFactory);
        list.setQueueNames("spring-queue");
        list.setMessageListener(new MessageListenerAdapter(new Receiver("All Greeting"),"receiveMsg"));

        return list;
    }

    @Bean
    public SimpleMessageListenerContainer q2Receiver(ConnectionFactory connectionFactory){

        SimpleMessageListenerContainer list = new SimpleMessageListenerContainer(connectionFactory);
        list.setQueueNames("spring-queue-english");
        list.setMessageListener(new MessageListenerAdapter(new Receiver("English"),"receiveMsg"));

        return list;
    }

    @Bean
    public SimpleMessageListenerContainer q3Receiver(ConnectionFactory connectionFactory){

        SimpleMessageListenerContainer list = new SimpleMessageListenerContainer(connectionFactory);
        list.setQueueNames("spring-queue-arabic");
        list.setMessageListener(new MessageListenerAdapter(new Receiver("Arabic"),"receiveMsg"));

        return list;
    }
    @Bean
    CommandLineRunner sender(RabbitTemplate rabbitTemplate){
        return args->{
            rabbitTemplate.convertAndSend("spring-topic",
                    "spring.greeting.arabic","السلام عليكم");
            rabbitTemplate.convertAndSend("spring-topic",
                    "spring.greeting.english","Hi");
        };
    }
}
