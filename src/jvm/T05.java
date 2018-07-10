package jvm;

/**
 * @ClassName T05
 * @Description TODO
 * 线程栈大小
 * -Xss 128k
 * 值小：线程并发数量可以多
 * 值大：线程递归更深
 * stack
 * start
 * stackOverFlow
 * @Author liang
 * @Date 2018/7/6 14:07
 * @Version 1.0
 **/
public class T05 {
    static int count = 0;
    static void r(){
        count++;
       // byte[] b =new byte[1024*1024];
        r();
    }
    public static void main(String [] args){
        try{
            r();
        }catch (Throwable t){
            System.out.println(count);
            t.printStackTrace();
        }
    }
}
