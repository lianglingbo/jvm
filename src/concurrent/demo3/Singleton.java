package concurrent.demo3;

import java.util.Arrays;

/**
 * @ClassName Singleton
 * @Description TODO 线程安全的单例模式
 * 不用加锁，也能实现懒加载
 * @Author liang
 * @Date 2018/7/3 17:23
 * @Version 1.0
 **/
public class Singleton {
    private Singleton(){
        System.out.println("singleton");
    }
    private static class Inner{
        private static Singleton s = new Singleton();
    }

    private static Singleton getSingle(){
        return Inner.s;
    }
    public static void main(String [] args){
        Thread[] ths = new Thread[200];
        for(int i=0;i<ths.length;i++){
            ths[i]=new Thread(() -> { Singleton.getSingle();});
        }
        Arrays.asList(ths).forEach(o -> o.start());
    }
}
