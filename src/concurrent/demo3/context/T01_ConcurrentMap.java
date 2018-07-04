package concurrent.demo3.context;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T01_ConcurrentMap
 * @Description TODO
 * 多线程并发的效率问题，看每种容器的执行时间
 * Hashtable : 708  ,往里加数据的时候要锁定整个对象
 * ConcurrentHashMap : 322  ，默认把容器分成16段，每次插入的时候只锁定启动一段，可以实现并发插入
 * ConcurrentSkipListMap : 474 ，高并发并且需要排序的情况下使用
 * HashMap  :  无响应
 * @Author liang
 * @Date 2018/7/3 19:17
 * @Version 1.0
 **/
public class T01_ConcurrentMap {
    public static void main(String [] args){
        //Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String> map = new ConcurrentSkipListMap<>();//跳表，插入效率高，有序
        Map<Object, Object> map = new HashMap<>();//Collections.synchronizedXXX 就实现了带锁的实现
        //TreeMap
        //Map<Object, Object> map = new Hashtable<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for(int i=0;i<ths.length;i++){
            ths[i]=new Thread(() -> {
                //100个线程，每个线程都往map里面put10000个随机数字符串
                for(int j=0;j<10000;j++){
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                }
                latch.countDown();
            });
        }
        //每个线程都启动
        Arrays.asList(ths).forEach(t -> t.start());
        try {
            //主线程在这等着，latch。countDown为0，就被调用
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
