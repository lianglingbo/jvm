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
 * @Author liang
 * @Date 2018/7/3 13:45
 * @Version 1.0
 **/
public class ReentrantLock3 {
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

    /**
     * 使用trylock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据trylock的返回值来判定是否锁定
     * 也可以指定trylock的时间，由于trylock（time）抛出异常，所以要unlock的处理，必须放到finally中
     */
     void m2(){

         boolean locked = false;
         try {
             lock.tryLock(2,TimeUnit.SECONDS);
             
            System.out.println("m2 ..."+locked);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
             if(locked){
                 //lock.unlock();
                 System.out.println("状态锁定");
             }
         }
     }
    public static void main(String [] args){
        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
