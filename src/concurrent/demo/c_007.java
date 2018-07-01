package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_007
 * @Description TODO 对业务写加锁,读不加锁,容易产生脏读问题; 线程还在sleep,读的数据就是空的;
 * @Author liang
 * @Date 2018/7/1 13:21
 * @Version 1.0
 **/
public class c_007 {
    String name;
    double balance;
    public synchronized void set (String name,double balance){
        this.name = name;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;

    }

    public double getBalance(String name) {
        return this.balance;
    }
    public static void main(String [] args){
        c_007 c = new c_007();
        new Thread(() -> c.set("liang",12345)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.getBalance("liang"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.getBalance("liang"));
    }

}
