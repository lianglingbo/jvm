package concurrent.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName T07_ParallelComputing
 * @Description TODO
 * 并行计算:计算1到20万中间所有的质数
 *
 * @Author liang
 * @Date 2018/7/4 13:22
 * @Version 1.0
 **/
public class T07_ParallelComputing {
    public static void main(String [] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println("运行时间："+(end-start));
        final int cpuCoreNum=4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask t1 = new MyTask(1,80000);
        MyTask t2 = new MyTask(80001,130000);
        MyTask t3 = new MyTask(130001,170000);
        MyTask t4 = new MyTask(170001,200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println("并发运行时间："+(end-start));

    }

    static class MyTask implements Callable<List<Integer>> {

        int startPos,endPos;
        MyTask(int s,int e){
            this.startPos=s;
            this.endPos=e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos,endPos);
            return r;
        }
    }
    static boolean isPrime(int num){
        for(int i=2;i<=num/2;i++){
            if(num % i==0){
                return false;
            }
        }
        return true;
    }
    static List<Integer> getPrime(int start,int end){
        List<Integer> result = new ArrayList<>();
        for(int i=start;i<=end;i++){
            if(isPrime(i)){
                result.add(i);
            }
        }
        return result;
    }
}
