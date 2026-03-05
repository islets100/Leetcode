import java.util.*;

/*
238.除了自身以外数组的乘积
给你一个整数数组 nums，返回 数组 answer ，
其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在
  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
*/

class productExceptSelfSolution {
    // 动态规划思路：利用前一个状态的计算结果来推导当前状态
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        // L[i] 代表：索引 i 左侧所有元素的乘积
        int[] L = new int[length];
        // R[i] 代表：索引 i 右侧所有元素的乘积
        int[] R = new int[length];
        // answer 代表：最终结果数组
        int[] answer = new int[length];

        // --- 第一步：计算左侧乘积列表 ---
        // 对于索引为 0 的元素，它左边没东西，所以初始化为 1
        L[0] = 1;
        for(int i = 1; i < length; i ++){
            // 重点：当前位置左边的乘积 = 它的前一个位置的数 * 前一个位置左边的乘积
            // 这样就不用每次都重新去乘一遍，实现了动态规划的“复用”
            L[i] = nums[i - 1] * L[i - 1];
        }

        // --- 第二步：计算右侧乘积列表 ---
        // 对于最后一个元素，它右边没东西，初始化为 1
        R[length - 1] = 1;
        // 注意：这里要从右往左倒着走
        for(int i = length - 2; i >= 0; i--){
            // 重点：当前位置右边的乘积 = 它的后一个位置的数 * 后一个位置右边的乘积
            R[i] = nums[i + 1] * R[i + 1];
        }

        // --- 第三步：合成最终结果 ---
        for(int i = 0; i < length; i++){
            // 索引 i 排除掉自己后的乘积 = 它左边的乘积 * 它右边的乘积
            answer[i] = L[i] * R[i];
        }

        return answer;
    }
}


public class ProductExceptSelf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取數組長度
        int n = scanner.nextInt();
        int[] nums = new int[n];

        // 讀取數組元素
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // 調用解法類
        productExceptSelfSolution solution = new productExceptSelfSolution();
        int[] answer = solution.productExceptSelf(nums);

        // 以一維陣列形式輸出結果，例如 [24, 12, 8, 6]
        System.out.println(Arrays.toString(answer));

        scanner.close();
    }
}
