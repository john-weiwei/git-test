package com.example.springproject.demo.study;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/26
 * @Description
 */
public class ThreadInterrupt4 {
    public static void main(String[] args) {
        System.out.println("Main thread is interrupted?"+Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("Main thread is interrupted?"+Thread.currentThread().interrupted());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still");
        }
    }
}
