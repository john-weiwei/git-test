package com.example.springproject.demo.leetcode.array;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/10/31
 * @Description:
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 */
public class SortedSquares {
    private static void sortedSquares(int[] nArr) {
        int nLen = nArr.length;
        int[] nFinalValue = new int[nLen];
        int value = 0;
        for (int i = 0; i < nLen; i++) {
            value = Math.abs(nArr[i]);
            int pValue = value * value;
            nFinalValue[i] = pValue;
        }
        //升序排序
        for (Integer item: nFinalValue
             ) {
            System.out.println(item);
        }
        int[] values =  fastSorted(nFinalValue,0,nFinalValue.length-1);
        for (Integer item: values
        ) {
            System.out.println(item);
        }
    }

    private static int[] fastSorted(int[] nList,int low,int high) {
        if (low >= high) {
            return nList;
        }
        int left = low;
        int right = high;
        int pivot = nList[left];
        while (left < right) {
            //从后往前扫，把比基准值小的数放在左边
            while (left < right && nList[right] >= pivot) {
                right--;
            }
            nList[left] = nList[right];
            //从前往后扫，比基准值大的放在右边
            while (left < right && nList[left] <= pivot) {
                left++;
            }
            nList[right] = nList[left];
        }
         nList[left] = pivot;
        //递归左子序列
        fastSorted(nList,low,left-1);
        //递归右子序列
        fastSorted(nList,left+1,high);
        return nList;
    }

    public static void main(String[] args) {
        int[] nArr = {-4,-1,0,3,10};
//        int[] nArr = {-7,-3,2,3,11};
        sortedSquares(nArr);
    }
}
