package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_016
 * @Description TODO
 * synchronized优化
 * 同步代码块内的语句越少越好
 * @Author liang
 * @Date 2018/7/3 9:11
 * @Version 1.0
 **/
public class c_016 {
    int count = 0;
    synchronized void m1(){
        //do something nedd not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有这句需要synchronized，这时不应该给整个方法上锁
        while (count<10000){
        count++;
        }

        //do something need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //只有需要锁的时候才加锁，线程争取时间变短
        synchronized (this){
            while(count<10000){
            count++;
            }

        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        c_016 c = new c_016();
        long start = System.currentTimeMillis();
//        new Thread(() ->c.m1() ).start();
//         new Thread(() ->c.m1() ).start();
         new Thread(() ->c.m2() ).start();
         new Thread(() ->c.m2() ).start();
        TimeUnit.SECONDS.sleep(4);
        System.out.println(c.count);
        if(c.count>9999){
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start));

        }



    }

}
