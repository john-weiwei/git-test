package com.example.springproject.demo.study;

import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/26
 * @Description
 */
public class ThreadSleep {

    private static class SleepDemo implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("sleep two seconds ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            Thread thread = new Thread(new SleepDemo());
//            thread.interrupt();
            thread.start();
            boolean bInterrupted = thread.isInterrupted();
            System.out.println(bInterrupted);
            System.out.println("执行完成！！！");
        }
    }
}
