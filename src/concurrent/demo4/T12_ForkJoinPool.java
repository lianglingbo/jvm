package concurrent.demo4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName T12_ForkJoinPool
 * @Description TODO
 * 类似于MapReduce
 * 将大任务分成小任务执行，最后合并到一起
 * 一个递归的task，由forkjoinPoll来维护
 * @Author liang
 * @Date 2018/7/4 14:37
 * @Version 1.0
 **/
public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0;i<nums.length;i++){
            nums[i] = r.nextInt(100);
        }
        //java8 新特性， stream api 直接把nums数组里的值加起来
        System.out.println(Arrays.stream(nums).sum());
    }

    //没有返回值
    static class AddTask extends RecursiveAction{

        int start ,end;
        AddTask(int s ,int e){
            start=s;
            end=e;
        }
        //把任务切一半，如果小于MAX_NUM就执行，否则就继续切  ，递归
        @Override
        protected void compute() {
            if(end-start <= MAX_NUM){
                long sum=0L;
                for(int i=start;i<end;i++){
                    sum +=nums[i];
                }
                System.out.println("from"+start+" to "+end+" = "+sum);
            }else {
                int middle = start+(end-start)/2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }
    //有返回值
    static class AddTask2 extends RecursiveTask<Long>{
        int start ,end;
        AddTask2(int s ,int e){
            start=s;
            end=e;
        }
        //把任务切一半，如果小于MAX_NUM就执行，否则就继续切  ，递归
        @Override
        protected Long compute() {
            if(end-start <= MAX_NUM){
                long sum=0L;
                for(int i=start;i<end;i++){
                    sum +=nums[i];
                }
               return sum;
            }
                int middle = start+(end-start)/2;
                AddTask2 subTask21 = new AddTask2(start, middle);
                AddTask2 subTask22 = new AddTask2(middle, end);
                subTask21.fork();
                subTask22.fork();
                return subTask21.join() + subTask22.join();

        }
    }

    public static void main(String [] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        //AddTask task = new AddTask(0, nums.length);
        AddTask2 task = new AddTask2(0, nums.length);

        fjp.execute(task);
        Long result = task.join();
        System.out.println(result);
        System.in.read();
    }
}
