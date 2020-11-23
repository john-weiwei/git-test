package com.example.springproject.demo.entity.anntoattion;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangWeiWei
 * @date 2020/9/28 16:52
 * @description
 */
@Data
public class Person {

    @NotNull
    private String name;
}
