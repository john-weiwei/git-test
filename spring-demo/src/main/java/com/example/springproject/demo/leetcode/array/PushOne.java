package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/24
 * @Description:
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 */
public class PushOne {
    private static int[] pushOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        int num = Integer.parseInt(sb.toString());
        num += 1;
        String strNum = String.valueOf(num);
        int len = strNum.length();
        int[] result = new int[len];
        for (int i = 0; i < strNum.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(strNum.charAt(i)));
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] digits = {1,2,3};
        int[] digits = {9,8,7,6,5,4,3,2,1,0};
        int[] result = pushOne(digits);
        for (int item: result
             ) {
            System.out.println(item);
        }
    }
}
