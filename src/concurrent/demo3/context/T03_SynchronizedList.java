package concurrent.demo3.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName T03_SynchronizedList
 * @Description TODO
 * Collections.synchronizedXXX
 * 给没有锁的集合加锁工具类
 *
 * @Author liang
 * @Date 2018/7/3 20:28
 * @Version 1.0
 **/
public class T03_SynchronizedList {
    public static void main(String [] args){
        ArrayList<String> strs = new ArrayList<>();
        List<String> strsSync = Collections.synchronizedList(strs);
    }
}
