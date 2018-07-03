package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_008
 * @Description TODO 一个线程同步方法可以调用另一个同步方法,一个线程已经拥有某个对象的锁,再次申请的时候任然会得到该对象的锁,也就是说synchronized获得锁是可以重入的
 *
 * @Author liang
 * @Date 2018/7/1 22:30
 * @Version 1.0
 **/
public class c_008 {
    synchronized void m1(){
        System.out.println("m1 start ...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }
    synchronized  void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
    
    public static void main(String [] args){
        c_008 c = new c_008();
        new Thread(() -> c.m1() ).start();
    }
}
