package concurrent.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocal1
 * @Description TODO 线程局部变量
 * @Author liang
 * @Date 2018/7/3 16:38
 * @Version 1.0
 **/
public class ThreadLocal1 {
    volatile static Person p = new Person();
    public static void main(String [] args){
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name="lisi";
        }).start();



    }
}
class Person{
    String name = "zhangsan";
}
