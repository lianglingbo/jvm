package concurrent.demo3.TicketSell;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Sell01
 * @Description TODO
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 写一个模拟程序
 *
 * 把容器换成同步的
 * 虽然remove操作是同步的，但是当判断完，还没remove的时候，可能有其他线程进来；
 * 
 * @Author liang
 * @Date 2018/7/3 17:38
 * @Version 1.0
 **/
public class Sell02 {
    static Vector<String> tickerts = new Vector<>();
    static{
        for(int i=0;i<10000;i++){
            tickerts.add("火车票号："+i);
        }
    }
    
    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while (tickerts.size()>0){
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了 ---"+tickerts.remove(0));
                }
            }).start();
        }
    }
}
