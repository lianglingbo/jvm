package concurrent.demo4;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName T11_WorkStealingPool
 * @Description TODO
 * 工作窃取线程池：
 * 每个线程都维护自己的任务队列，当某个线程完成了自己的任务队列，会去窃取其他任务队列
 * 8核，8个线程，
 * 第9个线程开始时，线程1会窃取执行
 * @Author liang
 * @Date 2018/7/4 14:21
 * @Version 1.0
 **/
public class T11_WorkStealingPool {

    public static void main(String [] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(3000));
        service.execute(new R(4000));
        service.execute(new R(5000));
        service.execute(new R(6000));
        service.execute(new R(7000));
        service.execute(new R(8000));
        service.execute(new R(9000));
        service.execute(new R(10000));

        //精灵线程，后台执行，控制台看不到
        System.in.read();

    }

    static class R implements Runnable{
        int time;
        R(int t){
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+""+Thread.currentThread().getName());
        }
    }
}
