package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/3
 * @Description:
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class RemoveElement {
    //输入的数据通常会被输出的部分覆盖
    private static int removeElement(int[] nums,int val) {
        int tempIndex = 0;
<<<<<<< HEAD
        int size = nums.length;
        while (tempIndex < size) {
            if (nums[tempIndex] == val) {
                nums[tempIndex] = nums[size-1];
                size--;
            } else {
                tempIndex++;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
        return tempIndex;
    }

    public static int removeElementBest(int[] nums,int val) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[result] = nums[i];
                result++;
            }
        }
        for (int i = 0; i < result; i++) {
            System.out.println(nums[i]);
        }
        return result;
=======
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                tempIndex++;
            } else {
                nums[i] = nums[tempIndex+1];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        return tempIndex-1;
>>>>>>> a25d19730c05c8b28d3d59f12440172853930829
    }

    public static void main(String[] args) {
//        int[] nums = {3,2,2,3};
        int[] nums = {0,1,2,2,3,0,4,2};
<<<<<<< HEAD
//        int result = removeElement(nums,2);
        int result = removeElementBest(nums,2);


=======
        int result = removeElement(nums,2);
>>>>>>> a25d19730c05c8b28d3d59f12440172853930829
        System.out.println("输出转换后的长度："+result);
    }
}
