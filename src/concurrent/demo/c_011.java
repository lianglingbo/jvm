package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_011
 * @Description TODO 程序执行过程中，如果有异常，默认情况锁会被释放，所以在并发处理的过程中，有异常要多加小心，会出现异常处理不一致的情况；
 *  比如，在一个web app处理过程中，多个servlet线程同步访问同一个资源，这个时候如果已换成那个处理不合适，在第一个线程中抛出异常，
 *  其他线程就回进入同步代码区，有可能会访问到异常产生时的数据
 *  同步业务逻辑中的异常要多注意
 * @Author liang
 * @Date 2018/7/2 16:18
 * @Version 1.0
 **/
public class c_011 {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while(true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count =" + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count ==5){
                try{
               int i = 1/0;  //此处抛出异常，锁将被释放，要想不释放，这里进行catch，然后让循环继续     
                }catch (Exception e){
                   System.out.println("异常");
                }
                
            }


        }
    }

    public static void main(String [] args){
        c_011 c = new c_011();
        Runnable r = new Runnable(){

            @Override
            public void run() {
               c.m();
            }
        };
        new Thread(r,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r,"t2").start();
    }
}
