package concurrent.demo;

/**
 * @ClassName c_002
 * @Description TODO 第一个例子中,为了锁住对象,每次都要new 一个Object,太麻烦,现在直接用this当前对象代替,synchronized锁定的是一个对象,不是代码块
 * @Author liang
 * @Date 2018/7/1 11:49
 * @Version 1.0
 **/
public class c_002 implements Runnable{
    private int count = 10;

    @Override
    public void run() {
        synchronized(this){
            count --;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }
    }
    public static void main(String [] args){
        c_002 c_002 = new c_002();
        Thread thread1 = new Thread(c_002);
         Thread thread2 = new Thread(c_002);
         Thread thread3 = new Thread(c_002);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
