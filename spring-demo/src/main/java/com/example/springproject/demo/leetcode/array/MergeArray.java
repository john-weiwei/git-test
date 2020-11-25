package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/25
 * @Description: 合并两个有序的数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明：
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 *
 * 示例：
 *
 * 输入：
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出：[1,2,2,3,5,6]
 *
 */
public class MergeArray {
    private static int[] merge(int[] num1,int m,int[] num2,int n) {
        //将第一个数组作为合并后的数组容器
        int num1Len = m - 1; //num1
        int num2Len = n - 1; //num2
        while (num1Len >= 0 && num2Len >= 0) {
            if (num1[num1Len] < num2[num2Len]) {
                num1[num1Len + num2Len + 2] = num2[num2Len];
                num2Len--;
            } else {
                num1[num1Len + num2Len + 2] = num1[num1Len];
                num1Len--;
            }
        }
        //处理剩余的数据,如果剩下的是num1中的数据，则留在其中。
        //如果是num2中的数据，则倒叙填充
        while (num2Len >= 0) {
            num1[num2Len] = num2[num2Len];
            num2Len--;
        }
        return num1;
    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3};
        int[] num2 = {2,5,6};
        int[] result = merge(num1,num1.length,num2,num2.length);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
