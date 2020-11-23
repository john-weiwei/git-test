package com.example.springproject.demo.plugins;

import org.springframework.stereotype.Component;

import javax.validation.MessageInterpolator;
import java.util.Locale;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 11:07
 * @description
 */
@Component
public class CustomMessageInterpolator implements MessageInterpolator {
    @Override
    public String interpolate(String messageTemplate, Context context) {
        return null;
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return null;
    }
}
