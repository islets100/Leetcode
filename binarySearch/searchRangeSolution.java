import java.util.Scanner;
import java.util.Arrays;

/**
 * 题号：LeetCode 34
 * 题目：在排序数组中查找元素的第一个和最后一个位置
 * 题目内容：给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 要求：设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class searchRangeSolution {

    /**
     * 主逻辑：通过两次二分查找确定范围
     */
    public int[] searchRange(int[] nums, int target) {
        // 1. 寻找第一个【大于等于】target 的索引位置（即左边界）
        int leftidx = binarySearch(nums, target, true);
        
        // 2. 寻找第一个【大于】target 的索引位置，然后减去 1（即右边界）
        int rightidx = binarySearch(nums, target, false) - 1;

        // 3. 结果校验：
        // leftidx <= rightidx: 确保找到了目标区间
        // rightidx < nums.length: 确保索引不越界
        // nums[leftidx] == target && nums[rightidx] == target: 确保找到的值确实是 target
        if (leftidx <= rightidx && rightidx < nums.length && nums[leftidx] == target && nums[rightidx] == target) {
            return new int[]{leftidx, rightidx};
        }
        
        // 4. 若校验失败，说明数组中没有 target
        return new int[]{-1, -1};
    }

    /**
     * 万能二分查找函数
     * @param lower 为 true 时，查找第一个 >= target 的位置
     * 为 false 时，查找第一个 > target 的位置
     */
    public int binarySearch(int[] nums, int target, boolean lower) {
        int n = nums.length;
        int left = 0, right = n - 1;
        
        // 初始化结果 ans 为 n，表示如果所有元素都小于 target，则返回数组末尾的下一个位置
        int ans = n;

        while (left <= right) {
            // 计算中点位置，防止溢出的位运算写法
            int mid = ((right - left) >> 1) + left;

            // 核心逻辑判断：
            // 情况 A：nums[mid] > target。说明 target 肯定在左边，中点可能是潜在的边界。
            // 情况 B：lower 为 true 且 nums[mid] == target。
            //        因为我们要找的是【第一个】，所以即使等于 target，也要继续往左搜。
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                // 记录当前满足条件的下标
                ans = mid;
                // 收缩右边界，去左半部分找更小的符合条件的索引
                right = mid - 1;
            } else {
                // 如果 nums[mid] < target，说明 target 在右侧，直接收缩左边界
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        searchRangeSolution solution = new searchRangeSolution();

        while (sc.hasNextInt()) {
            // 读取数组大小
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 读取数组元素
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            // 读取目标值
            int target = sc.nextInt();

            // 调用算法并打印
            int[] result = solution.searchRange(nums, target);
            System.out.println(Arrays.toString(result));
        }
        sc.close();
    }
}