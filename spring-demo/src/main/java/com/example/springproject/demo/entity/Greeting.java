package com.example.springproject.demo.entity;

/**
 * @author ZhangWeiWei
 * @date 2020/9/17 17:55
 * @description
 */
public class Greeting {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
