import java.util.Scanner;

/**
 * 题号：LeetCode 55
 * 题目：跳跃游戏 (Jump Game)
 * 题目内容：给定一个非负整数数组 nums ，你最初位于数组的第一个下标。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * * 核心逻辑（贪心算法）：
 * 1. 维护一个变量 k，表示当前所有已走过的路程中，能跳到的【最远边界】。
 * 2. 遍历数组，如果当前位置 i 已经超过了最远边界 k，说明此路不通，返回 false。
 * 3. 实时更新最远边界 k。
 * 4. 如果 k 已经覆盖了最后一个下标，提前返回 true。
 */
public class canJumpSolution {

    /**
     * 算法实现：贪心扫描法
     * @param nums 跳跃步数数组
     * @return 是否能到达终点
     */
    public boolean canJump(int[] nums) {
        // 1. 初始化最远可达边界 k 为 0
        int k = 0;

        // 2. 开始遍历数组中的每一个位置 i
        for (int i = 0; i < nums.length; i++) {
            
            // 3. 【失败校验】：如果当前索引 i 大于目前能跳到的最远距离 k
            // 说明你的能量不足以让你走到当前这个格子，后续也就更不可能了。
            if (i > k) {
                return false;
            }

            // 4. 【状态更新】：尝试更新最远边界 k
            // 新边界 = max(旧边界, 当前位置 i + 当前格子的跳跃能力 nums[i])
            k = Math.max(k, i + nums[i]);

            // 5. 【提前返回】：如果当前的最远边界已经涵盖了最后一个位置（nums.length - 1）
            // 那么没必要再继续往后走循环了，直接判定成功。
            if (k >= nums.length - 1) {
                return true;
            }
        }

        /**
         * 6. 【兜底返回】：
         * 虽然在循环中我们写了提前返回 true，但根据 Java 语法要求，
         * 必须在方法末尾提供一个明确的返回值。
         * 此外，如果数组长度为 0（虽然本题输入通常长度 >= 1），
         * 循环不会执行，此时默认返回 true。
         */
        return true;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        canJumpSolution sol = new canJumpSolution();

        // 循环读取输入数据
        while (sc.hasNextInt()) {
            // 输入格式：n (数组长度)，然后是 n 个整数
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 输出算法结果
            System.out.println(sol.canJump(nums));
        }
        sc.close();
    }
}