import java.util.Scanner;

/**
 * 题号：LeetCode 152
 * 题目：乘积最大子数组 (Maximum Product Subarray)
 * 题目内容：给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组
 * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-bit 整数。
 * * 核心思路：动态规划。由于负数的存在，最大值可能由“前一个最小值 * 当前负数”得到，
 * 因此需要同时维护最大值 (maxF) 和最小值 (minF)。
 */
public class maxProductSolution {

    /**
     * 算法实现：动态规划
     * @param nums 输入的整数数组
     * @return 连续子数组的最大乘积
     */
    public int maxProduct(int[] nums) {
        // 1. 获取数组长度
        int length = nums.length;
        
        // 2. 创建两个 DP 数组，长度与原数组一致
        // 使用 long 类型是为了防止中间乘积超出 int 的范围 (-2147483648 ~ 2147483647)
        long[] maxF = new long[length];
        long[] minF = new long[length];

        // 3. 初始化：第一个元素结尾的最大积和最小积都是它自己
        for (int i = 0; i < length; i++) {
            maxF[i] = nums[i];
            minF[i] = nums[i];
        }

        // 4. 从第二个元素开始遍历，递推计算每一个位置的状态
        for (int i = 1; i < length; i++) {
            // 当前数 nums[i]
            long cur = (long) nums[i];
            // 上一时刻的最大值和最小值
            long prevMax = maxF[i - 1];
            long prevMin = minF[i - 1];

            /**
             * 5. 【状态转移方程 - 最大值】：
             * 我们要在以下三个候选者中选出最大的：
             * - prevMax * cur: 如果当前是正数，最大值可能继续变大
             * - cur: 如果之前的乘积效果不好（比如之前是0），则从当前数重新开始
             * - prevMin * cur: 如果当前是负数，且之前有很小的负数，负负得正会变成巨大正数
             */
            maxF[i] = Math.max(prevMax * cur, Math.max(cur, prevMin * cur));

            /**
             * 6. 【状态转移方程 - 最小值】：
             * 同样地，记录最小值是为了给后续的“负负得正”做准备。
             */
            minF[i] = Math.min(prevMax * cur, Math.min(cur, prevMin * cur));

            // 注意：你提到的 if(minF[i] < ...) 保护逻辑在这里被省略了，
            // 只要 long 没溢出，保留真实的最小值对后续计算更准确。
        }

        // 7. 最终答案：maxF[i] 存储的是以 i 结尾的最大乘积。
        // 我们需要遍历整个 maxF 数组，找到全局的最大值。
        int ans = (int) maxF[0];
        for (int i = 1; i < length; i++) {
            // 将 long 强制转换为 int 返回，因为题目保证结果在 int 范围内
            ans = Math.max(ans, (int) maxF[i]);
        }

        return ans;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxProductSolution sol = new maxProductSolution();

        // 持续读取输入，支持多个测试用例
        while (sc.hasNextInt()) {
            // 第一行输入：数组长度 n
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 第二行输入：数组的 n 个元素
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 执行算法并打印结果
            System.out.println(sol.maxProduct(nums));
        }
        
        // 关闭资源
        sc.close();
    }
}