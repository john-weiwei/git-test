package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/10/
 * @Description: 主要元素
 * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 */
public class MajorElement {
    public static void main(String[] args) {
//        int[] nArr = {1, 2, 2, 3, 2, 2, 4};
        int[] nArr = {1, 2,3};
//        int[] nArr = {6,5,5};
//        int result = majorElement(nArr);
        int result = majorElementOptimize(nArr);
        System.out.println(result);
    }

    /**
     * 时间复杂度 O(n^2)
     * 时间复杂度分析的基本策略：
     * 从内向外分析，从最深层开始分析。
     * 如果遇到函数调用，就要深入函数分析
     *
     * @param nArr
     * @return
     */
    private static int majorElement(int[] nArr) {
        int arrLen = nArr.length;
        int nHalf = arrLen / 2;
        int count = 1;
        for (int i = 0; i < arrLen; i++) {
            int temp = nArr[i];
            for (int j = i; j < arrLen - 1; j++) {
                if (temp == nArr[j + 1]) {
                    count++;
                }
            }
            if (count > nHalf) {
                return temp;
            }
            count = 1;
        }
        return -1;
    }

    /**
     * 摩尔投票算法
     * [1, 2, 2, 3, 2, 2, 4]
     * [6,5,5]
     * @param nArr
     * @return
     */
    private static int majorElementOptimize(int[] nArr) {
        int arrLen = nArr.length;
        int tempCount = 0;
        int temp = 0;
        for (int i = 0; i < arrLen; i++) {
            if (tempCount == 0) {
                temp = nArr[i];
                tempCount = 1;
            } else {
                if (temp == nArr[i]) {
                    tempCount++;
                } else {
                    tempCount--;
                }
            }
        }
        int nHalf = arrLen / 2;
        int count = 0;
        for (int i = 0; i < arrLen; i++) {
            if (nArr[i] == temp) {
                count++;
            }
        }
        if (count > nHalf) {
            return temp;
        }
        return -1;
    }
}
