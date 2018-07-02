package concurrent.demo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName c_013
 * @Description TODO
 * volatile并不能保证多个线程共同修改running变量所带来的不一致问题，也就是说volatile不能替代synchronized
 * 解决这类问题最高效的方法是使用Atomxxx类
 * AtomXXX类本省方法都是原子性的，
 * 但不能保证多个方法连续调用是原子性的:
 *  一行代码具有原子性，两行代码，在两行代码之间，可能会有其他线程插进来，导致执行结果不一致
 * @Author liang
 * @Date 2018/7/2 17:25
 * @Version 1.0
 **/
public class c_013 {
    //volatile int count = 0;
     AtomicInteger count = new AtomicInteger(0);
    /*synchronized*/ void  m(){
        //System.out.println("线程"+Thread.currentThread().getName()+"进来了，初始count为："+count);
        for(int i =0;i<1000;i++){
            //count++;
            count.incrementAndGet();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"进来了,最后count值为："+count);
    }

    public static void main(String [] args){
        c_013 c = new c_013();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++){
            threads.add(new Thread(c::m,"thread-"+i));
        }
        threads.forEach((o) -> o.start());
        threads.forEach((o) ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(c.count);
    }
}
