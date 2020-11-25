package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/19
 * @Description:
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 使用二分查找法，边界值问题
 */
public class SearchInsert {

    public static int searchInsert(int[] nums,int target) {
        int left = 0;
        int right = nums.length -1;
        //边界值[left,right],left可以等于right
        while (left <= right) {
            //求中间值
            int middle = left + ((right-left)/2);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return right+1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 0;
        int result = searchInsert(nums,target);
        System.out.println(result);
    }
}
