package jvm;

/**
 * @ClassName T01
 * @Description TODO
 * 前面的减号表示关闭
 * 关闭逃逸分析：         -XX:-DoEscapeAnalysis
 * 关闭标量替换：         -XX:-EliminateAllocations
 * 关闭线程本地内存：     -XX:-UseTLAB
 * 打印GC信息：          -XX:+PrintGC
 * @Author liang
 * @Date 2018/7/5 15:47
 * @Version 1.0
 **/
public class T01 {
    class User{
        int id;
        String name;
        
        User(int id,String name){
            this.id = id;
            this.name = name;
        }
    }
    
    void alloc(int i){
        new User(i,"name"+i);
    }
    
    public static void main(String [] args){
        T01 t =new T01();
        long start = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            t.alloc(i);

        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
}
