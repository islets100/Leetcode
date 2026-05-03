import java.util.Scanner;

/**
 * 题号：LeetCode 45
 * 题目：跳跃游戏 II (Jump Game II)
 * 题目内容：给定一个长度为 n 的非负整数数组 nums。
 * 你最初位于数组的第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 目标是使用最少的跳跃次数到达数组的最后一个下标。
 * * 假设你总是可以到达数组的最后一个下标。
 * * 核心逻辑（贪心算法/区间步进）：
 * 1. 维护当前跳跃次数能达到的【右边界】 end。
 * 2. 在当前边界内 [start, end]，寻找下一跳能达到的【最远位置】 maxPos。
 * 3. 当目前的 end 还没到终点时，说明必须再跳一次，更新 end = maxPos。
 */
public class JumpGameIISolution {

    /**
     * 算法实现：贪心区间扫描
     * @param nums 跳跃步数数组
     * @return 最少跳跃次数
     */
    public int jump(int[] nums) {
        // 1. ans 记录跳跃次数
        int ans = 0;
        // 2. start 代表当前这一跳能到达的区间的左起点
        int start = 0;
        // 3. end 代表当前这一跳能到达的区间的右终点
        int end = 0;

        /**
         * 4. 只要当前的右边界 end 还没有覆盖到最后一个下标 (nums.length - 1)
         * 我们就需要继续寻找下一跳。
         */
        while (end < nums.length - 1) {
            // 5. maxPos 记录在 [start, end] 这个区间内，所有位置能跳到的最远点
            int maxPos = 0;

            /**
             * 6. 遍历当前步数所能覆盖的整个区间。
             * 我们要在这个“起跳平台”中，选一个能把我们送得最远的人。
             */
            for (int i = start; i <= end; i++) {
                // 7. i + nums[i] 是从下标 i 起跳能到达的最远位置
                // 我们不断更新 maxPos 记录下这个区间内的“最强战力”
                maxPos = Math.max(maxPos, i + nums[i]);
            }

            /**
             * 8. 准备进行下一跳：
             * 下一次搜索的起点从当前边界的下一个位置开始。
             */
            start = end + 1;

            /**
             * 9. 下一次能达到的最远右边界，就是刚才在区间里找到的 maxPos。
             */
            end = maxPos;

            /**
             * 10. 既然更新了边界，说明我们完成了一次“接力”跳跃，次数加 1。
             */
            ans++;
        }

        // 11. 返回总跳跃次数
        return ans;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JumpGameIISolution sol = new JumpGameIISolution();

        // 持续读取输入
        while (sc.hasNextInt()) {
            // 输入数组长度 n
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 读取 n 个整数进入数组
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 调用算法并输出结果
            System.out.println(sol.jump(nums));
        }
        sc.close();
    }
}