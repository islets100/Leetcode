import java.util.*;

/**994.腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class orangesRottingSolution {

    // 定义四个方向：上、下、左、右
    private static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 核心算法：计算橘子全部腐烂所需的最短时间
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0; // 统计新鲜橘子的数量

        // 队列 q 用于存放当前轮次所有腐烂橘子的坐标
        // 这里用 List 模拟队列（也可以用 Queue<int[]>）
        List<int[]> q = new ArrayList<>();

        // 1. 扫描整个网格，初始化状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 发现一个新鲜橘子
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j}); // 发现初始腐烂橘子，加入队列
                }
            }
        }

        int ans = 0; // 记录经过的分钟数（轮次）

        // 2. 开始 BFS 扩散
        // 循环条件：还有新鲜橘子 且 队列里还有能扩散的腐烂橘子
        while (fresh > 0 && !q.isEmpty()) {
            ans++; // 每一轮代表 1 分钟
            
            // tmp 存放的是“这一分钟新变腐烂”的橘子
            List<int[]> tmp = q;
            q = new ArrayList<>(); // 清空 q，准备接收下一分钟要扩散的种子

            // 遍历当前这一轮所有的腐烂橘子
            for (int[] pos : tmp) {
                // 尝试向四个方向扩散
                for (int[] d : DIRECTIONS) {
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];

                    // 如果坐标在范围内，并且该位置是新鲜橘子
                    if (0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1) {
                        fresh--;        // 新鲜橘子少了一个
                        grid[i][j] = 2; // 把它变腐烂
                        q.add(new int[]{i, j}); // 把它加入下一轮扩散的队列
                    }
                }
            }
        }

        // 3. 检查结果：如果还有新鲜橘子没被感染，返回 -1；否则返回分钟数
        return fresh > 0 ? -1 : ans;
    }

    /**
     * 主函数：处理 ACM 输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        orangesRottingSolution solution = new orangesRottingSolution();

        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            System.out.println(solution.orangesRotting(grid));
        }
        sc.close();
    }
}