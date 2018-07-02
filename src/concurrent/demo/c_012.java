package concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_012
 * @Description TODO
 * volatile 关键字，使一个变量在多个线程间可见
 * A  B 线程都用到一个变量，java默认是让线程A保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都读到变量的修改值
 *
 * 在下面代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程工作区，在运行过程中直接使用这个copy，并不会每次都去读取堆内存，
 * 这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取rnning的值
 * volatile并不能保证多个线程共同修改running变量时带来的不一致问题，也就是说volatile不能替代synchronized
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * @Author liang
 * @Date 2018/7/2 16:43
 * @Version 1.0
 **/
public class c_012 {
    /*volatile*/
    boolean running = true;
    void m(){
        System.out.println("m start");
        while(running){
            System.out.println("running ...");
            //当这里打印了东西，线程可能会和堆进行通信，所有最后可能会修改状态；如果cpu有空闲，就会到堆内存中去刷一下
        }
        System.out.println("m end");
    }

    public static void main(String [] args){
        c_012 c = new c_012();
        new Thread(c::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.running = false ;
        System.out.println("修改了状态为false");
    }
}
