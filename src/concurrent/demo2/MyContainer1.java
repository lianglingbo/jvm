package concurrent.demo2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyContainer1
 * @Description TODO
 * 固定容量同步器，有put，get，getCount方法
 * 如果容器满了：put方法就要wait
 * 如果容器空了：get方法就要wait
 * 能支持两个生产者线程以及10个消费者线程的阻塞调用
 * @Author liang
 * @Date 2018/7/3 15:06
 * @Version 1.0
 **/
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    synchronized void put(T t){
        /**
         * 为什么用while不用if
         * 如果用if，当容器满了，线程1wait，线程2拿到锁并且已经add了，
         * 就在这个时候，线程1醒了，从wait方法下面开始继续执行，这个时候不再执行if判断容器满不满，然后add后容器就爆了
         * 第一个线程醒来以后直接从wait下面开始执行，如果不用while就会漏了当它在wait时进来的线程的add
         * 如果用while，当程序要往下执行的时候，会再执行一遍，while是一直判断，if判断一次
         * 一般wait和while结合用
         */
        while (lists.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        /**
         * 这里用的是notifyAll而不是notify
         * 如果只notify了上面那个满了而去wait的线程，程序就gg了
         */
        this.notifyAll();//通知消费者线程进行消费
    }

    synchronized T get(){
        T t =null;
        while(lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;
    }

    public static void main(String [] args){
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(() ->{
                for(int j=0;j<5;j++){
                    System.out.println("消费掉了"+c.get());
                }
            },"消费线程 "+i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0;i<2;i++){
            new Thread(() -> {
                for(int j=0;j<25;j++){
                    c.put(Thread.currentThread().getName()+"的对象："+j);
                }
            },"生产线程 "+i).start();
        }

    }
}
