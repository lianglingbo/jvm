package concurrent;

public class TaskRunnable implements  Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 100 ; i++){
            System.out.println(Thread.currentThread().getName()+"第"+i+"次执行");
        }
    }
}
