package com.lihui.activemp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author lihui 2017 11 05
 * 消息消费者
 */
public class JmsConsumer {
    /**
     * 默认连接用户名
     */
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    /**
     * 默认连接密码
     */
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * 默认连接地址
     */
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
//        连接工厂
        ConnectionFactory connectionFactory;
//        连接
        Connection connection = null;
//        会话  接受或者发送消息的线程
        Session session;
//        消息的目的地
        Destination destination;
//        消息的消费者
        MessageConsumer messageConsumer;
//        实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JmsConsumer.USERNAME, JmsConsumer.PASSWORD, JmsConsumer.BROKEURL);
        try {
//            创建连接
            connection = connectionFactory.createConnection();
//            启动连接
            connection.start();
            /*创建会话，参数param1表示是否支持事务，
            * 参数param2表示确认动作由谁来做：
            * Session.AUTO_ACKNOWLEDGE：为自动确认，
            * 客户端发送和接收消息不需要做额外的工作
            * Session.CLIENT_ACKNOWLEDGE：为客户端确认。
            * 客户端接收到消息后，必须调用javax.jms.Message
            * 的acknowledge方法。jms服务器才会删除消息
            */
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            创建一个连接lihui的消息队列
            destination = session.createQueue("lihui");
//            创建消息消费者
            messageConsumer=session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(1000000000);
                if(textMessage != null){
                    System.out.println("收到的消息:" + textMessage.getText());
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
