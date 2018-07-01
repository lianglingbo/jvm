package concurrent.demo;

/**
 * @ClassName c_003
 * @Description TODO 在静态方法上 不能用this对象加锁,static没有this,使用xxx.class
 * 这里锁定的不是整段代码.而是锁定this对象!!
 * @Author liang
 * @Date 2018/7/1 11:58
 * @Version 1.0
 **/
public class c_005 implements Runnable{
    private  int count = 10;
    public  /*synchronized*/ void n() {
          count --;
            System.out.println(Thread.currentThread().getName()+"count = "+count);

    }
    @Override
    public  void run() {
        n();
    }
    public static void main(String [] args){
        c_005 c = new c_005();
        for(int i=0;i<9;i++){
            new Thread(c,"thread : "+i).start();
        }
    }
}
