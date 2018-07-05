package concurrent.demo4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T05_ThreadPool
 * @Description TODO
 * 一个线程池中有两个队列，
 *
 * @Author liang
 * @Date 2018/7/4 11:05
 * @Version 1.0
 **/
public class T05_ThreadPool {
    public static void main(String [] args) throws InterruptedException {
        //executorService往里扔任务，execute:执行的Runnable任务，没有返回值，submit：执行的是call可以有返回值
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<6;i++){
            service.execute(() -> {
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());

        //关闭线程池
        service.shutdown();

        TimeUnit.SECONDS.sleep(1);
        //是否执行完了
        System.out.println(service.isTerminated());
        //是否关闭
        System.out.println(service.isShutdown());
        System.out.println(service);


    }
}
