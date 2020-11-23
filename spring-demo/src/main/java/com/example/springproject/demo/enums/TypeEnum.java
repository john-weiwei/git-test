package com.example.springproject.demo.enums;

import com.example.springproject.demo.plugins.contraint.CheckCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangWeiWei
 * @date 2020/10/16 13:50
 * @description
 */
public enum TypeEnum implements ITypeEnum{
    FORBIDDEN {
        @Override
        public String addPoint() {
            return "point";
        }

        @Override
        public String addArticle() {
            return "article";
        }

        @Override
        public String addVote() {
            return "vote";
        }
    }

}
