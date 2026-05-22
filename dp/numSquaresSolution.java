import java.util.Scanner;

/**
 * 题号：LeetCode 279
 * 题目：完全平方数
 * 内容：给你一个整数 n ，返回和为 n 的完全平方数的最少数量。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class numSquaresSolution {

    /**
     * 核心算法：动态规划
     * @param n 目标正整数
     * @return 最少数量
     */
    public int numSquares(int n) {
        // 1. 定义 dp 数组 f，f[i] 表示凑成数字 i 的最少完全平方数个数
        // 数组大小为 n + 1，因为我们要计算到 f[n]
        int[] f = new int[n + 1];

        // 2. 初始状态：f[0] = 0，因为凑成 0 需要 0 个平方数
        f[0] = 0;

        // 3. 开始外层循环：计算从 1 到 n 的每一个数字的最优解
        for (int i = 1; i <= n; i++) {
            
            // 4. 初始化 minn 为最大整数，用来在内层循环找最小值
            int minn = Integer.MAX_VALUE;

            // 5. 开始内层循环：枚举所有小于等于 i 的完全平方数 j*j
            // 比如 i=12 时，j*j 可以是 1(1*1), 4(2*2), 9(3*3)
            for (int j = 1; j * j <= i; j++) {
                
                // 6. 【状态转移核心】：
                // 如果我们选了 j*j 这个平方数，那么剩下的数值就是 i - j*j
                // 我们去查表 f[i - j*j]，看凑成剩下这个数最少需要几个
                // 在所有可选的 j*j 中，记录下最小的那个 f 值
                minn = Math.min(minn, f[i - j * j]);
            }

            // 7. 找到了最小的 f[i - j*j] 后，加上当前选的这 1 个平方数(j*j)
            // 存入 f[i]，表示凑成 i 的最少方案
            f[i] = minn + 1;
        }

        // 8. 返回最终目标 n 的最少数量
        return f[n];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化对象
        numSquaresSolution sol = new numSquaresSolution();

        // 持续读取输入直到结束
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            // 打印算法计算结果
            System.out.println(sol.numSquares(n));
        }
        
        // 关闭扫描器
        sc.close();
    }
}