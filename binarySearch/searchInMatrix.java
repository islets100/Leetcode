
/*
240.搜索二维矩阵
编写一个高效的算法来搜索 m x n 矩阵 matrix 
中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 */

import java.util.Scanner;

class searchMatrixSolution {
    // 主函数：在矩阵中搜索目标值
    public boolean searchMatrix(int[][] matrix, int target) {
        // 使用 for-each 循环遍历矩阵的每一行
        // row 代表当前正在处理的那一整行数组
        for(int[] row : matrix){
            // 对当前这一行执行二分查找
            int index = search(row, target);
            
            // 如果返回的索引 index >= 0，说明在这一行找到了目标值
            if(index >= 0){
                return true; // 只要找到一个，直接返回 true，结束程序
            }
        }
        // 如果遍历完所有行都没找到，返回 false
        return false;
    }

    // 辅助函数：标准的二分查找算法
    public int search(int[] nums, int target){
        int low = 0;                 // 查找范围的左边界
        int high = nums.length - 1;  // 查找范围的右边界
        
        // 只要左边界没超过右边界，就继续折半查找
        while (low <= high){
            // 计算中间索引，(high - low) / 2 + low 是为了防止 (high + low) 直接相加导致整数溢出
            int mid = (high - low) / 2 + low;
            int num = nums[mid]; // 拿到中间位置的数值
            
            if (num == target){
                return mid;      // 运气好，直接撞上目标值，返回下标
            } else if(num < target){
                low = mid + 1;   // 中间的数太小了，目标值肯定在右半边，左边界往右移
            } else {
                high = mid - 1;  // 中间的数太大了，目标值肯定在左半边，右边界往左移
            }
        }
        // 循环结束还没找到，返回 -1
        return -1;
    }
}
public class searchInMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取行數、列數和目標值
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        int[][] matrix = new int[m][n];

        // 讀取矩陣元素（已按行、列升序給出）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 調用解法類進行搜尋
        searchMatrixSolution solution = new searchMatrixSolution();
        boolean found = solution.searchMatrix(matrix, target);

        // ACM 風格輸出：找到輸出 true，否則輸出 false
        System.out.println(found);

        scanner.close();
    }
}
