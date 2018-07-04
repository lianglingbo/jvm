package concurrent.demo3.context;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName T02_CopyOnWriteList
 * @Description TODO
 * 写时复制容器
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境;
 * 每次写数据的时候，复制一份出来给线程读，这样多线程读就不需要加锁了
 * CopyOnWriteArrayList :3502
 * Vector :76
 * @Author liang
 * @Date 2018/7/3 20:01
 * @Version 1.0
 **/
public class T02_CopyOnWriteList {
    public static void main(String [] args){
        List<String> lists =
                new ArrayList<>(); //这个会出并发问题！
                //new Vector();
                //new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for(int i=0;i<ths.length;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        lists.add("a"+r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
            runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths){
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
