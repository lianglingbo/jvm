package concurrent.demo;

/**
 * @ClassName c_018
 * @Description TODO
 * 不要以字符串常量作为锁定对象
 * 下面情况其实锁定的同一个对象
 * 但是如果用了其他类库，并且该类库中锁定了此字符串，就可能发生死锁阻塞，应该和该类库用了同一把锁
 * @Author liang
 * @Date 2018/7/3 9:43
 * @Version 1.0
 **/
public class c_018 {
    String s1 = "hello";
    String s2 = "hello";
    void m1(){
        synchronized (s1){
            while (true){
            System.out.println(Thread.currentThread().getName());
            }

        }
    }
    void m2(){
        synchronized (s2){
            while (true){
            System.out.println(Thread.currentThread().getName());
            }

        }
    }

    public static void main(String [] args){
        c_018 c = new c_018();
        new Thread(() -> c.m1()).start();
        new Thread(() -> c.m2()).start();
    }
}

