package com.example.springproject.demo.entity;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ZhangWeiWei
 * @date 2020/9/17 17:55
 * @description
 */
public class Greeting {

    @NotBlank
    private int id;

    @NotBlank
    @Size(min = 5,max = 15)
    private String content;

    @AssertFalse
    private boolean isShow;

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", isShow=" + isShow +
                '}';
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

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
