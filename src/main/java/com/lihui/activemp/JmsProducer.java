package com.lihui.activemp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 消息生产者
 * @author lihui
 */
public class JmsProducer {
    private  static final Logger logger=LoggerFactory.getLogger(JmsProducer.class);
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
    /**
     * 发送的消息数量
     */
    private static final int SENDNUM=10;

    public static void main(String[] args){
//        连接工厂
        ConnectionFactory connectionFactory;
//        连接
        Connection connection=null;
//        会话  接受或者发送消息的线程
        Session session;
//        消息的目的地
        Destination destination;
//        消息的生产者
        MessageProducer messageProducer;
//        实例化连接工厂
        connectionFactory=new ActiveMQConnectionFactory(JmsProducer.USERNAME,JmsProducer.PASSWORD,JmsProducer.BROKEURL);
        try{
//            创建连接
            connection=connectionFactory.createConnection();
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
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
//            创建一个名称为lihui的消息队列
            destination=session.createQueue("lihui");
//            创建消息生产者
            messageProducer=session.createProducer(destination);
//            发送消息
            sendMessage(session,messageProducer);

            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 发送消息
     * @param session
     * @param messageProducer 消息生产者
     */
    private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
        for (int i=0; i<JmsProducer.SENDNUM; i++){
            TextMessage message=session.createTextMessage("ActiveMQ 发送的消息"+i+"lihuisiiduvbisufvnsk");
            messageProducer.send(message);
            logger.info("ActiveMQ 发送的消息[{}]======={}",i,"lihuisiiduvbisufvnsk");
        }
    }
}
