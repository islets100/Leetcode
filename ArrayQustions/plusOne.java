package ArrayQustions; // 1. 这里必须跟文件夹名对齐

import java.util.Arrays;
import java.util.Scanner;

/**
 * Leetcode66. Plus One
 * You are given a large integer represented as an integer array digits, where
 * each digits[i] is the ith digit of the integer. The digits are ordered from
 * most significant to least significant in left-to-right order. The large
 * integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 */

public class plusOne { // 2. 这里的 plusOne 必须跟文件名 plusOne.java 对齐

    public int[] plusOneSolution(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {
        plusOne solution = new plusOne();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int[] ans = solution.plusOneSolution(nums);
            System.out.println(Arrays.toString(ans)); // 用 Arrays.toString 打印
        }
        sc.close();
    }
}