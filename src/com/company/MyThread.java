package com.company;

import java.util.concurrent.Callable;

/**
 * Created by lion on 16/9/8.
 */
public class MyThread {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 =new Thread1();
        Thread ta = new Thread(t1,"A");  //这里的 new Thread里可以传入一个 实现了Runnable接口的对象,
        Thread tb =new Thread(t1,"B");
        ta.start();
        tb.start();

        ta.join();
        System.out.println("end");



    }
}
class Thread1 implements Runnable{

    @Override
    public void run() {
//        synchronized(this){
            for (int i = 0; i < 500; i++) {
                System.out.println(Thread.currentThread().getName()+"--synchronized loop"+i);

                /**
                //因为是2个,因此这个通知顺序好说
                this.notifyAll();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */

                /**
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */


                Thread.yield();//退让,给同等级的线程退让



                //当把notify放在wait()后果然死锁了,因为互相等通知。。。。当然对方先睡眠后没法通知了
                //而且这个notify 必须是握有锁的才有权利notify,因此

            }//for
//        }//syns

    }

}



