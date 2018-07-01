package concurrent.demo;

/**
 * @ClassName c_003
 * @Description TODO 如果一段代码在开始的时候就要用this锁,那就可以在方法申明上写
 * 这里锁定的不是整段代码.而是锁定this对象!!
 * @Author liang
 * @Date 2018/7/1 11:58
 * @Version 1.0
 **/
public class c_003 implements Runnable{
    private int count = 10;

    @Override
    public synchronized void run() {
            count --;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
    }
    public static void main(String [] args){
        c_003 c_003 = new c_003();
        Thread thread1 = new Thread(c_003);
         Thread thread2 = new Thread(c_003);
         Thread thread3 = new Thread(c_003);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
