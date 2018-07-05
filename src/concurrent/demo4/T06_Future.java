package concurrent.demo4;

import java.util.concurrent.*;

/**
 * @ClassName T06_Future
 * @Description TODO
 * futureTask将callable的实现进行封装，未来可以产生返回值的任务
 * @Author liang
 * @Date 2018/7/4 11:34
 * @Version 1.0
 **/
public class T06_Future {
    public static void main(String [] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            //装的是一个callable类型任务，带返回值
            TimeUnit.MILLISECONDS.sleep(5000);
            return 1000;
        });
        new Thread(task).start();
        //阻塞，等任务执行完，得到返回值1000
        System.out.println(task.get());

        //方式二直接使用submit提交任务，直接放到未来的返回值里
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f =service.submit(() ->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());


    }
}
