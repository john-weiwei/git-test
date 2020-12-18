package com.example.springproject.demo.entity.anntoattion;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangWeiWei
 * @date 2020/9/28 16:51
 * @description 支持级联验证
 */
@Data
public class Car {

    @NotNull
    @Valid
    private Person driver;

    //验证容器对象不能为空
    private List<@NotNull @Valid Person> passengers = new ArrayList<>();

    public Car(@NotNull @Valid Person driver) {
        this.driver = driver;
    }

    public static void rentCar(@NotNull @FutureOrPresent Date startDate, @Min(1) int durationInDays,@NotNull String value) {
        long startTime = startDate.getTime();
        System.out.println("aa"+startTime+value);
    }

    public static void main(String[] args) {
        rentCar(new Date(),0,null);
    }
}
