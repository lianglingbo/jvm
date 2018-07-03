package concurrent.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocal1
 * @Description TODO 线程局部变量 ，运行结果为null
 * 每个线程的tl对象都是独立的，局部变量
 * @Author liang
 * @Date 2018/7/3 16:38
 * @Version 1.0
 **/
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();
    public static void main(String [] args){
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person p = new Person();

            tl.set(p);
        }).start();



    }
}

