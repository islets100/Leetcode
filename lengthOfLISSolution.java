import java.util.Scanner;

/**
 * 题号：LeetCode 300
 * 题目：最长递增子序列 (LIS)
 * 题目内容：给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class lengthOfLISSolution {

    /**
     * 核心算法：动态规划
     * @param nums 输入数组
     * @return 最长递增子序列的长度
     */
    public int lengthOfLIS(int[] nums) {
        // 1. 基础校验：如果数组为空，长度自然为 0
        if (nums.length == 0) {
            return 0;
        }

        // 2. 定义 dp 数组：dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        
        // 3. 初始化：第一个元素结尾的 LIS 长度肯定是 1
        dp[0] = 1;
        
        // 4. 定义全局最大值 maxans，用来记录 dp 数组中的最大数
        int maxans = 1;

        // 5. 外层循环 i：从下标 1 开始，依次计算每个位置作为结尾时的 LIS 长度
        for (int i = 1; i < nums.length; i++) {
            
            // 6. 每一个数字起步长度都是 1（即只包含它自己）
            dp[i] = 1;
            
            // 7. 内层循环 j：回头看 i 之前的所有数字
            for (int j = 0; j < i; j++) {
                
                // 8. 【核心判断】：如果当前的数比前面的数大，说明可以形成递增关系
                if (nums[i] > nums[j]) {
                    /**
                     * 9. 【状态转移】：
                     * 我们尝试把 nums[i] 接在 nums[j] 后面。
                     * 接上去后的新长度就是 dp[j] + 1。
                     * 我们不断对比，取所有可能接法中的最大值。
                     */
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            // 10. 每次算完一个 dp[i]，都更新一下全局最长长度
            maxans = Math.max(maxans, dp[i]);
        }

        // 11. 返回最终结果
        return maxans;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        lengthOfLISSolution sol = new lengthOfLISSolution();

        // 循环读取输入数据
        while (sc.hasNextInt()) {
            // 输入数组长度
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 输入数组元素
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            // 打印最长递增子序列长度
            System.out.println(sol.lengthOfLIS(nums));
        }
        sc.close();
    }
}