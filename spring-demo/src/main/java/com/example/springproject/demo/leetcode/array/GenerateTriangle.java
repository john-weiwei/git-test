package com.example.springproject.demo.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/26
 * @Description:
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 递归，
 * 动态规划：记住求解过的子问题的解
 *
 */
public class GenerateTriangle {

    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> outsideArr = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> arr = new ArrayList<>(i+1);
            if (i <= 1) {
                for (int j = 0; j < i+1; j++) {
                    arr.add(1);
                }
            } else {
                for (int j = 0; j < i+1; j++) {
                    if (j == 0 || j == i) {
                        arr.add(1);
                    } else {
                        List<Integer> upArr = outsideArr.get(i-1);
                        int value = upArr.get(j-1) + upArr.get(j);
                        arr.add(value);
                    }
                }
            }
            outsideArr.add(arr);
        }
        return outsideArr;
    }

    private static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> outsideArr = new ArrayList<>(numRows);
        if (numRows == 0 ) {
            return outsideArr;
        }
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(1);
        outsideArr.add(arr1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> arr = new ArrayList<>(i+1);
            arr.add(1);
            if (i>1) {
                for (int j = 1; j < i; j++) {
                    arr.add(outsideArr.get(i-1).get(j-1) + outsideArr.get(i-1).get(j));
                }
            }
            arr.add(1);
            outsideArr.add(arr);
        }

        return outsideArr;
    }

    /**
     * 递归的三个点
     * 1、找到整个递归的结束条件
     * 2、找返回值
     * 3、一次递归如何操作
     * @param numRows
     * @return 递归的效率最高
     */
    private static List<List<Integer>> generate3(int numRows) {
        List<List<Integer>> outsideArr = new ArrayList<>(numRows);
        if (numRows == 0 ) {
            return outsideArr;
        }

        if (numRows == 1) {
            List<Integer> arr = new ArrayList<>();
            arr.add(1);
            outsideArr.add(arr);
            return outsideArr;
        }
        //递归操作
        outsideArr = generate3(numRows- 1);
        //递归返回值
        //新申请一个数组，第一元素和最后一个元素值为1
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        //上一行数组值
        List<Integer> preArr = outsideArr.get(numRows-2);
        for (int i = 1; i < numRows - 1; i++) {
            arr.add(preArr.get(i-1)+preArr.get(i));
        }
        arr.add(1);
        outsideArr.add(arr);
        return outsideArr;
    }

    /**
     * 动态规划
     * 记住已经求解过的子问题的解
     * @param numRows
     * @return
     */
    private static List<List<Integer>> generate4(int numRows) {
        List<List<Integer>> outsideArr = new ArrayList<>(numRows);
        if (numRows == 0 ) {
            return outsideArr;
        }
        outsideArr.add(new ArrayList<>());
        outsideArr.get(0).add(1);
        //迭代
        for (int i = 1; i < numRows; i++) {
            List<Integer> arr = new ArrayList<>();
            List<Integer> preArr = outsideArr.get(i-1);
            arr.add(1);
            for (int j = 1; j < i; j++) {
                arr.add(preArr.get(j-1) + preArr.get(j));
            }
            arr.add(1);
            outsideArr.add(arr);
        }

        return outsideArr;
    }

    /**
     * 1、先得到杨辉三角
     * 2、获取第n行数据
     * 或
     * 直接计算第n行数据
     * @return
     */
    private static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> outsideArr = new ArrayList<>(rowIndex);
        outsideArr.add(new ArrayList<>());
        outsideArr.get(0).add(1);
        //迭代
        for (int i = 1; i < rowIndex; i++) {
            List<Integer> arr = new ArrayList<>();
            List<Integer> preArr = outsideArr.get(i-1);
            arr.add(1);
            for (int j = 1; j < i; j++) {
                arr.add(preArr.get(j-1) + preArr.get(j));
            }
            arr.add(1);
            outsideArr.add(arr);
        }

        return outsideArr.get(rowIndex-1);
    }

    private static List<Integer> getRow2(int rowIndex) {
        List<Integer> preArr = new ArrayList<>();
        List<Integer> currArr = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            currArr = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == 1) {
                    currArr.add(1);
                } else {
                    currArr.add(preArr.get(j-1) + preArr.get(j));
                }
            }
            preArr = currArr;
        }
        return currArr;
    }
}
