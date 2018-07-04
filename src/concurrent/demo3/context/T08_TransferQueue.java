package concurrent.demo3.context;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T08_TransferQueue
 * @Description TODO
 * 消费者先启动，生产者先判断是否有消费者线程，如果有，直接给消费者，不用发给队列
 * @Author liang
 * @Date 2018/7/4 9:47
 * @Version 1.0
 **/
public class T08_TransferQueue {
    public static void main(String [] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
      /*  new Thread(() -> {
            try{
                System.out.println(strs.take());
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }).start();*/


        strs.transfer("aaa");//特殊方法，找不到消费者就阻塞
        //strs.add("vbv");
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try{
                System.out.println(strs.take());
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }).start();

    }
}
