package concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName c_015
 * @Description TODO
 *
 * 证明atom类的可见性
 * @Author liang
 * @Date 2018/7/2 18:31
 * @Version 1.0
 **/
public class c_015 {
    AtomicBoolean flag = new AtomicBoolean(true);
    void m(){
        while (flag.get()){

        }
        System.out.println("m end");
    }
    public static void main(String [] args){
        c_015 c = new c_015();
        new Thread(() -> c.m()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         c.flag.set(false);
    }
}
