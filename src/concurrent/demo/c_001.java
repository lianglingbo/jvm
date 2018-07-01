package concurrent.demo;

import java.io.ObjectOutputStream;

/**
 * @ClassName c_001
 * @Description TODO 新建一个对象,每次运行run方法时,都要拿到该对象的锁才能运行
 * @Author liang
 * @Date 2018/7/1 11:33
 * @Version 1.0
 **/
public class c_001 implements Runnable{
    private int count = 10;
    private Object o =new Object();
    public void run(){
       // synchronized (o){
            count --;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
       // }
    }
    public static void main(String [] args){
        c_001 c_001 = new c_001();
        Thread thread1 = new Thread(c_001);
         Thread thread2 = new Thread(c_001);
         Thread thread3 = new Thread(c_001);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
