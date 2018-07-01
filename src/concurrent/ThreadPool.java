package concurrent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description TODO 线程池类，线程管理，创建线程，执行线程，销毁线程，获取线程基本信息
 * @Author liang
 * @Date 2018/5/16  10:37
 * @Version 1.0
 **/
public final class ThreadPool {
    //默认线程个数为5
    private static int worker_num = 5;
    //工作线程
    private WorkThread[] workThreads;
    //未处理线程
    private static volatile int finished_task = 0;
    //任务队列，作为一个缓冲，list线程不安全
    private List<Runnable> taskQueue = new LinkedList<Runnable>();
    private  static ThreadPool threadPool;
    //创建具有默认线程个数的线程池
    private ThreadPool(){
        this(5);
    }
    //创建线程池，worker_num为线程池中工作线程的个数
    private ThreadPool(int worker_num){
        ThreadPool.worker_num = worker_num;
        workThreads = new WorkThread[worker_num];
        for(int i = 0;i < worker_num; i++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();//开启线程池中的线程
        }
    }
    //单例模式，获得一个默认线程个数的线程池
    public static ThreadPool getThreadPool(){
        return getThreadPool(ThreadPool.worker_num);
    }
    //单例模式，获得一个默认线程个数的线程池，worker_num大于0的工作线程个数
    public static ThreadPool getThreadPool(int worker_num1){
        if(worker_num1 <= 0){
            worker_num1 = ThreadPool.worker_num;
        }
        if(threadPool == null){
            threadPool = new ThreadPool(worker_num1);
        }
        return threadPool;
    }

    //批量执行任务，其实只是把任务加入到任务队列中，具体由线程池管理器决定
    public void execute(Runnable[] task){
        synchronized (taskQueue){
            for (Runnable t:task) {
                taskQueue.add(t);
                taskQueue.notify();
            }
        }
    }

    //批量执行任务,只是加载到队列中
    public void execute(List<Runnable> task){
        synchronized (taskQueue){
            for(Runnable t : task){
                taskQueue.add(t);
                taskQueue.notify();
            }
        }
    }

    //销毁线程池
    public void destroy(){
        while (!taskQueue.isEmpty()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //工作线程停止工作，设为null
        for(int i = 0;i < worker_num ; i++){
            workThreads[i].stopWorker();
            workThreads[i]=null;
        }
        threadPool=null;
        taskQueue.clear();//清空任务队列
    }
    //返回工作线程个数
    public int getWorkThreadNumber(){
        return worker_num;
    }
    //返回已完成任务的个数，
    public int getFinishedTaskNumber(){
        return finished_task;
    }
    //返回队列的长度，还没处理的任务
    public int getWaitTaskNumber(){
        return taskQueue.size();
    }

    //覆盖tostring方法，返回线程池信息，工作线程池个数和完成任务个数
    @Override
    public String toString() {
        return "ThreadPool{" +
                "workThreads=" + Arrays.toString(workThreads) +
                ", taskQueue=" + taskQueue +
                '}';
    }

    /**
     * 内部类，工作线程
     */
    private class WorkThread extends Thread{
        //该工作线程是否有效，用于结束该工作线程
        private boolean isRunning = true;
        /**
         * !!如果队列不为空，则取出任务执行，为空，等待
         */
        @Override
        public void run(){
            Runnable r = null;
            while (isRunning){//若线程无效则自然结束run方法，该线程就没用了
                synchronized (taskQueue){
                    while (isRunning && taskQueue.isEmpty()){//队列为空
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!taskQueue.isEmpty()){
                        r = taskQueue.remove(0);//取出任务
                    }
                }
                if(r!=null){
                    r.run();//执行任务。
                }
                finished_task++;
                r=null;
            }
        }
        //停止工作，让该线程自然执行完run方法，自然结束
        public void stopWorker(){
            isRunning=false;

        }
    }
}
