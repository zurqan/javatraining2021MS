package com.training.rabbitmqfirstsample;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class DefaultSender implements CommandLineRunner {

    private final static String EXCHANGE_NAME="ab-training-fanout-exchange";
    private final static String QUEUE_1_NAME="ab-training-queu1";
    private final static String QUEUE_2_NAME="ab-training-queu2";
    private final static String QUEUE_3_NAME="ab-training-queu3";
    private final static String ROUTING_1_KEY="greeting.#";
    private final static String ROUTING_2_KEY="greeting.arabic";
    private final static String ROUTING_3_KEY="greeting.english";
    @Override
    public void run(String... args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection= connectionFactory.newConnection()){

            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout",true);
            channel.queueDeclare(QUEUE_1_NAME,true,false,false,null);
            channel.queueDeclare(QUEUE_2_NAME,true,false,false,null);
            channel.queueDeclare(QUEUE_3_NAME,true,false,false,null);
            channel.queueBind(QUEUE_1_NAME,EXCHANGE_NAME,ROUTING_1_KEY);
            channel.queueBind(QUEUE_2_NAME,EXCHANGE_NAME,ROUTING_2_KEY);
            channel.queueBind(QUEUE_3_NAME,EXCHANGE_NAME,ROUTING_3_KEY);
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            channel.basicPublish("",QUEUE_2_NAME,null,"السلام عليكم".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
