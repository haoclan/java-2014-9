package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by lion on 16/10/18.
 */

public class MyDelayQueue {

public static void main(String [] args){
    Random rand = new Random(47);
    ExecutorService exec = Executors.newCachedThreadPool();
    DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();

    for (int i = 0; i < 20 ; i++) {
        queue.put(new DelayedTask(rand.nextInt(5000)));
    }
    queue.add(new DelayedTask.EndSentinel(5000,exec));
    //???queue.add  queue.put (类似于有了顺序了吧)
    exec.execute(new DelayedTaskConsumer(queue)); //通常里面是具有runnable的类的对象,就直接多线程了

}

}//主类


class DelayedTask implements Runnable,Delayed{
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;  //延迟毫秒  这样写能触发自动构造函数的代码补全
    private final long trigger;//触发的时刻,纳秒
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();



    public DelayedTask(int delta  ) {
        this.delta = delta;
        this.trigger = System.nanoTime()+NANOSECONDS.convert(delta,MILLISECONDS);
        sequence.add(this);//使用构造函数,将自己这个对象加入到类的静态队列
    }

    //这函数式Delayerd ,是当前剩余时刻,当变为0的时候就执行
    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println(id+" "+delta+"执行了一次getDelay, 其getDelay值是"+unit.convert(trigger - System.nanoTime(),NANOSECONDS));//也就是说这个函数是一直在执行,但是并不是unity那样的无脑扫描,从结果看像是通知的方式。

        return unit.convert(trigger - System.nanoTime(),NANOSECONDS);


    }

    @Override
    public int compareTo(Delayed o)  //因为DelayQueue中有方法是直接取一个延迟等待的
    {
        DelayedTask that =  (DelayedTask) o;
        if(trigger<that.trigger) return -1;//谁的时间短,谁放前面?
        if(trigger>that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {

        System.out.println("单个任务的run"+this + " ");//这里会调用toString()
    }

    @Override
    public String toString()//输出此任务的原本定时与任务id
    {
        return String.format("[%1$-4d] ",delta)+": Task "+id; // 4个位宽 第一个参数delta随机的参数
    }

    public String summary() //任务id与延迟
    {
        return "(" +id +":"+ delta +")";
    }

    //静态内部类 sentinel哨兵
    public static class EndSentinel extends DelayedTask{
        private ExecutorService exec;

        public EndSentinel(int delta,ExecutorService e) {
            super(delta);
            exec =e;
        }
        public void run()//重写了父类的run方法,父类中run方法只写了一个this
        {
            for (DelayedTask pt :sequence
                 ) {
                System.out.print(pt.summary()+" ");
            }
            System.out.println();
            System.out.println(this+"Calling shutdownNow()");
            exec.shutdownNow();//这个东西继承了DelayedTask,因此也是具体的任务,但是它的执行就是关闭最外部的那个线程池
        }

    }

}

class DelayedTaskConsumer implements Runnable
{
    private DelayQueue<DelayedTask> q;//主角在这里

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
        this.q = q;
    }

    @Override
    public void run() {
        try {
            System.out.println("消费者执行了run");
            while (!Thread.interrupted())
            {
                q.take().run();//这是使用这个消费者的线程去执行这个任务
                System.out.println("一直从q中取");
            }

        }catch (InterruptedException e)
        {
            System.out.println("中断");
        }
        System.out.println("Finished DelayedTaskConsumer");

    }
}
