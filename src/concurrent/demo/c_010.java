package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_010
 * @Description TODO 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候任然会得到该对象的锁，也就是说synchronized获得的锁是可以重入的，
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 * @Author liang
 * @Date 2018/7/2 16:08
 * @Version 1.0
 **/
public class c_010 {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end ");
    }


    public static void main(String [] args){
        new cc().m();
    }
}

class cc extends c_010{
    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();
        System.out.println("chid m end");
    }
}
