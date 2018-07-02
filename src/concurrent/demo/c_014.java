package concurrent.demo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName c_014
 * @Description TODO
 * 证明synchronized和Atom的效率
 * @Author liang
 * @Date 2018/7/2 17:59
 * @Version 1.0
 **/
public class c_014 {
    private AtomicInteger countA = new AtomicInteger(0);
    private int countB = 0;
    public void atom(){
        for(int i=0;i<1000000;i++){
            countA.incrementAndGet();
        }
    }
    public synchronized void m(){
        for(int i=0;i<1000000;i++){
            countB++;
        }
    }
    public static void main(String [] args){

        long startA = System.currentTimeMillis();
            runatom();
           // runsyn();
            long endA = System.currentTimeMillis();
        System.out.println("atom,time"+(endA-startA));

    }
    public static  void runatom(){
        c_014 c = new c_014();
        ArrayList<Thread> threadA = new ArrayList<Thread>();
        for(int i=0;i<100;i++){
            threadA.add(new Thread(c::atom));

        }
        threadA.forEach((o) -> o.start());
        threadA.forEach((o) ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
    public static void runsyn(){
        c_014 c = new c_014();
        ArrayList<Thread> threadB = new ArrayList<Thread>();
        for(int i=0;i<100;i++){
            threadB.add(new Thread(c::m));

        }

        threadB.forEach((o) -> o.start());
        threadB.forEach((o) ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
