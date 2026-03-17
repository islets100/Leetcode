import java.util.Scanner;

/**
 * 题号：LeetCode 74
 * 题目：搜索二维矩阵 (Search a 2D Matrix)
 * 题目内容：给你一个满足下述两条属性的 m x n 整数矩阵：
 * 1. 每行中的整数从左到右按非严格递增顺序排列。
 * 2. 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * 要求：时间复杂度必须为 O(log(m * n))。
 */
public class TFsearchMatrixSolution {

    /**
     * 核心算法：将二维矩阵逻辑上视为一维数组进行二分查找
     * @param matrix 输入的二维矩阵
     * @param target 目标值
     * @return 是否找到目标值
     */
    public boolean TFsearchMatrix(int[][] matrix, int target) {
        // 1. 获取矩阵的行数 m 和列数 n
        int m = matrix.length;
        int n = matrix[0].length;
        
        // 2. 初始化二分查找的范围。将二维矩阵看作长度为 m * n 的一维数组
        // 索引范围从 0 到 m * n - 1
        int low = 0, high = m * n - 1;
        
        // 3. 开始标准二分查找循环
        while (low <= high) {
            // 4. 计算中点索引，使用防溢出写法并配合位运算优化
            int mid = ((high - low) >> 1) + low;
            
            // 5. 【核心变换】：将一维索引 mid 映射回二维矩阵的坐标 (row, col)
            // 行坐标 = mid / 总列数
            // 列坐标 = mid % 总列数
            // 这一点是利用了矩阵按行存储的特性
            int x = matrix[mid / n][mid % n];
            
            // 6. 比较当前值与目标值
            if (x < target) {
                // 如果当前值太小，说明目标在右侧，收缩左边界
                low = mid + 1;
            } else if (x > target) {
                // 如果当前值太大，说明目标在左侧，收缩右边界
                high = mid - 1;
            } else {
                // 7. 找到了目标值，直接返回 true
                return true;
            }
        }
        
        // 8. 循环结束仍未找到，返回 false
        return false;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TFsearchMatrixSolution sol = new TFsearchMatrixSolution();

        // 读取行数和列数
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            
            int[][] matrix = new int[m][n];
            // 填充矩阵
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
            
            // 读取目标值
            int target = sc.nextInt();
            
            // 执行算法并输出结果
            System.out.println(sol.TFsearchMatrix(matrix, target));
        }
        sc.close();
    }
}