package com.training.rabbitmqfirstsampleconsumer;

import com.rabbitmq.client.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TopicConsumer implements CommandLineRunner {

    private final static String EXCHANGE_NAME="ab-training-topic-exchange";
    private final static String QUEUE_1_NAME="ab-training-queu1";
    private final static String QUEUE_2_NAME="ab-training-queu2";
    private final static String QUEUE_3_NAME="ab-training-queu3";

    @Override
    public void run(String... args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (tag,msg)->{
            System.out.println("Message received");
            System.out.println("msg = " + new String(msg.getBody(),"UTF-8"));

        };
        channel.basicConsume(QUEUE_1_NAME,true,new MyDeliverCallback("All Greeting"),tag->{});
        channel.basicConsume(QUEUE_2_NAME,true,new MyDeliverCallback("Arabic Greeting"),tag->{});
        channel.basicConsume(QUEUE_3_NAME,true,new MyDeliverCallback("English Greeting"),tag->{});
    }

    public static class MyDeliverCallback implements DeliverCallback{

        private final String queueName;

        public MyDeliverCallback(String queueName) {
            this.queueName = queueName;
        }

        @Override
        public void handle(String consumerTag, Delivery message) throws IOException {
            System.out.format("Message received on %s %s\n",queueName,new String(message.getBody(),"UTF-8"));
        }
    }
}
