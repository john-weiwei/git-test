package com.example.springproject.demo.entity;

import org.springframework.lang.Nullable;

import javax.validation.constraints.AssertTrue;

/**
 * @author ZhangWeiWei
 * @date 2020/9/25 16:35
 * @description
 */

public class Hello {

    private String name;
    private String value;
    private boolean isMale;

    @AssertTrue
    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
