package com.lihui.annotation.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * [@Configuration]注解相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器
 */
@Configuration()
public class QueueDestinationConfig {
    /**
     * [@Bean]标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
     */
    @Bean
    public Queue testQueueActiveMQ(){
        return new ActiveMQQueue("lihui02ling");
    }
}
