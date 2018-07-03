package concurrent.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReentrantLock1
 * @Description TODO
 * 重入锁用于替代synchronized
 * 本例用于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 就是synchronized最原始的语义
 * @Author liang
 * @Date 2018/7/3 13:45
 * @Version 1.0
 **/
public class ReentrantLock1 {
    synchronized void m1(){
        for(int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    synchronized void m2(){
        System.out.println("m2 ....");
    }
    public static void main(String [] args){
        ReentrantLock1 r1 = new ReentrantLock1();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
