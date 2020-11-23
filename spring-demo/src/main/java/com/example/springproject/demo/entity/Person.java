package com.example.springproject.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 15:46
 * @description
 */
@Data
public class Person {

    @NotBlank
    @Size(min = 1,max = 32)
    private String name;

    @Size(max = 11)
    private int age;

    @NotBlank
    @Size(max = 32)
    private String identityCard;
}
