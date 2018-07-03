package concurrent.demo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName c_019
 * @Description TODO
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器，线程2现实监控元素的个数，当个数到5个时，线程2给出提示并提示结束
 * 加了volatile，线程2还不一定能实时读到内存区的值，线程1还要sleep一段时间才能实现，而且t2死循环很浪费cpu
 * @Author liang
 * @Date 2018/7/3 9:54
 * @Version 1.0
 **/
public class c_019 {
   /*volatile*/ ArrayList list = new ArrayList();
   void add(Object o){
       list.add(o);
   }
   int size(){
       return list.size();
   }

   public static void main(String [] args){
       c_019 c = new c_019();
       new Thread(() -> {
           for(int i=0;i<10;i++){
               c.add(new Object());
               System.out.println("add:"+i);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();

       new Thread(() -> {
           while(true){
               if(c.size()==5){
                   break;
               }
           }
           System.out.println(Thread.currentThread().getName()+" 结束");
       }).start();
   }
}
