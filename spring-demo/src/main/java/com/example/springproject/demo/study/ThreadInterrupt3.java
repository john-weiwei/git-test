package com.example.springproject.demo.study;

import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/26
 * @Description
 */
public class ThreadInterrupt3 {
    public static void main(String[] args)throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    System.out.println("interrupted:"+Thread.interrupted());
                }
            }
        };
//        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
