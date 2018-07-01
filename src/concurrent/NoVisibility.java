package concurrent;

/**
 * @ClassName NoVisibility
 * @Description TODO 测试不可见性，重排序
 * @Author liang
 * @Date 2018/5/17 17:29
 * @Version 1.0
 **/
public class NoVisibility {

    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        public void run(){
            while(ready){
                Thread.yield();
                System.out.println("number:"+number);
            }
        }
    }
    public static void main(String [] args){
        new ReaderThread().start();
        number=43;
        ready=true;
    }
}
