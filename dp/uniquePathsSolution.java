import java.util.Scanner;

/**
 * 题号：LeetCode 62
 * 题目：不同路径 (Unique Paths)
 * 题目内容：一个机器人位于一个 m x n 网格的左上角 (起点在下图中标记为 “Start” )。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角 (终点在下图中标记为 “Finish” )。
 * 问总共有多少条不同的路径？
 * * 核心思路：动态规划
 * 1. 状态定义：dp[i][j] 表示从起点走到坐标 (i, j) 的路径总数。
 * 2. 状态转移：dp[i][j] = dp[i-1][j] + dp[i][j-1] (上方路径 + 左方路径)。
 * 3. 初始状态：第一行和第一列的路径数均为 1（因为只能一路向右或一路向下）。
 */
public class uniquePathsSolution {

    /**
     * 算法实现：二维动态规划
     * @param m 行数
     * @param n 列数
     * @return 路径总数
     */
    public int uniquePaths(int m, int n) {
        // 1. 创建一个二维数组 f (即 dp 表)，用于存储到达每个格子的路径数
        int[][] f = new int[m][n];

        // 2. 初始化第一列：
        // 机器人只能向下移动，所以第一列的任何格子都只有一种走法（从起点一直往下）
        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }

        // 3. 初始化第一行：
        // 机器人只能向右移动，所以第一行的任何格子都只有一种走法（从起点一直往右）
        for (int j = 0; j < n; j++) {
            f[0][j] = 1;
        }

        // 4. 从第 1 行第 1 列（即坐标 (1,1)）开始填充剩余的格子
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                /**
                 * 5. 【核心状态转移方程】：
                 * 到达 f[i][j] 的路径数 = 来自上方的路径 f[i-1][j] + 来自左方的路径 f[i][j-1]。
                 */
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        // 6. 返回右下角终点位置的路径总数
        return f[m - 1][n - 1];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取控制台输入
        Scanner sc = new Scanner(System.in);
        uniquePathsSolution sol = new uniquePathsSolution();

        // 循环读取输入，通常输入为两个整数 m 和 n
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            
            // 执行算法并打印结果
            System.out.println(sol.uniquePaths(m, n));
        }

        // 资源清理
        sc.close();
    }
}