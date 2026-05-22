import java.util.Scanner;

/**
 * 题号：LeetCode 416
 * 题目：分割等和子集
 * 题目内容：给你一个只包含正整数的非空数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * * 解题思路：
 * 1. 计算数组总和 sum。如果 sum 是奇数，直接返回 false。
 * 2. 目标是寻找子集和为 target = sum / 2。
 * 3. 这是一个典型的 0-1 背包问题：每个数字只能选一次，看能否凑满容量为 target 的背包。
 */
public class canPartitionSolution {

    /**
     * 核心算法：动态规划 (二维数组版)
     * @param nums 正整数数组
     * @return 是否可以分割
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        // 1. 如果数组长度小于 2，无法分成两个非空子集
        if (n < 2) {
            return false;
        }

        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        // 2. 如果总和是奇数，无法平分成两份
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        // 3. 如果数组中最大的数已经超过了总和的一半，那剩下所有数加起来也不够 target
        if (maxNum > target) {
            return false;
        }

        // 4. 定义 dp 数组：dp[i][j] 表示从前 i 个数中选，能否凑出和为 j
        // 行：表示考虑的数字范围 (0 到 n-1)
        // 列：表示目标和 (0 到 target)
        boolean[][] dp = new boolean[n][target + 1];

        // 5. 初始化：如果目标和为 0，不选任何数即可达成，所以全部设为 true
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // 6. 初始化第一行：只有当数字本身等于目标和时，才能凑出来
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        // 7. 开始填充 dp 表
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                // 8. 如果当前目标和 j 大于等于当前数字 num
                if (j >= num) {
                    /**
                     * 状态转移核心：
                     * 你有两个选择：
                     * A. 不选当前数 nums[i]：那么结果取决于 dp[i-1][j]
                     * B. 选当前数 nums[i]：那么结果取决于 dp[i-1][j-num] (看之前能不能凑出剩下的差额)
                     * 只要其中一种情况为 true，dp[i][j] 就是 true。
                     */
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    // 9. 如果当前数字 num 太大了，直接放不下，只能选择“不选”
                    dp[i][j] = dp[i - 1][j];
                }
            }
            
            // 优化点：如果已经发现能凑出 target 了，可以提前结束
            if (dp[i][target]) return true;
        }

        // 10. 返回最终结果
        return dp[n - 1][target];
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        canPartitionSolution sol = new canPartitionSolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(sol.canPartition(nums));
        }
        sc.close();
    }
}