import java.util.*;

/**
 * 题号：LeetCode 912（归并排序实现）
 * 题目：排序数组 (Sort an Array)
 * 题目内容：给你一个整数数组 nums，请你将该数组升序排列，并返回排序后的数组。
 *
 * 解题思路：归并排序 (Merge Sort)
 * 1. 分治：将 [left, right] 从中间 mid 拆成左右两半，分别递归排序。
 * 2. 合并：用临时数组 temp 对两个已有序子区间做双指针归并，再写回原数组。
 * 3. 时间复杂度 O(n log n)，空间复杂度 O(n)（每层合并需要临时数组）。
 * （同目录 sortArraySolution.java 为快排实现，可对比两种分治排序。）
 */
public class mergeSortSolution {

    /**
     * 对外接口：原地归并排序后返回原数组引用
     */
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 递归归并：对闭区间 [left, right] 排序
     */
    private void mergeSort(int[] nums, int left, int right) {
        // 1. 递归出口：区间长度 ≤ 1，天然有序
        if (left >= right) {
            return;
        }

        // 2. 取中点，拆成 [left, mid] 与 [mid+1, right]
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        // 3. 左右子数组都有序后，做一次归并
        merge(nums, left, mid, right);
    }

    /**
     * 合并两个有序子数组 nums[left..mid] 与 nums[mid+1..right]
     */
    private void merge(int[] nums, int left, int mid, int right) {
        // 4. 临时数组长度 = 当前待合并区间长度
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        // 5. 双指针：谁小谁先进入 temp
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 6. 某一侧耗尽后，把另一侧剩余元素直接拷入 temp
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // 7. 将 temp 中的有序结果写回 nums 的对应区间
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    private static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? "" : " "));
        }
        System.out.println();
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mergeSortSolution sol = new mergeSortSolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int[] ans = sol.sortArray(nums);
            printNums(ans);
        }
        sc.close();
    }
}
