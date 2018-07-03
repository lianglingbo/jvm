package concurrent.demo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_019
 * @Description TODO
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器，线程2现实监控元素的个数，当个数到5个时，线程2给出提示并提示结束
 * 加了volatile，线程2还不一定能实时读到内存区的值，线程1还要sleep一段时间才能实现，而且t2死循环很浪费cpu
 *
 * 使用wait和notify 必须先锁定，wait会释放锁，而notify不会释放锁
 * 注意：运用这种方法必须要保证t2先执行，也就是t2要先监听才可以
 *
 * 下面程序有个问题，t1唤醒t2的时候，t2并不会执行，因为notify不会释放锁，t2还是没拿到锁；notify完了后再wait，这样能实现，但是比较繁琐
 *
 * @Author liang
 * @Date 2018/7/3 9:54
 * @Version 1.0
 **/
public class c_019_1 {
    //添加volatile使t2能得到通知
   volatile ArrayList list = new ArrayList();
   void add(Object o){
       list.add(o);
   }
   int size(){
       return list.size();
   }

   public static void main(String [] args){
       c_019_1 c = new c_019_1();
       final Object lock = new Object();
       new Thread(() -> {

           synchronized (lock){
               System.out.println("t2 启动");
               if(c.size() != 5){
                   try {
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("t2 结束");
           //t2执行完了再把锁交还给他
           lock.notify();
           }

       }).start();
       try {
           TimeUnit.SECONDS.sleep(2);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       new Thread(() -> {
           System.out.println("t1 启动");
           synchronized (lock){
            for(int i=0;i<10;i++){
               c.add(new Object());
               System.out.println("add:"+i);
               if(c.size()==5){
                   lock.notify();
                   try {
                       //释放锁，锁给t2
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

           }
            System.out.println("t1 结束");
           }
      }).start();


   }
}
