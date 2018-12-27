package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by lion on 16/10/18.
 */

// HorseRace
public class MycyclicBarrier {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public MycyclicBarrier(int nHorses, final int pause)
    {
        //注意这个CyclicBarrier的构造方法!!!
        //CyclicBarrier要规定 一共有多少个并发的个体,以及第2个参数 规定了NPC线程每一步要执行的任务,NPC的休息保证了同步问题的时间间隔
        //也就是每个子线程中 先循环调用到 barrier.await(),这样来一遍后,NPC执行一次run(),然后继续
        //因此叫做CyclicBarrier  最终的停止是在barrier的NPC线程中手工判断,然后执行线程池的立刻关闭

        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {

                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++)
                    s.append("=");
                System.out.println(s);

                for (Horse horse : horses)
                    System.out.println(horse.tracks());//输出当前马的这个值

                for (Horse horse : horses)
                    if(horse.getStrides() >= FINISH_LINE)
                    {System.out.println(horse+"won!");
//                        exec.shutdown();//我错在这里 了!!!没有立刻关闭
                        exec.shutdownNow();
                        return;//主线恒关闭
                    }

//
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(pause);//不断循环
//                    } catch (InterruptedException e) {
//                       //如果有中断发生
//                        System.out.println("barier-action sleep interrupted");
//                    }
                    System.out.println("NPC执行了一次");
            }//run
        });//构造barrier 实际上是单独写了一个  新的线程执行逻辑

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);//各个线程传入相同的barrier是对的。
            horses.add(horse);
            exec.execute(horse);
        }
    }//此类构造函数

    public static void main(String [] args)
    {
        int nHorses= 7;
        int pause =2;
        if(args.length > 0){
            int n = new Integer(args[0]);
            nHorses = n> 0?n:nHorses;
        }
        if(args.length>1){
            int p = new Integer(args[1]);
            pause = p >-1?p:pause;
        }
        //以上都是为了获取参数

        new MycyclicBarrier(nHorses,pause);//相当于是写了个外层类的构造函数来执行所有 的东西。
    }

}

class Horse implements Runnable{
    private static int counter =0;
    private final int id =counter++;
    private int strides = 0;
    private static Random rand= new Random(47);
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier b) //每次构造对象都会重新重置那个静态的 cyclibarrier
    {
        barrier = b;
    }
    public synchronized int getStrides(){
        return strides;
    }

    @Override
    public void run() //实际上就是永远地奔跑,int++,每次加加后就阻塞等待其他,前提是不中断
    {
        try {
        while (!Thread.interrupted())
            {
            synchronized (this) {
                strides += rand.nextInt(3);
                System.out.println(id+"执行了自+");
            }

            barrier.await();//通过此对象让线程阻塞
            }


        } catch (InterruptedException e) {
            //中止后做的事情
            System.out.println("Thread"+id+"中断啦");

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String toString()
    {
        return "Horse"+ id +" ";
    }
    public String tracks()
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }

    }
