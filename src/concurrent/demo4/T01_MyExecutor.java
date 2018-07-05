package concurrent.demo4;

import java.util.concurrent.Executor;

/**
 * @ClassName T01_MyExecutor
 * @Description TODO
 * @Author liang
 * @Date 2018/7/4 10:24
 * @Version 1.0
 **/
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
    public static void main(String [] args){
        new T01_MyExecutor().execute(() -> System.out.println("executor"));
    }
}
