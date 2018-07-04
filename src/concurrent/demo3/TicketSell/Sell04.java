package concurrent.demo3.TicketSell;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName Sell01
 * @Description TODO
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 写一个模拟程序
 *
 * 使用队列，并发容器:Queue ConcurrentLinkedQueue
 * 效率高；
 * 现在的判断和操作也是分开的，为什么没有线程安全问题？
 * 因为我们在先取值，然后判断，如果判断为空直接break了，没有再对队列进行修改
 * 
 * @Author liang
 * @Date 2018/7/3 17:38
 * @Version 1.0
 **/
public class Sell04 {
    static Queue<String> tickerts = new ConcurrentLinkedQueue<>();
    static{
        for(int i=0;i<10000;i++){
            tickerts.add("火车票号："+i);
        }
    }
    
    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while (true){
                    String s = tickerts.poll();
                    if(s==null){
                        break;
                    }else {
                        System.out.println("销售了 ---"+ s);
                    }

                }
            }).start();
        }
    }
}
