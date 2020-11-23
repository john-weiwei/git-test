package com.example.springproject.demo.leetcode.array;

/**
 * 给定一个矩阵 A， 返回 A 的转置矩阵。
 *
 * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 * 示例 1：
 *
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 * 示例 2：
 *
 * 输入：[[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 *
 */
public class TransposeMatrix {
    public static void main(String[] args) {
        int[][] aArr = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = transpose(aArr);
        System.out.println("转置矩阵完成");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
            System.out.println("输出第"+i+"行数据");
        }
    }

    public static int[][] transpose(int[][] A) {
        //整个数组长度
        int ALen = A.length;
        //子数组的长度
        int A0Len = A[0].length;
        //转置后的元素数组
        int[][] aT = new int[A0Len][ALen];
        for (int i = 0; i < ALen; i++) {
            for (int j = 0; j < A0Len; j++) {
                aT[j][i] = A[i][j];
                System.out.println("A["+i+"]["+j+"]赋值给aT["+j+"]["+i+"]");
            }
            System.out.println("第"+i+"转换完成");
        }
        return aT;
    }
}
