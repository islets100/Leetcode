import java.util.*;

/**
 * 题号：LeetCode 51
 * 题目：N 皇后 (N-Queens)
 * 题目内容：按照国际象棋的规则，皇后可以攻击与之处在同一行、同一列或同一斜线上的棋子。
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 */
public class NQueensSolution {

    /**
     * 主方法：初始化并开启回溯
     * @param n 皇后数量/棋盘维度
     */
    public List<List<String>> solveNQueens(int n) {
        // 1. ans 用于存放所有满足要求的合法棋盘布局
        List<List<String>> ans = new ArrayList<>();
        
        // 2. 核心状态数组：queens[r] = c 表示在第 r 行，皇后放在了第 c 列
        // 因为每一行只能放一个皇后，所以用一维数组即可代表棋盘状态
        int[] queens = new int[n];
        
        // 3. 【禁区雷达】布尔数组，记录哪些区域不能再放皇后
        // col[c]: 标记第 c 列是否已经有皇后了
        boolean[] col = new boolean[n];
        
        // diag1[r + c]: 标记“左下到右上”方向的对角线（主对角线）
        // 在同一条这类斜线上，行索引 r 和列索引 c 的和 (r+c) 是固定的
        boolean[] diag1 = new boolean[n * 2 - 1];
        
        // diag2[r - c + n - 1]: 标记“左上到右下”方向的对角线（副对角线）
        // 在同一条这类斜线上，行索引 r 和列索引 c 的差 (r-c) 是固定的
        // 加 n-1 是为了保证结果为正数，作为数组下标
        boolean[] diag2 = new boolean[n * 2 - 1];

        // 4. 开启回溯搜索：从第 0 行开始摆放
        dfs(0, queens, col, diag1, diag2, ans);
        return ans;
    }

    /**
     * 递归函数：深度优先搜索 (DFS)
     * @param r      当前正在处理第 r 行
     * @param queens 记录当前每行皇后位置的数组
     * @param col    列占用标记
     * @param diag1  主对角线占用标记
     * @param diag2  副对角线占用标记
     * @param ans    结果集容器
     */
    private void dfs(int r, int[] queens, boolean[] col, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        int n = col.length;
        
        // 【递归出口】：如果 r == n，说明从 0 到 n-1 行都已经成功摆放了皇后
        if (r == n) {
            // 准备一个符合题目输出格式的 List<String> 棋盘
            List<String> board = new ArrayList<>(n);
            for (int c : queens) {
                // c 是每一行皇后所在的列号
                char[] row = new char[n];
                Arrays.fill(row, '.'); // 默认全部填上 '.'
                row[c] = 'Q';          // 在皇后位置填上 'Q'
                board.add(new String(row)); // 转换成字符串加入当前棋盘
            }
            ans.add(board); // 将这个完整的合法棋盘存入总结果
            return;
        }

        // 【遍历尝试】：在当前的第 r 行，尝试每一列 c
        for (int c = 0; c < n; c++) {
            // 计算当前格子所在的副对角线索引
            int rc = r - c + n - 1;
            
            // 【核心判断】：如果第 c 列、这条主对角线、这条副对角线都没有被占用
            if (!col[c] && !diag1[r + c] && !diag2[rc]) {
                
                // 1. 【做选择】：在 (r, c) 这个位置放置皇后
                queens[r] = c; 
                
                // 2. 【锁定区域】：标记列和两条斜线为占用状态 (true)
                col[c] = diag1[r + c] = diag2[rc] = true;
                
                // 3. 【递归】：前往下一行 (r + 1) 继续找位置
                dfs(r + 1, queens, col, diag1, diag2, ans);
                
                // 4. 【撤销/回溯】：非常关键！
                // 当从递归返回后，无论结果如何，都要把刚刚占用的列和斜线释放掉 (false)
                // 这样循环到下一列 c+1 时，之前的标记才不会干扰后续的判断
                col[c] = diag1[r + c] = diag2[rc] = false;
            }
        }
    }

    /**
     * ACM 模式主函数：读取输入并打印结果
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NQueensSolution solution = new NQueensSolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            List<List<String>> results = solution.solveNQueens(n);
            
            // 打印所有方案
            System.out.println("N = " + n + " 的解法共有 " + results.size() + " 种：");
            for (List<String> board : results) {
                for (String row : board) {
                    System.out.println(row);
                }
                System.out.println(); // 方案之间留空行
            }
        }
        sc.close();
    }
}