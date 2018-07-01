package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutors {
    public static void main(String [] args){
        ExecutorService service = Executors.newFixedThreadPool(5);
        TaskRunnable taskRunnbale = new TaskRunnable();
        service.submit(taskRunnbale);
        System.out.println("-----------");
        service.submit(taskRunnbale);
        service.shutdown();
    }
}
