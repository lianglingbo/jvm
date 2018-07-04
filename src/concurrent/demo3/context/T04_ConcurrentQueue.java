package concurrent.demo3.context;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName T04_ConcurrentQueue
 * @Description TODO
 * 重要
 * @Author liang
 * @Date 2018/7/3 20:35
 * @Version 1.0
 **/
public class T04_ConcurrentQueue {
    public static void main(String [] args){
        //无界队列，直到内存耗完
        ConcurrentLinkedQueue<String> strs = new ConcurrentLinkedQueue<>();
        for(int i=0;i<10;i++){
            //类似于add，add会有容量的限制，offer不会有，会返回一个Boolean值
            strs.offer("a"+i);
        }
        System.out.println(strs);
        System.out.println(strs.size());

        //拿走第一个，返回
        System.out.println(strs.poll());
        System.out.println(strs.size());
        //只读取，不删除第一个
        System.out.println(strs.peek());
        System.out.println(strs.size());
    }
}
