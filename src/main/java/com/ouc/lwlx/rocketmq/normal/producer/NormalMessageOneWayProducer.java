package com.ouc.lwlx.rocketmq.normal.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author wlli
 * @date 2021/08/20
 */
public class NormalMessageOneWayProducer {
    public static void main(String[] args) throws Exception {
        // 1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_group");
        // 2.指定Nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动producer
        System.out.println("生产者启动");
        producer.start();

        for (int i = 0; i < 3; i++) {
            // 4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic 参数二：消息Tag 参数三：消息内容
             */
            Message msg = new Message("Topic_demo_sync", "Tag_demo_sync",
                ("Hello 虚竹，" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 5.发送单向消息
            producer.sendOneway(msg);
            System.out.println("发送完");
        }

        // 6.关闭生产者producer
        producer.shutdown();
        System.out.println("生产者关闭");
    }
}
