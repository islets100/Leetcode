import java.util.Arrays;
import java.util.Scanner;

/**
 * 题号：LeetCode 88
 * 题目：合并两个有序数组 (Merge Sorted Array)
 * 题目内容：给你两个按非递减顺序排列的整数数组 nums1 和 nums2，
 * 以及两个整数 m 和 n，分别表示 nums1 和 nums2 中元素的数目。
 * nums1 的长度为 m + n，前 m 个元素为有效元素；nums2 长度为 n。
 * 请你合并 nums2 到 nums1 中，使合并后的数组仍按非递减顺序排列，结果存放在 nums1 中。
 *
 * 解题思路：双指针 + 辅助数组
 * 1. 用指针 p1、p2 分别扫描 nums1 的有效段和 nums2。
 * 2. 每次取当前较小的元素写入辅助数组 sorted，直到某一侧耗尽。
 * 3. 将 sorted 拷回 nums1。时间复杂度 O(m+n)，空间复杂度 O(m+n)。
 * （进阶：也可从 nums1 尾部倒着合并，实现 O(1) 额外空间，本题采用 interview 中的辅助数组写法。）
 */
public class mergeSortedArraySolution {

    /**
     * 核心算法：双指针归并
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 1. 两个指针分别指向 nums1 有效区、nums2 的起始位置
        int p1 = 0, p2 = 0;
        // 2. 辅助数组存放归并结果，长度为 m + n
        int[] sorted = new int[m + n];
        int cur;

        // 3. 只要还有一侧未读完，就继续归并
        while (p1 < m || p2 < n) {
            // 4. nums1 已读完，剩余元素全部来自 nums2
            if (p1 == m) {
                cur = nums2[p2++];
            }
            // 5. nums2 已读完，剩余元素全部来自 nums1
            else if (p2 == n) {
                cur = nums1[p1++];
            }
            // 6. 两侧都有元素：取较小者，相等时优先取 nums1（保持稳定性）
            else if (nums1[p1] <= nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            // 7. 写入当前归并位置：p1 + p2 - 1 表示已写入元素个数减 1
            sorted[p1 + p2 - 1] = cur;
        }

        // 8. 将归并结果拷回原数组 nums1
        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];
        }
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mergeSortedArraySolution sol = new mergeSortedArraySolution();

        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] nums1 = new int[m + n];
        for (int i = 0; i < m; i++) {
            nums1[i] = sc.nextInt();
        }
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
        }

        sol.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
        sc.close();
    }
}
