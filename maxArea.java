/*
11.盛水最多的容器

给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。
*/

class maxAreaSolution {
    public int maxArea(int[] height) {
        // 1. 初始化
        // i: 左指针，从数组最左边开始 (下标 0)
        // j: 右指针，从数组最右边开始 (下标 长度-1)
        // res: 记录目前为止找到的最大面积
        int i = 0, j = height.length - 1, res = 0;

        // 2. 当左指针还在右指针左边时，持续循环
        while(i < j) {
            
            // 3. 这是一个三元运算符 (Condition ? Result1 : Result2)
            // 它的核心逻辑是：比较左右两根柱子，谁矮就处理谁
            res = height[i] < height[j] ? 
                
                // --- 情况 A：左边柱子 height[i] 较矮 ---
                // 面积 = 宽度 (j - i) * 高度 (较矮的 height[i])
                // Math.max 会把“新面积”和“旧纪录 res”对比，留下大的那个
                // height[i++] 表示：用完当前的 i 计算后，i 自动加 1 (左指针向右移)
                Math.max(res, (j - i) * height[i++]): 
                
                // --- 情况 B：右边柱子 height[j] 较矮或等高 ---
                // 面积 = 宽度 (j - i) * 高度 (较矮的 height[j])
                // height[j--] 表示：用完当前的 j 计算后，j 自动减 1 (右指针向左移)
                Math.max(res, (j - i) * height[j--]); 
        }

        // 4. 循环结束，返回最高纪录
        return res;
    }
}


public class maxArea {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        maxAreaSolution solution = new maxAreaSolution();
        System.out.println(solution.maxArea(height));
    }
}
