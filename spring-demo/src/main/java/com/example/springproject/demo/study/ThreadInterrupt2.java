package com.example.springproject.demo.study;

import javax.validation.OverridesAttribute;
import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/26
 * @Description
 */
public class ThreadInterrupt2 {
    public static void main(String[] args)throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        System.out.println("is interrupted："+isInterrupted());
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("thread is interrupted："+thread.isInterrupted());
        thread.interrupt();//捕获到中断信号之后，会擦除中断标记位
        TimeUnit.SECONDS.sleep(1);
        System.out.println("thread is interrupted："+thread.isInterrupted());

    }
}
