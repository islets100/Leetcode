/* 滑动窗口
209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。
找出该数组中满足其和 ≥ target 的长度最小的 
连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
并返回其长度。如果不存在符合条件的子数组，返回 0 。
*/


class minSubArrayLenSolution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;      // 获取数组长度
        int ans = n + 1;          // 初始化结果为最大可能值（n+1），用于后续取最小值
        int sum = 0;              // 记录当前滑动窗口内数字的总和
        int left = 0;             // 滑动窗口的左边界指针

        // 使用 right 作为窗口的右边界指针，不断向右扩张
        for (int right = 0; right < n; right++) {
            sum += nums[right];   // 将当前右指针指向的值加入总和

            // 【核心逻辑】只要当前窗口的和满足 >= target，就尝试缩小窗口
            // 这是一个持续收缩的过程，直到 sum 小于 target 为止
            while (sum >= target) {
                // 1. 更新答案：计算当前窗口长度 (right - left + 1)
                // 并与之前的最短纪录 ans 比较，只保留更小的那个
                ans = Math.min(ans, right - left + 1);

                // 2. 尝试收缩窗口：减去左边界的值
                sum -= nums[left];

                // 3. 左边界指针向右移动一位
                left++;
            }
        }

        // 最后判断：如果 ans 还是 n+1，说明没有找到任何满足条件的子数组，返回 0
        // 否则返回我们找到的全局最小长度 ans
        return ans <= n ? ans : 0;
    }
}

class subArray {
    public static void main(String[] args) {
        minSubArrayLenSolution solution = new minSubArrayLenSolution();
        int[] nums1 = {2,3,1,2,4,3};
        int target1 = 7;
        int ans1 = solution.minSubArrayLen(target1, nums1);
        int[] nums2 = {1,4,4};
        int target2 = 4;
        int ans2 = solution.minSubArrayLen(target2, nums2);
        int[] nums3 = {1,1,1,1,1,1,1,1};
        int target3 = 11;
        int ans3 = solution.minSubArrayLen(target3, nums3);
        int[] nums4 = {1,2,3,4,5};
        int target4 = 15;
        int ans4 = solution.minSubArrayLen(target4, nums4);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
    }

}