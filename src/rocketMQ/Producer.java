package rocketMQ;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author liang
 * @Date 2018/7/5 9:32
 * @Version 1.0
 **/
public class Producer {
    public static void main(String [] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //指定producer组名。
        DefaultMQProducer producer = new DefaultMQProducer("group");
        producer.setNamesrvAddr("192.168.31.168:9876");
        producer.start();
        for(int i=0;i<100;i++){
            Message msg = new Message("orders", ("order" + i).getBytes());
            SendResult result = producer.send(msg);
            System.out.println(result);
            System.out.println(msg+"send out");
            Thread.sleep(500);
        }
        producer.shutdown();
    }
}
