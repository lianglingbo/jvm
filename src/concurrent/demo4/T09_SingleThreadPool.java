package concurrent.demo4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName T09_SingleThreadPool
 * @Description TODO
 * 线程池里就一个线程
 * 保证任务顺序执行
 * @Author liang
 * @Date 2018/7/4 14:02
 * @Version 1.0
 **/
public class T09_SingleThreadPool {
    public static void main(String [] args){
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            final int j = i;
            service.execute(()->{
                System.out.println(j+Thread.currentThread().getName());
            });
        }
    }
}
