package com.lihui.annotation.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;

@Component
public class JmsMessageProducer {
    private static final Logger logger= LoggerFactory.getLogger(JmsMessageProducer.class);
    @Autowired
    private JmsTemplate jmsQueueTemplate;
    @Autowired
    private Queue testQueueActiveMQ;

    private int messageSize=100;

    public void sendMessage(){
        StringBuffer stringBuffer=null;
        logger.info("===================start=====================");
        for(int i=0; i<messageSize; i++){
            stringBuffer=new StringBuffer("MESSAGE [" +i+ "] IS ："+ new Date().toString());
            jmsQueueTemplate.convertAndSend(testQueueActiveMQ,stringBuffer.toString());
            logger.info("=====已发送消息====={}",stringBuffer.toString());
            stringBuffer=null;
        }
        logger.info("====================end=======================");
    }
}
