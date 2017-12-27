package com.lihui.annotation.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class JmsMessageListener implements MessageListener   {

    private static final Logger logger= LoggerFactory.getLogger(JmsMessageListener.class);

    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        try {
            logger.info("==================Consumed message:{}===================="
                    ,textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
