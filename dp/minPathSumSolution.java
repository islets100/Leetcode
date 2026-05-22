import java.util.Scanner;

/**
 * 题号：LeetCode 64
 * 题目：最小路径和 (Minimum Path Sum)
 * 题目内容：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，
 * 使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * * 核心思路：动态规划 (DP)
 * 1. 状态定义：dp[i][j] 表示从起点 (0,0) 走到坐标 (i, j) 的最小路径总和。
 * 2. 状态转移：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]。
 * 3. 初始状态：起点、第一行（只能从左来）、第一列（只能从上来）。
 */
public class minPathSumSolution {

    /**
     * 算法实现：二维动态规划
     * @param grid 输入的权重网格
     * @return 最小路径总和
     */
    public int minPathSum(int[][] grid) {
        // 1. 边界情况校验：如果网格为空，则路径和为 0
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;       // 网格的行数
        int columns = grid[0].length; // 网格的列数

        // 2. 创建一个二维 dp 数组，用于存储到达每个格子的最小路径和
        int[][] dp = new int[rows][columns];

        // 3. 初始化起点：到达起点的最小路径和就是起点格子本身的值
        dp[0][0] = grid[0][0];

        // 4. 初始化第一列：
        // 对于第一列的格子，只能通过“一路向下”到达，所以路径和是上方累加的结果
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 5. 初始化第一行：
        // 对于第一行的格子，只能通过“一路向右”到达，所以路径和是左方累加的结果
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 6. 填充剩余的格子 (i, j)：这些格子既可以从上面来，也可以从左边来
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                /**
                 * 7. 【核心状态转移方程】：
                 * 到达当前格子的最小路径和 = 前一步的两个来源中的最小值 + 当前格子的值。
                 * Math.min(dp[i-1][j], dp[i][j-1]) 负责挑出“更省钱”的那条路。
                 */
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // 8. 最终返回右下角终点位置存储的累计最小值
        return dp[rows - 1][columns - 1];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 使用 Scanner 读取输入数据
        Scanner sc = new Scanner(System.in);
        minPathSumSolution sol = new minPathSumSolution();

        // 循环读取数据
        while (sc.hasNextInt()) {
            // 输入行数 m 和列数 n
            int m = sc.nextInt();
            int n = sc.nextInt();
            
            // 构建并填充 grid 矩阵
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            
            // 调用算法并输出结果
            System.out.println(sol.minPathSum(grid));
        }
        
        // 关闭资源
        sc.close();
    }
}