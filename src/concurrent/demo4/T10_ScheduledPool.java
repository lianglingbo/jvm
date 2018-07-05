package concurrent.demo4;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T10_ScheduledPool
 * @Description TODO
 * 定时任务,每隔500毫秒任务重复执行
 * @Author liang
 * @Date 2018/7/4 14:06
 * @Version 1.0
 **/
public class T10_ScheduledPool {
    public static void main(String [] args){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS);

    }
}
