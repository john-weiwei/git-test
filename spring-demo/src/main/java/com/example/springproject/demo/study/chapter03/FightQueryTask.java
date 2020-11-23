package com.example.springproject.demo.study.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Create by ZhangWeiWei
 *
 * @Date 2020/10/27
 * @Description
 */
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;
    private final List<String> fightLines = new ArrayList<>();

    public FightQueryTask(String airLine,String origin, String destination) {
        super("["+airLine+"]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return this.fightLines;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n",getName(),origin,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightLines.add(getName()+"_"+randomVal);
            System.out.printf("The Fight:%s list query successful \n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
