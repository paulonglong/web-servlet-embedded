package com.ouc.lwlx.rocketmq.delay.producer;

import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author wlli
 * @date 2021/08/20
 */
public class DelayMessageProducer {
    public static void main(String[] args)
        throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_delay_group");
        // 2.指定Nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动producer
        producer.start();
        System.out.println("生产者启动");

        for (int i = 0; i < 10; i++) {
            // 4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic 参数二：消息Tag 参数三：消息内容
             */
            Message msg = new Message("DelayTopic", "Tag1", ("Hello 虚竹" + i).getBytes());
            // 设定延迟时间 10s
            msg.setDelayTimeLevel(3);
            // 5.发送消息
            SendResult result = producer.send(msg);
            // 发送状态
            SendStatus status = result.getSendStatus();

            System.out.println("发送结果:" + result);

            TimeUnit.SECONDS.sleep(1);
        }

        // 6.关闭生产者producer
        producer.shutdown();
    }
}
