package concurrent.demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLock1
 * @Description TODO
 * 重入锁用于替代synchronized
 * 本例用于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 使用reentranLock代替的写法
 * 用完之后必须要手动释放
 * 使用syn锁定遇到异常，jvm会自动释放锁；但是使用lock必须手动释放，所有在finally中进行释放
 *
 *在使用reentrantlock可以进行尝试锁定，trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否等待继续
 *
 * 使用reentrantlock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 一个在等待获得锁的线程，常规下不能被打断，
 * 用interrupt打断
 *
 * @Author liang
 * @Date 2018/7/3 13:45
 * @Version 1.0
 **/
public class ReentrantLock4 {


    public static void main(String [] args){
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println("t1 start");
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            }finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                //lock.lock();  //如果t2使用常规方式锁定，t2不会被打断
                lock.lockInterruptibly();//使用可被打断锁，能接受中断信息
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            }finally {
                //lock.unlock();
            }
        });
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();//打断线程2的等待
    }
}
