 package com.ouc.lwlx.rocketmq.batch.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

/**
 * @author wlli
 * @date 2021/08/20
 */
public class BatchMessageProducer {
    public static void main(String[] args) throws Exception {
        // 1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_batch_group");
        // 2.指定Nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动producer
        producer.start();
        System.out.println("生产者启动");
        List<Message> msgs = new ArrayList<Message>();

        // 4.创建消息对象，指定主题Topic、Tag和消息体
        /**
         * 参数一：消息主题Topic 参数二：消息Tag 参数三：消息内容
         */
        for (int i = 0; i < 20; i++) {
            Message msg = new Message("Topic_batch_demo", "Tag_batch_demo", ("Hello 虚竹，这是批量消息" + i).getBytes());
            msgs.add(msg);
        }

        // 5.发送消息
        SendResult result = producer.send(msgs);
        // 发送状态
        SendStatus status = result.getSendStatus();

        System.out.println("发送结果:" + result);

        // 线程睡1秒
        TimeUnit.SECONDS.sleep(1);

        // 6.关闭生产者producer
        producer.shutdown();
        System.out.println("生产者关闭");
    }
}
