package concurrent.demo;

/**
 * @ClassName c_006
 * @Description TODO 同步方法执行的过程中,非同步方法是可以同时执行的;
 * 同理,锁的对象不一样,也是可以互相穿插的
 * @Author liang
 * @Date 2018/7/1 12:45
 * @Version 1.0
 **/
public class c_006 {
    public synchronized void m1()  {
        System.out.println(Thread.currentThread().getName()+"m1 start ...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end ...");
    }
   private Object o = new Object();
    public   void m2() {
        synchronized (o) {


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "m2 ");

        }
    }
    public static void main(String [] args){
        c_006 c = new c_006();
        new Thread (() -> c.m1(),"c1").start();
        new Thread (() -> c.m2(),"c2").start();

    }
}
