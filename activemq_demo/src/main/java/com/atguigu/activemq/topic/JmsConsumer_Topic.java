package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author ruttiger
 * @create 2021-02-11 3:48 下午
 */
public class JmsConsumer_Topic {
    private static String ACTIVEMQ_URL = "tcp://192.168.56.139:61616/";
    private static String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer messageConsumer = session.createConsumer(topic);
        while (true) {
            TextMessage textMessage = (TextMessage)messageConsumer.receive();
            if (null != textMessage) {
                System.out.println("*****消费者接收到消息:" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
