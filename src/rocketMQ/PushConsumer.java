package rocketMQ;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName PushConsumer
 * @Description TODO
 * 订阅模式
 * @Author liang
 * @Date 2018/7/5 9:55
 * @Version 1.0
 **/
public class PushConsumer {
    public static void main(String [] args){
        DefaultMQPushConsumer consumers = new DefaultMQPushConsumer("consumers");
        consumers.setNamesrvAddr("192.168.31.168:9876");
        try{
            //订阅pushTopic下tag的消息
            consumers.subscribe("orders", (String) null);
            //程序第一次启动从消息队列头取数据
            consumers.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumers.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                    MessageExt msg = list.get(0);
                    System.out.println(new String(msg.getBody()));
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumers.start();
        }catch (Exception e){
            e.printStackTrace();
        }
     }
}
