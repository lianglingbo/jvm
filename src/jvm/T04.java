package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T04
 * @Description TODO
 * 内存文件的查看工具visualVM
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C: -XX:+PrintGCDetails -Xms10M -Xmx10M
 * 内存溢出时，把内存信息dump出来；存放位置；打印gc详细信息；程序起始时分配的堆内存-最大给程序分配的堆内存（调优时配成相等，效率高）
 * m：memory
 * s：start
 *
 * @Author liang
 * @Date 2018/7/6 13:49
 * @Version 1.0
 **/
public class T04 {
    public static void main(String [] args){
        List<Object> lists = new ArrayList<>();
        for(int i=0;i<100000000;i++){
            lists.add(new byte[1024*1024]);
        }
    }
}
