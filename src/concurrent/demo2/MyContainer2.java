package concurrent.demo2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyContainer2
 * @Description TODO
 * 使用lock和condition来是实现
 * condition的方式可以更加精准的指定哪些线程可以被唤醒
 *
 * @Author liang
 * @Date 2018/7/3 16:22
 * @Version 1.0
 **/
public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();
    void put(T t){
        try{
            lock.lock();
            while(lists.size()==10){
                producer.await();
            }
            lists.add(t);
            ++count;
            //指定消费者线程进行消费
            consumer.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    T get(){
        T t = null;
        try{
            lock.lock();
            while (lists.size()==0){
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            //通知生产者进行生产
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return  t;
    }
    
    public static void main(String [] args){
        MyContainer2<String> c = new MyContainer2<>();
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
