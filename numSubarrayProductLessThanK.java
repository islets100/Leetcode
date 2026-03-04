/*
713. 乘积小于 K 的子数组
给定一个正整数数组 nums 和整数 k ，
请找出该数组内乘积小于 k 的连续的子数组的个数。
*/

class numSubarrayProductLessThanKSolution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 特判：因为题目要求乘积严格小于 k，且数组元素都是正整数（最小为 1）
        // 如果 k <= 1，没有任何子数组的乘积能小于 1，直接返回 0
        if (k <= 1) {
            return 0;
        }

        int ans = 0;      // 用于累计符合条件的子数组总数
        int prod = 1;     // 用于记录当前窗口内所有元素的乘积
        int left = 0;     // 滑动窗口的左边界指针

        // right 是窗口的右边界指针，从左向右遍历整个数组
        for (int right = 0; right < nums.length; right++) {
            // 1. 进场：将当前右指针指向的数乘到 prod 中，窗口向右扩张
            prod *= nums[right];

            // 2. 出场（收缩）：如果当前窗口的乘积 prod 大于或等于 k
            // 则需要不断移动左指针 left，将左边的数从乘积中除掉，直到满足 prod < k
            while (prod >= k) {
                prod /= nums[left]; // 除去左边界的值
                left++;             // 左边界向右移动
            }

            // 3. 计数：这是最关键的一行！
            // 此时以 right 为终点，且满足乘积小于 k 的连续子数组个数
            // 正好等于当前窗口的长度：right - left + 1
            // 我们把这个长度累加到总数 ans 中
            ans += right - left + 1;
        }

        return ans; // 返回最终统计的总个数
    }
}


public class numSubarrayProductLessThanK {
    public static void main(String[] args) {
        numSubarrayProductLessThanKSolution solution = new numSubarrayProductLessThanKSolution();
        int[] nums1 = {10,5,2,6};
        int k1 = 100;
        int ans1 = solution.numSubarrayProductLessThanK(nums1, k1);
        System.out.println(ans1);
    }
}
