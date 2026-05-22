import java.util.*;

/**
 * 题号：LeetCode 912
 * 题目：排序数组 (Sort an Array)
 * 题目内容：给你一个整数数组 nums，请你将该数组升序排列，并返回排序后的数组。
 *
 * 解题思路：快速排序 (Quick Sort)
 * 1. 随机选取基准并交换到区间左端，降低有序数据下的最坏情况概率。
 * 2. partition：双指针从两端向中间扫描，将小于基准的放左侧，大于的放右侧。
 * 3. 以基准最终下标为界，递归排序左右子区间。平均时间 O(n log n)，空间 O(log n) 递归栈。
 * （同目录 mergeSortSolution.java 为归并排序实现，可对比两种分治排序。）
 */
public class sortArraySolution {

    private static final Random RANDOM = new Random();

    /**
     * 对外接口：原地排序后返回原数组引用
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 递归快排：对 [left, right] 闭区间排序
     */
    private void quickSort(int[] nums, int left, int right) {
        // 1. 区间长度 ≤ 1，已有序，直接返回
        if (left >= right) {
            return;
        }

        // 2. 随机选 pivot 并换到 left，避免固定取首元素在有序数组上退化
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 3. 划分后 pivot 的最终下标
        int pivotIndex = partition(nums, left, right);

        // 4. 递归左右子区间
        quickSort(nums, left, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    /**
     * 核心划分：以 nums[left] 为基准，返回基准最终位置
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            // 5. 从右向左找第一个小于 pivot 的元素
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];

            // 6. 从左向右找第一个大于 pivot 的元素
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        // 7. 双指针相遇处放基准
        nums[left] = pivot;
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sortArraySolution sol = new sortArraySolution();

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
