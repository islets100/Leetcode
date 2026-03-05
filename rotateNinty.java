import java.util.*;
/*
48. 旋转图像
给定一个 n × n 的二维矩阵 matrix 表示一个图像。
请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。
请不要 使用另一个矩阵来旋转图像。
*/

class rotateNintySolution {
    public void rotate(int[][] matrix) {
        int n = matrix.length; // 获取矩阵的阶数（n * n）

        // --- 第一阶段：水平轴翻转（上下对调） ---
        // 只需要遍历前一半的行（n / 2），否则换过去又换回来了
        for(int i = 0; i < n / 2; i ++){
            // 每一行里的所有列都要参与对调
            for(int j = 0; j < n; j ++){
                // 使用 temp 变量交换两个位置的数值
                int temp = matrix[i][j];
                // 当前行 i 对应的下方镜像行是 n - i - 1
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        
        // 此时矩阵已经完成了上下翻转

        // --- 第二阶段：主对角线翻转（行列对调/矩阵转置） ---
        // 遍历整个矩阵
        for (int i = 0; i < n; i++){
            // 注意：j 的范围是 0 到 i（只遍历下三角部分）
            // 如果 j 也遍历到 n，那么元素会被交换两次，导致原地不动
            for(int j = 0; j < i; j++){
                // 交换 matrix[i][j] 和 matrix[j][i]
                // 比如把 (1, 0) 的位置和 (0, 1) 的位置对调
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
public class rotateNinty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取矩陣階數
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        // 讀取矩陣元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 調用解法類，旋轉矩陣
        rotateNintySolution solution = new rotateNintySolution();
        solution.rotate(matrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i > 0){
                    System.out.print(" ");
                }
                System.out.print( matrix[i][j] );
            }
            System.out.println();
        }
        scanner.close();
    }
}
