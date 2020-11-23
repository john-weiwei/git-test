package com.example.springproject.demo.study;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/22
 * @Description
 * yield和sleep的区别
 * 1、sleep会导致当前线程暂停指定的时间，没有CPU时间片的消耗
 * 2、yield只是对CPU调度器的一个提示，如果CPU调度器没有忽略这个提示，它会导致
 * 线程上下文的切换；
 * 3、sleep会使线程短暂block，在给定的时间内释放资源
 * 4、调用yield方法会使线程从RUNNABLE状态转换到RUNNING状态，如果CPU调度器没有忽略这个
 * 提示，会导致线程上下文切换
 * 5、sleep方法几乎百分之百的休眠制定的时间，而yield方法则不一定能保证
 * 6、一个线程sleep另一个线程interrupt会捕获得到中断信号，而yield不会
 */
public class ThreadYield {
    public static void main(String[] args) {
        //开三个线程
        IntStream.range(0,3).mapToObj(ThreadYield::create).forEach(Thread::start);
    }

    /**
     * 创建一个线程
     * @param index
     * @return
     */
    private static Thread create(int index) {
        return new Thread(()->{
            if (index == 0){

                /**
                 * yield只是一个提示，CPU调度器并不保证每次都能满足yield提示；
                 * 调用yield方法会使当前线程从RUNNING状态转为RUNNABLE状态
                 */
                Thread.yield();
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println(index);
        });
    }
}
