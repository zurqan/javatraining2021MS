package com.training.rabbitmqfirstsample;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {

    private final static String EXCHANGE_NAME="ab-training-exchange";
    private final static String QUEUE_NAME="ab-training-queu";
    private final static String ROUTING_KEY="ab-training-rout";
    @Override
    public void run(String... args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection= connectionFactory.newConnection()){

            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();

            builder = builder.userId("mohammd");

            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,builder.build(),"Hello World latest!".getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,builder.deliveryMode(2).build(),"Hello World latest!".getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
