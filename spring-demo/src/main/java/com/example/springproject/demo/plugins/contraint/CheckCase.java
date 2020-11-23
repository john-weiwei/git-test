package com.example.springproject.demo.plugins.contraint;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 11:43
 * @description
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyCheckCase.class)
@Documented
public @interface CheckCase {

    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List{
        CheckCase[] value();
    }
}
