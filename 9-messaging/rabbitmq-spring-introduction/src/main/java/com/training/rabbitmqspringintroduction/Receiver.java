package com.training.rabbitmqspringintroduction;

public class Receiver {

    private final String queueName;

    public Receiver(String queueName) {
        this.queueName = queueName;
    }

    public void receiveMsg(String msg){
        System.out.printf("Message received queue %s %s\n",queueName,msg);
    }
}
