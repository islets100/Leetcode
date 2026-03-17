import java.util.Scanner;

/**
 * 题号：LeetCode 79
 * 题目：单词搜索 (Word Search)
 * 题目内容：给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 */
public class existSolution {

    /**
     * 主逻辑入口：exist 方法
     * @param board 二维字符矩阵
     * @param word  目标单词
     * @return 是否存在该路径
     */
    public boolean exist(char[][] board, String word) {
        // 1. 获取矩阵的高度 h 和宽度 w
        int h = board.length;
        int w = board[0].length;
        
        // 2. 创建一个等大的布尔矩阵 visited，记录当前搜索路径中哪些位置已被占用
        // 这是为了满足题目要求的“同一个单元格内的字母不允许被重复使用”
        boolean[][] visited = new boolean[h][w];

        // 3. 遍历整个矩阵的每一个格子 (i, j) 作为可能的“单词起点”
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 4. 从当前位置 (i, j) 开始尝试匹配单词的第 0 个字符
                boolean flag = check(board, visited, i, j, word, 0);
                // 5. 只要从某一个格子开始能找到完整路径，就直接返回 true
                if (flag) {
                    return true;
                }
            }
        }
        // 6. 如果所有格子都当过起点都失败了，则返回 false
        return false;
    }

    /**
     * 核心回溯递归函数：check
     * @param board   原始矩阵
     * @param visited 访问标记矩阵
     * @param i, j    当前所在的矩阵坐标
     * @param s       目标单词字符串
     * @param k       当前匹配到了单词的第 k 个字符（从 0 开始）
     */
    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        // [判断一]：当前格子字符与单词目标字符不匹配
        if (board[i][j] != s.charAt(k)) {
            // 直接判定此路不通
            return false;
        } 
        // [判断二]：字符匹配成功，且 k 已经是单词的最后一个索引
        else if (k == s.length() - 1) {
            // 说明整条路径已经完整对上了，返回 true
            return true;
        }

        // --- 执行到这里，说明当前字符匹配成功，需要继续往四周找第 k+1 个字符 ---

        // 7. 【标记】：将当前坐标 (i, j) 标记为已访问，防止在递归过程中“走回头路”
        visited[i][j] = true;

        // 8. 方向数组：定义上下左右四个偏移量，{行偏移, 列偏移}
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // 用于记录后续路径是否成功的标志位
        boolean result = false;

        // 9. 遍历四个方向进行深度优先搜索 (DFS)
        for (int[] dir : directions) {
            // 计算新坐标 (newi, newj)
            int newi = i + dir[0];
            int newj = j + dir[1];

            // 10. 边界检查：新坐标必须在矩阵范围内
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // 11. 状态检查：新坐标在当前这条路径中没被访问过
                if (!visited[newi][newj]) {
                    // 12. 【递归】：带着 k + 1 进入下一层，继续寻找下一个字母
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    // 13. 如果下一层返回 true，说明找到了解
                    if (flag) {
                        result = true;
                        // 找到一个解就够了，直接 break 跳出方向循环
                        break;
                    }
                }
            }
        }

        // 14. 【回溯关键步】：撤销标记
        // 这一步是灵魂：当以当前 (i, j) 为中心的所有路都试完了（或者找到解了），
        // 我们需要把当前格子的访问状态重置为 false，
        // 这样它才能被后续从其他起点出发的搜索路径再次使用。
        visited[i][j] = false;

        // 15. 返回最终结果
        return result;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        existSolution sol = new existSolution();

        // 读取行数和列数
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            char[][] board = new char[m][n];
            // 读取矩阵内容
            for (int i = 0; i < m; i++) {
                String row = sc.next();
                board[i] = row.toCharArray();
            }
            // 读取目标单词
            String word = sc.next();
            // 输出结果
            System.out.println(sol.exist(board, word));
        }
        sc.close();
    }
}