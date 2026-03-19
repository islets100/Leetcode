import java.util.*;

/**
 * 题号：LeetCode 84
 * 题目：柱状图中最大的矩形 (Largest Rectangle in Histogram)
 * 题目内容：给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * * 核心思路：单调栈 (Monotonic Stack)
 * 1. 目标：找到每根柱子向左和向右能扩展到的第一个【矮于】自己的边界。
 * 2. 栈内存储：柱子的下标。
 * 3. 栈内特性：下标对应的柱子高度保持【单调递增】。
 * 4. 结算时机：当遇到一个比栈顶矮的柱子时，说明栈顶柱子的【右边界】找到了，开始计算其面积。
 */
public class largestRectangleAreaSolution {

    /**
     * 算法实现：单调栈
     * @param heights 柱子高度数组
     * @return 最大矩形面积
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        // 1. 基础判断：无柱子或只有一根柱子的情况
        if (len == 0) return 0;
        if (len == 1) return heights[0];

        int res = 0; // 用于记录全局最大面积
        
        // 2. 使用 Deque 实现栈。ArrayDeque 作为栈比 LinkedList 效率更高。
        // 栈内存放的是柱子的下标。
        Deque<Integer> stack = new ArrayDeque<>(len);

        // 3. 开始遍历每一根柱子，寻找它们的右边界
        for (int i = 0; i < len; i++) {
            /**
             * 4. 【核心逻辑】：如果当前柱子 i 的高度小于栈顶柱子的高度。
             * 这说明：栈顶柱子向右遇到了比它矮的柱子，它无法再向右扩展了。
             * 此时，i 就是栈顶柱子的【右边界】。
             */
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                // 5. 弹出栈顶下标，作为当前要计算高度的矩形
                int curHeight = heights[stack.pollLast()];

                /**
                 * 6. 优化处理：如果栈中还有和当前弹出的高度一样的柱子，
                 * 它们计算出的面积逻辑是一样的，直接一起弹出，避免重复计算。
                 */
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }

                // 7. 确定宽度 (Width)
                int curWidth;
                if (stack.isEmpty()) {
                    /**
                     * 8. 如果栈空了，说明刚才弹出的柱子左边没有任何柱子比它矮。
                     * 它的宽度就是从下标 0 到当前 i 的距离，即 i。
                     */
                    curWidth = i;
                } else {
                    /**
                     * 9. 如果栈不空，左边的第一个【矮于】自己的柱子就是当前的栈顶。
                     * 宽度公式：(右边界 i) - (左边界 stack.peekLast()) - 1
                     */
                    curWidth = i - stack.peekLast() - 1;
                }

                // 10. 计算面积，并更新全局最大值
                res = Math.max(res, curHeight * curWidth);
            }
            // 11. 将当前柱子下标入栈，保持栈内高度单调递增
            stack.addLast(i);
        }

        /**
         * 12. 【收尾处理】：遍历结束后，栈内可能还残留一些柱子。
         * 这些柱子一直到最后都没有遇到比它们矮的右边界，所以它们的右边界统一视为数组长度 len。
         */
        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            // 同样处理高度相等的情况
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            
            int curWidth;
            if (stack.isEmpty()) {
                // 这种柱子是全局最矮的，或者是左边无障碍的，宽度就是数组总长度 len
                curWidth = len;
            } else {
                // 宽度公式：(总长 len) - (左边界 stack.peekLast()) - 1
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }

        return res;
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        largestRectangleAreaSolution sol = new largestRectangleAreaSolution();

        System.out.println("请输入柱子数量 n：");
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] heights = new int[n];
            for (int i = 0; i < n; i++) {
                heights[i] = sc.nextInt();
            }
            System.out.println("最大矩形面积为：" + sol.largestRectangleArea(heights));
        }
        sc.close();
    }
}