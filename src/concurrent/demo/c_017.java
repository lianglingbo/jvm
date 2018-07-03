package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_017
 * @Description TODO
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另一个对象，则锁定的对象发生改变
 * 应该避免将锁定的对象的引用变成另外的对象
 *
 * @Author liang
 * @Date 2018/7/3 9:28
 * @Version 1.0
 **/
public class c_017 {
    Object o = new Object();
    void m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
    public static void main(String [] args) throws InterruptedException {
        c_017 c = new c_017();
        new Thread(() -> c.m()).start();
        //假如这个时候，把锁对象的引用换掉，第二个线程就能进入了\
        TimeUnit.SECONDS.sleep(1);
        c.o = new Object();
         new Thread(() -> c.m()).start();


    }
}
