package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_009
 * @Description TODO 模拟死锁
 * @Author liang
 * @Date 2018/7/1 22:51
 * @Version 1.0
 **/
public class c_009 {
    private Object a = new Object();
    private Object b = new Object();

    void m1(){
        synchronized (a){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1 a ");
            synchronized (b){
                System.out.println("m1 b");
            }
        }

    }
    void m2(){
        synchronized (b){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m2 b");
            synchronized (a){
                System.out.println("m2 a");
            }
        }
    }

    public static void main(String [] args){
        c_009 c = new c_009();
        new Thread(() -> c.m1()).start();
        new Thread(() -> c.m2()).start();

    }
}
