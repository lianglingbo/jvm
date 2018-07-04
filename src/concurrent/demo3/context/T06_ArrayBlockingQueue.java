package concurrent.demo3.context;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T06_ArrayBlockingQueue
 * @Description TODO
 * 有界队列固定长度
 * @Author liang
 * @Date 2018/7/4 9:18
 * @Version 1.0
 **/
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();
    public static void main(String [] args) throws InterruptedException {
        for(int i =0;i<10;i++){
            try {
                strs.put("a"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //strs.poll();
        strs.add("sss");//存不进，报异常
        //strs.offer("ooo"); //存不进去，不报异常，通过返回值来判断是否加成功
        strs.offer("aaa",1,TimeUnit.SECONDS);//指定时间添加
        strs.put("aa");//满了会阻塞，等着
        System.out.println(strs);
    }
}
