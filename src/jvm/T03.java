package jvm;

/**
 * @ClassName T03
 * @Description TODO
 * 通过runtime类计算大致内存情况
 *
 * @Author liang
 * @Date 2018/7/6 13:45
 * @Version 1.0
 **/
public class T03 {
    public static void main(String [] args){
        printMemoryInfo();
        byte[] b = new byte[1024*1024*1024];
        System.out.println("=============");
        printMemoryInfo();
    }

    static void printMemoryInfo(){
        System.out.println("total:"+Runtime.getRuntime().totalMemory());
        System.out.println("free:"+Runtime.getRuntime().freeMemory());
    }
}
