import java.util.*;

/*
73. 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0 ，
则将其所在行和列的所有元素都设为 0 。
请使用 原地 算法。
*/

class setZeorsSolution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;    // 矩阵的行数
        int n = matrix[0].length; // 矩阵的列数
        
        // --- 1. 备份第一行和第一列的“清白” ---
        // 因为我们稍后要用第一行和第一列存“标记”，
        // 所以得先记住它们原本自己是不是带 0，以免信息丢失。
        boolean flagCol0 = false, flagRow0 = false;

        // 检查第一列（第 0 列）原本是否有 0
        for(int i = 0; i < m ; i++){
            if(matrix[i][0] == 0){
                flagCol0 = true; // 只要有一个 0，整个第一列最后都要变 0
            }
        }

        // 检查第一行（第 0 行）原本是否有 0
        for(int i = 0; i < n ; i++){
            if(matrix[0][i] == 0){
                flagRow0 = true; // 只要有一个 0，整个第一行最后都要变 0
            }
        }

        // --- 2. 使用第一行和第一列做标记（草稿纸阶段） ---
        // 遍历矩阵内部（从下标 1 开始）
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    // 如果发现 matrix[i][j] 是 0，
                    // 就跑到行首标记：这一行全完蛋了
                    // 再跑到列首标记：这一列也全完蛋了
                    matrix[i][0] = 0; 
                    matrix[0][j] = 0;
                }
            }
        }

        // --- 3. 根据标记位，置零内部元素 ---
        // 同样从下标 1 开始，参考刚才打的“勾”
        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j ++){
                // 只要看到对应的行首是 0，或者列首是 0
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0; // 该位置变 0
                }
            }
        }

        // --- 4. 最后处理第一行和第一列自己 ---
        // 如果 flagCol0 为 true，说明最开始第一列里就有 0，现在把整列刷成 0
        if (flagCol0){
            for (int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }

        // 如果 flagRow0 为 true，说明最开始第一行里就有 0，现在把整行刷成 0
        if (flagRow0){
            for (int j = 0; j < n; j++){
                matrix[0][j] = 0;
            }
        }
    }
}
public class setZeors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取行數和列數
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];

        // 讀取矩陣元素
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 調用解法類處理
        setZeorsSolution solution = new setZeorsSolution();
        solution.setZeroes(matrix);

        // 輸出處理後的矩陣：每行輸出一行元素，元素之間用空格分隔
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        scanner.close();
    }
}
