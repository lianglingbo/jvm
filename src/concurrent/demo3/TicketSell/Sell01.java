package concurrent.demo3.TicketSell;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Sell01
 * @Description TODO
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 写一个模拟程序
 *
 * 此版本没有同步，线程不安全
 * 
 * @Author liang
 * @Date 2018/7/3 17:38
 * @Version 1.0
 **/
public class Sell01 {
    static List<String> tickerts = new ArrayList<>();
    static{
        for(int i=0;i<10000;i++){
            tickerts.add("火车票号："+i);
        }
    }
    
    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->{
                while (tickerts.size()>0){
                    System.out.println("销售了 ---"+tickerts.remove(0));
                }
            }).start();
        }
    }
}
