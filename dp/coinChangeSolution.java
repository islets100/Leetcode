import java.util.Scanner;
import java.util.Arrays;

/**
 * 题号：LeetCode 322
 * 题目：零钱兑换 (Coin Change)
 * 题目内容：给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
public class coinChangeSolution {

    /**
     * 核心算法：动态规划（自底向上）
     * @param coins 硬币面额数组
     * @param amount 目标总金额
     * @return 最少硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        // 1. 设置一个“最大值”哨兵。
        // 因为凑齐 amount 最多需要 amount 枚硬币（全是 1 元），
        // 所以 amount + 1 绝对是一个不可能达到的上限。
        int max = amount + 1;

        // 2. 开辟 dp 数组，长度为 amount + 1。
        // 下标代表金额，dp[i] 代表凑齐金额 i 的最少硬币数。
        // 这里的 +1 正是为了让下标能直接对应金额 0 到 amount。
        int[] dp = new int[amount + 1];

        // 3. 初始化数组：将所有位置填充为一个“不可能的大数” (max)。
        // 这样做是为了在后续 Math.min 比较时，能正确选出较小值。
        Arrays.fill(dp, max);

        // 4. 基础状态：凑齐金额 0 需要 0 枚硬币。
        dp[0] = 0;

        // 5. 外层循环：从金额 1 开始计算，直到目标金额 amount。
        for (int i = 1; i <= amount; i++) {
            
            // 6. 内层循环：遍历所有可选的硬币面额。
            for (int j = 0; j < coins.length; j++) {
                
                // 7. 判断：如果当前硬币面额 coins[j] 小于等于我们要凑的金额 i
                if (coins[j] <= i) {
                    /**
                     * 8. 【核心状态转移】：
                     * dp[i - coins[j]] 是凑齐剩余金额所需的最少硬币。
                     * + 1 是加上当前这枚面值为 coins[j] 的硬币。
                     * 我们通过不断比较，更新 dp[i] 为最小值。
                     */
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        /**
         * 9. 结果返回：
         * 如果 dp[amount] 依然大于 amount（即初始的 max），
         * 说明没有任何硬币组合能凑齐该金额，按要求返回 -1。
         * 否则返回算出的最少枚数。
         */
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        coinChangeSolution sol = new coinChangeSolution();

        // 循环读取输入
        while (sc.hasNextInt()) {
            // 第一行：输入硬币种类个数
            int n = sc.nextInt();
            int[] coins = new int[n];
            // 第二行：输入每种硬币的面值
            for (int i = 0; i < n; i++) {
                coins[i] = sc.nextInt();
            }
            // 第三行：输入目标金额
            int amount = sc.nextInt();

            // 打印结果
            System.out.println(sol.coinChange(coins, amount));
        }
        sc.close();
    }
}