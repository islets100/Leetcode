import java.util.Scanner;

/**
 * 题号：LeetCode 198
 * 题目：打家劫舍
 * 内容：你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 制约你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class robSolution {

    /**
     * 核心算法：动态规划
     * @param nums 房屋金额数组
     * @return 最高偷窃总额
     */
    public int rob(int[] nums) {
        // 1. 健壮性检查：如果数组为空或长度为 0，没得偷，返回 0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        
        // 2. 边界处理：如果只有一间房，直接偷了它，返回它的金额
        if (length == 1) {
            return nums[0];
        }

        // 3. 定义 dp 数组：dp[i] 表示偷到第 i 间房屋时能拿到的最高总金额
        int[] dp = new int[length];

        // 4. 初始化状态：
        // 第 0 间房：最高金额就是偷它自己
        dp[0] = nums[0];
        // 第 1 间房：因为不能同时偷 0 和 1，所以选金额最高的那一个偷
        dp[1] = Math.max(nums[0], nums[1]);

        // 5. 开始迭代：从第 2 间房屋（下标为 2）开始往后推导
        for (int i = 2; i < length; i++) {
            /**
             * 6. 【核心状态转移方程】：
             * 我们要在两个策略中选最大值：
             * A. 抢劫当前房屋 (nums[i])：
             * 那必须跳过上一间，所以是当前金额 + 到达上上间时的最大金额 (dp[i - 2] + nums[i])
             * B. 不抢当前房屋：
             * 那最高金额就等于到达上一间时的最高金额 (dp[i - 1])
             */
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        // 7. dp 数组的最后一位记录了在所有房屋中能偷到的最大总额
        return dp[length - 1];
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        robSolution sol = new robSolution();

        // 循环读取数据
        while (sc.hasNextInt()) {
            int n = sc.nextInt(); // 先输入房间总数
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt(); // 输入每间房的金额
            }
            // 打印结果
            System.out.println(sol.rob(nums));
        }
        sc.close();
    }
}