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
 * 可以指定为公平锁:谁等的时间长，谁获得锁，效率低
 *
 * @Author liang
 * @Date 2018/7/3 13:45
 * @Version 1.0
 **/
public class ReentrantLock5 extends Thread{
    //参数为true标识为公平锁
    private static ReentrantLock lock = new ReentrantLock(true);
    public void run(){
        for(int i=0;i<100;i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }
    
    public static void main(String [] args){
        ReentrantLock5 r = new ReentrantLock5();
        new Thread(r).start();
        new Thread(r).start();
    }
}
