package com.example.springproject.demo.study.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/27
 * @Description
 */
public class FightQueryExample {

    private static List<String> fightComoany = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> result = search("SH","BJ");
        System.out.println("===============result==============");
        result.forEach(System.out::println);
    }

    private static List<String> search(String origin,String destination) {
        final List<String> result = new ArrayList<>();
        //每条航线都用单独的线程去查询
        List<FightQueryTask> tasks = fightComoany.stream().map(f->createQueryTask(f,origin,destination)).collect(Collectors.toList());
        //启动线程
        tasks.forEach(Thread::start);
        //调用join方法
        tasks.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //主线程会阻塞，获取每个线程的执行结果存放到result中
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;

    }

    private static FightQueryTask createQueryTask(String airLine,String origin,String destination) {
        return new FightQueryTask(airLine,origin,destination);
    }
}
