import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名命名规则：核心方法名 + Solution
 */
public class numIslandsSolution {

    /**
     * 核心递归函数：深度优先搜索 (DFS)
     * 作用：从指定的 (r, c) 开始，把相连的所有陆地 '1' 变成 '0'
     */
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;    // 行数
        int nc = grid[0].length; // 列数

        // [递归出口/边界检查]
        // 1. 坐标越界（r < 0, c < 0 等）
        // 2. 当前位置是 '0'（已经是水，或者已经被访问并沉没了）
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        // 将当前陆地“沉没”，标记为 '0'，防止以后重复访问
        grid[r][c] = '0';

        // 递归访问四个方向：上、下、左、右
        dfs(grid, r - 1, c); // 向上走一行
        dfs(grid, r + 1, c); // 向下走一行
        dfs(grid, r, c - 1); // 向左走一列
        dfs(grid, r, c + 1); // 向右走一列
    }

    /**
     * 核心算法：计算岛屿数量
     */
    public int numIslands(char[][] grid) {
        // 边界处理：如果网格为空，岛屿数为 0
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;    // 网格的行数
        int nc = grid[0].length; // 网格的列数
        int num_islands = 0;     // 记录岛屿的数量

        // 双层循环遍历整个二维网格
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                // 如果发现一块陆地 '1'
                if (grid[r][c] == '1') {
                    // 岛屿计数加 1
                    ++num_islands;
                    // 启动 DFS，把和这块陆地相连的所有陆地全部“沉掉”
                    dfs(grid, r, c);
                }
            }
        }

        // 返回最终统计到的岛屿总数
        return num_islands;
    }

    /**
     * 3. 主函数：处理 ACM 模式输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numIslandsSolution solution = new numIslandsSolution();

        // 假设输入格式为：
        // 第一行两个整数 n m (表示行列)
        // 接下来 n 行，每行 m 个字符
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine(); // 消耗整数后的换行符

            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                // 允许输入 1 1 0 或 110 这种格式
                line = line.replace(" ", ""); 
                grid[i] = line.toCharArray();
            }

            // 输出岛屿数量
            System.out.println(solution.numIslands(grid));
        }
        sc.close();
    }
}