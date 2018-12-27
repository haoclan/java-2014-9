package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lion on 16/9/25.
 */
public class MyCountDownLatch {

    static final int SIZE = 100;


    public static void main(String [] args)
    {

        //CountDownLatch
        // 一个同步辅助类,在完成一组正在其他线程中执行的操作前,允许一个或多个线程一直等待。用给定
        //的计数初始化 如果计数器被重置参考 CyclicBarrier


        //主线程中调用CountDownLatch的await方法(一直阻塞),让各个线程调用
        //在这样情况下尤其适用：将一个任务分成若干线程执行，等到所有线程执行完，再进行汇总处理。
        //Sun大神终于为我们这些可怜的小程序员退出了java.util.concurrent工具包,减少竞争条件和死锁线程。

        ExecutorService exec = Executors.newCachedThreadPool();//线程池 这个生成的模式,工厂模式?

        CountDownLatch latch = new CountDownLatch(SIZE);//这就是一个同步锁,里面的有条件的数量,到0才能触发条件


        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new TaskPortion(latch));
        }

        System.out.println("Launched all tasks");
        exec.shutdown();

        //Thread.interrupted();
        //Thread th = new Thread();


    }
}

class TaskPortion implements  Runnable{
    private static int counter =0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {

            dowork();
            latch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void dowork() throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(rand.nextInt(20000));
        //Thread.sleep(rand.nextInt(20000));
        System.out.println(this+"completed");

        // 这2个sleep的区别,其实就是TimeUnit.具体单位  更方便了


    }

    public String toString() {
        return String.format("%1$-3d",id);//将int转为 String
    }

}


class WaitingTask implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;


    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {
        try {

            latch.await(); //等待锁到0 为止
            System.out.println("Latch barrier passed for"+this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String toString()
    {
        return String.format("WaitingTask %1$-3d",id);
    }

}


/**
 总结:

 */
