package com.company;

import java.util.Random;

/**
 * Created by lion on 16/10/17.

 这个例子来说明线程不安全的情况,虽然使用了volatile关键字
 错误使用。因为没有锁的支持，volatile的修改不能依赖于当前值，当前值可能在其他线程中被修改

 主要是 用实际例子来说明 使用锁和不使用锁, 的线程安全问题。

 */
public class MyconcurrentTestvolatile {
    public volatile static int count =0;//volatile
    public static Object obj =new Object();

    public static void inc() throws InterruptedException {
        Thread.sleep(new Random(47).nextInt(2000));

        //加一个锁试试?
        //synchronized (obj) {
            count++;
            //obj.notifyAll();  //去通知那些wait阻塞的线程
            // obj.wait();
        //}

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MyconcurrentTestvolatile.inc();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        //这里是因为没有返回就去读取?
        Thread.sleep(2000);
        System.out.println(MyconcurrentTestvolatile.count);

    }

}
