package concurrent.demo3.context;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T05_LinkedBlockingQueue
 * @Description TODO
 * @Author liang
 * @Date 2018/7/4 8:55
 * @Version 1.0
 **/
public class T05_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String [] args){
        new Thread(() -> {
            for(int i=0;i<100;i++){
                try {
                    strs.put("a"+i);//如果满了就会等待
                    TimeUnit.MICROSECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for(int i=0;i<5;i++){
            new Thread(() -> {
                for(;;){
                    try{
                        System.out.println(Thread.currentThread().getName()+"take -"+strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }
    }

}
