package com.example.springproject.demo.study;

import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/26
 * @Description
 */
public class ThreadInterrupt {
    public static void main(String[] args)throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                //sleep是可中断方法，会捕获中断信号
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
        thread.start();
        //调用sleep，让线程阻塞
//        TimeUnit.SECONDS.sleep(1);
        //调用interrupt，中断线程阻塞
        thread.interrupt();
    }
}
