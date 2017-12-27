package com.lihui.annotation.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.security.RunAs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicactionContextCofig.xml")
public class JmsMessageProducerTest {
    @Autowired
    private JmsMessageProducer jmsMessageProducer;
    @Test
    public void sendMessage(){
        try{
            jmsMessageProducer.sendMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
