package concurrent.demo3.context;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName T09_SynchronusQueue
 * @Description TODO
 * 同步队列，一种特殊的TransferQueue,容量为0的队列，来的信息必须马上消费掉，不能使用add，只能用put，因为put可以阻塞等待消费者消费，里面用的是transfer
 * @Author liang
 * @Date 2018/7/4 10:12
 * @Version 1.0
 **/
public class T09_SynchronusQueue {
    public static void main(String [] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();
        new Thread(() -> {
            try{
                System.out.println(strs.take());
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }).start();
        strs.put("qq");
        //strs.add("ss");
        System.out.println(strs.size());
    }
}
