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
 *  两种情况
 *  1、数组中全部元素都是9，新数组长度需要+1
 *  2、所有元素不全为9
 */
public class PushOne {
    private static int[] pushOne(int[] digits) {
        int len = digits.length;
        for (int i = len-1; i >= 0 ; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        //如果继续往下执行，则数组中的所有元素都是9
        int[] result = new int[len + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
//        int[] digits = {1,2,3};
        int[] digits = {9,9,9};
//        int[] digits = {9,8,7,6,5,4,3,2,1,0};
        int[] result = pushOne(digits);
        for (int item: result
             ) {
            System.out.println(item);
        }
    }
}
