package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/24
 * @Description: 最大子序列表
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 动态规划
 */
public class MaxSubArray {

    //动态规划
    private static int maxArray(int[] nums) {
        int sum = 0;
        int first = nums[0];
        for (Integer item: nums
             ) {
            if (sum > 0) {
                sum += item;
            } else {
                //舍弃前i个和小于0的子序列
                sum = item;
            }
            first = Math.max(sum,first);
        }
        return first;
    }

    //动态规划
    private static int dynamicMaxArray(int[] nums) {

        //递归

        //备忘录（自顶向下）

        //自底向上

        return 0;
    }

    public static void main(String[] args) {

    }
}
