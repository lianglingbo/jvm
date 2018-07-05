package concurrent.demo4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName T04_Executors
 * @Description TODO
 * 一个executor的工具类
 * @Author liang
 * @Date 2018/7/4 10:59
 * @Version 1.0
 **/
public class T04_Executors {

    public static void main(String [] args){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    }
}
