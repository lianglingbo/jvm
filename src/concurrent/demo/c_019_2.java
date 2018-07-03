package concurrent.demo;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
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
 * 使用Latch替代wait和notify来进行通知
 * 好处是通信简单，能指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，不需要锁定，当count的值为0时，当前线程继续运行
 * 当不涉及同步，只涉及线程通信的时候，用synchronized + wait、notify就太繁琐
 * 这是就应该考虑countdownlatch、cyclicbarrier，semapore
 * @Author liang
 * @Date 2018/7/3 9:54
 * @Version 1.0
 **/
public class c_019_2 {
    //添加volatile使t2能得到通知
   volatile ArrayList list = new ArrayList();
   void add(Object o){
       list.add(o);
   }
   int size(){
       return list.size();
   }

   public static void main(String [] args){
       c_019_2 c = new c_019_2();
       CountDownLatch latch = new CountDownLatch(1);
       new Thread(() -> {
           System.out.println("t2 启动");
           if(c.size() != 5){
               try {
                   latch.await();
                   //也可指定等待时间
//                   latch.await(5000,TimeUnit.MICROSECONDS);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           System.out.println("t2 结束");
       }).start();

       try {
           TimeUnit.SECONDS.sleep(2);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       new Thread(() -> {
           System.out.println("t1 启动");
           for(int i=0;i<10;i++){
               c.add(new Object());
               System.out.println("add "+i);
               if(c.size() == 5){
                   //打开门闩,让t2得以执行
                   latch.countDown();
               }
           }
       }).start();



   }
}
