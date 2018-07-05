package rocketMQ;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



/**
 * @ClassName PullConsumer
 * @Description TODO
 * 
 * @Author liang
 * @Date 2018/7/5 10:22
 * @Version 1.0
 **/
public class PullConsumer {
    private static final Map<MessageQueue,Long> offsetTable = new HashMap<MessageQueue,Long>();
    
    public static void main(String [] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumergroup");
        consumer.setNamesrvAddr("192.168.31.168:9876");
        consumer.start();

        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("orders");
        System.out.println((mqs.size()));
        int i=0;
        for(MessageQueue mq:mqs){
            System.out.println("Consume from the queue:"+i++ +"" +mq);
           /* PullResult pullResult = consumer.pull(mq, null, mq.toString(), 32);
            pullResult.getMsgFoundList().forEach((m)->{
                System.out.println(new String(m.getBody()));
            });*/
            System.out.println(mq);
        }
        consumer.shutdown();
    }
}
