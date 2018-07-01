package concurrent.demo;

/**
 * @ClassName c_003
 * @Description TODO 在静态方法上 不能用this对象加锁,static没有this,使用xxx.class
 * 这里锁定的不是整段代码.而是锁定this对象!!
 * @Author liang
 * @Date 2018/7/1 11:58
 * @Version 1.0
 **/
public class c_004 implements Runnable{
    private static int count = 10;
    public static synchronized void n() {
      synchronized (c_004.class){
          count --;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
      }

    }
    @Override
    public  void run() {
        n();
    }
    public static void main(String [] args){
        c_004 c_004 = new c_004();
        Thread thread1 = new Thread(c_004);
         Thread thread2 = new Thread(c_004);
         Thread thread3 = new Thread(c_004);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
