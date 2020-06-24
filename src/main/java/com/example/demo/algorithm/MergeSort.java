package com.example.demo.algorithm;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MergeSort {
    public void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[a.length];
        int p1 = left, p2 = mid+1, k = left;
        while(p1 <=mid && p2<=right) { // 比较左右两个数组，按序将元素添加到temp对应位置
            if(a[p1] <= a[p2]) {
                temp[k++] = a[p1++];
            } else {
                temp[k++] = a[p2++];
            }
        }
        while(p1<=mid) temp[k++] = a[p1++]; // 如果左侧数组有余值，直接将后面元素添加到temp
        while(p2<=right) temp[k++] = a[p2++]; // 同上
        // 将已排序好的区间复制回原数组
        for(int i = left; i<=right; i++) {
            a[i] =temp[i];
        }
    }

    public void mergeSort(int[] a, int start, int end) {
        if(start < end) {
            int mid = (start+end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid+1, end);
            merge(a, start, mid, end);
        }
    }

    public int[] mergeSort(int[] arr) {
        int l = arr.length;
        if (1 == l) {
            return arr;
        }
        int mid = (l-1) / 2;
        int[] leftArray = Arrays.copyOfRange(arr, 0, mid + 1);
        int[] rightArray = Arrays.copyOfRange(arr, mid + 1, l);

        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }
    public int[] merge(int[] left, int[] right) {
        int ll = left.length;
        int lr = right.length;
        int[] tmp = new int[ll+lr];
        int p1 = 0, p2 = 0, k = 0;
        // 归并有序数组
        while(p1 <= ll-1 && p2 <= lr-1 ) {
            if(left[p1] <= right[p2]) {
                tmp[k++] = left[p1++];
            } else {
                tmp[k++] = right[p2++];
            }
        }

        while(p1 <= ll-1) tmp[k++] = left[p1++];
        while(p2 <= lr-1) tmp[k++] = right[p2++];

        return tmp;
    }
    public static void main(String[] args) {
        int arrLength = 1000000;
        // int[] a = {38,13,3,29,46,13,75,99};
        int[] a = new int[arrLength];
        Random random = new Random();
        for (int i=0; i < arrLength; i++) {
            a[i] = random.nextInt(arrLength);
        }
        MergeSort merge = new MergeSort();
        int[] newArray = new int[a.length];
        long st = System.currentTimeMillis();
        newArray = merge.mergeSort(a);
        long ed = System.currentTimeMillis();
        // for (int e : newArray) {
        //     System.out.print( e + " ");
        // }
        System.out.print("\n");
        System.out.println("方法2耗时: "+(ed-st)+"ms\n"); // 方法2 耗时相对较少
        System.out.println("排序好数组长度："+ newArray.length+"\n");
        // for (int e : a) {
        //     System.out.print( e + " ");
        // }
        // System.out.print("\n");
        long stt = System.currentTimeMillis();
        merge.mergeSort(a, 0, a.length - 1);
        long ent = System.currentTimeMillis();
        //  for (int e : a) {
        //     System.out.print( e + " ");
        // }
        // System.out.print("\n");
        System.out.println("方法1耗时: "+(ent-stt)+"ms\n");
        System.out.println("排序好数组长度："+ a.length);
	}

}