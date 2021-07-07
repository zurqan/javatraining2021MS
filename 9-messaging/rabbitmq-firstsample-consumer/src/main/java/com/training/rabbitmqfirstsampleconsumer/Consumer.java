package com.training.rabbitmqfirstsampleconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Consumer  implements CommandLineRunner {

    private final static String EXCHANGE_NAME="ab-training-exchange";
    private final static String QUEUE_NAME="ab-training-queu";
    private final static String ROUTING_KEY="ab-training-rout";

    @Override
    public void run(String... args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (tag,msg)->{
            System.out.println("Message received");
            System.out.println("msg = " + new String(msg.getBody(),"UTF-8"));

        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,tag->{});
    }
}
