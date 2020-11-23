package com.example.springproject.demo.plugins.contraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 16:09
 * @description
 * 自定义验证器
 */
public class MyCheckCase implements ConstraintValidator<CheckCase,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
