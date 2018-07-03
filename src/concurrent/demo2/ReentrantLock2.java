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
 * @Author liang
 * @Date 2018/7/3 13:45
 * @Version 1.0
 **/
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
        lock.lock();
        for(int i=0;i<10;i++){
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
     void m2(){
        lock.lock();
        System.out.println("m2 ....");
        lock.unlock();
    }
    public static void main(String [] args){
        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
