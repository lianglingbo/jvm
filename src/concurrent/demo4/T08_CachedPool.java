package concurrent.demo4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T08_CachedPool
 * @Description TODO
 * 默认生存周期为60秒
 * @Author liang
 * @Date 2018/7/4 13:50
 * @Version 1.0
 **/
public class T08_CachedPool {
    public static void main(String [] args){
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        
        for (int i=0;i<2;i++){
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);
    }
}
