package concurrent.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName T14_ParalleStreamAPI
 * @Description TODO
 * @Author liang
 * @Date 2018/7/4 16:19
 * @Version 1.0
 **/
public class T14_ParalleStreamAPI {
    public static void main(String [] args){
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for(int i=0;i<10000000;i++){
            nums.add(10000+r.nextInt(10000));
        }
        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        //使用parallel stream api
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParalleStreamAPI::isPrime);
        end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static boolean isPrime(int num){
        for(int i=2;i<=num/2;i++){
            if(num % i == 0){
                return false;
            }
         }
        return true;

    }
}
