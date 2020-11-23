package com.example.springproject.demo.study;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/27
 * @Description
 */
public class ThreadJoin {
    private static ThreadJoin ourInstance = new ThreadJoin();

    public static ThreadJoin getInstance() {
        return ourInstance;
    }

    private ThreadJoin() {
    }

    public static void main(String[] args) throws InterruptedException {
        //定义两个线程存放在List中
        List<Thread> threadList = IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
        //开启这两个线程
        threadList.forEach(Thread::start);
        //执行这两个线程的join方法
        for (Thread thread: threadList
             ) {
            thread.join();
        }
        //主线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"#"+i);
            ShortSleep();
        }
    }

    private static Thread create(int seq) {
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"#"+i);
                ShortSleep();
            }
        },String.valueOf(seq));
    }

    private static void ShortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
