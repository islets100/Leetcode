import java.util.Scanner;

/**
 * 题号：LeetCode 70
 * 题目：爬楼梯 (Climbing Stairs)
 * 题目内容：假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 示例：n = 3 -> 3种方法 (1+1+1, 1+2, 2+1)
 */
public class climbStairsSolution {

    /**
     * 核心算法：动态规划（空间优化版）
     * @param n 楼梯的总阶数
     * @return 到达顶部的总方法数
     */
    public int climbStairs(int n) {
        // 1. 定义三个变量代表状态：
        // p: 到达“前两级”台阶的方法数 (f[n-2])
        // q: 到达“前一级”台阶的方法数 (f[n-1])
        // r: 到达“当前”台阶的方法数 (f[n])
        int p = 1, q = 2, r = 2;

        // 2. 特殊情况处理（边界）：
        // 如果只有 1 阶，直接返回 p (1 种走法)
        if (n == 1) return p;
        // 如果只有 2 阶，直接返回 q (2 种走法)
        if (n == 2) return q;

        // 3. 从第 3 阶开始循环计算，直到第 n 阶
        // 既然前两阶已知，我们只需要通过累加来推导后面的阶数
        for (int i = 3; i <= n; i++) {
            // 4. 【核心转移方程】：f(i) = f(i-1) + f(i-2)
            // 即：到达当前阶的方法 = 到达上一阶的方法 + 到达上上阶的方法
            r = p + q;

            // 5. 【窗口滚动/准备下一轮】：
            // 将原来的“前一级 (q)”变成下一轮的“前两级 (p)”
            p = q;
            // 将刚刚算出的“当前级 (r)”变成下一轮的“前一级 (q)”
            q = r;
            
            // 这样，在下一次循环中，r = p + q 依然能准确算出更高一级的结果
        }

        // 6. 循环结束，r 已经累加到了第 n 级的结果
        return r;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类对象
        climbStairsSolution solution = new climbStairsSolution();

        // 持续读取输入直到没有数据
        while (sc.hasNextInt()) {
            // 读取用户输入的阶数 n
            int n = sc.nextInt();
            // 调用算法并输出结果
            System.out.println(solution.climbStairs(n));
        }
        
        // 养成好习惯，关闭扫描器
        sc.close();
    }
}