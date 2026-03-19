import java.util.*;

/**
 * 题号：LeetCode 739
 * 题目：每日温度 (Daily Temperatures)
 * 题目内容：给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * * 核心思路：单调栈 (Monotonic Stack)
 * 1. 我们维护一个栈，里面存放的是气温数组的【下标】。
 * 2. 栈内的元素对应的温度必须是【单调递减】的。
 * 3. 遍历到第 i 天时，如果第 i 天的温度比栈顶下标对应的温度高：
 * - 说明找到了栈顶元素“下一个更高温度”。
 * - 弹出栈顶，计算下标差值 (i - prevIndex)，存入结果。
 * 4. 否则，将当前下标 i 入栈。
 */
public class dailyTemperaturesSolution {

    /**
     * 算法实现：单调栈
     * @param temperatures 气温数组
     * @return 距离下一次升温的天数数组
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        // 1. 定义结果数组，Java 默认初始化 int 数组为 0，符合题目“无升温填0”的要求
        int[] ans = new int[length];

        /**
         * 2. 定义栈，存储下标。
         * 这里推荐使用 ArrayDeque 代替 LinkedList，因为它作为栈使用时性能更好。
         * 栈内存储的是下标，因为通过下标既能找到温度，又能计算天数差。
         */
        Deque<Integer> stack = new ArrayDeque<>();

        // 3. 开始遍历每一天的温度
        for (int i = 0; i < length; i++) {
            int currentTemp = temperatures[i];

            /**
             * 4. 【核心逻辑】：只要当前温度比栈顶温度高，就说明栈顶元素“守得云开见月明”了。
             * 这是一个循环过程，因为当前温度可能一次性比栈里好几天的温度都高。
             */
            while (!stack.isEmpty() && currentTemp > temperatures[stack.peek()]) {
                // 5. 弹出栈顶那个终于等到回暖的下标
                int prevIndex = stack.pop();
                
                // 6. 计算天数差距：当前下标 i 减去 过去那个下标 prevIndex
                ans[prevIndex] = i - prevIndex;
            }

            /**
             * 7. 无论是否触发了上面的弹出逻辑，当前的下标 i 都要入栈。
             * 入栈后，栈依然保持从栈底到栈顶对应的温度是单调递减的。
             */
            stack.push(i);
        }

        // 8. 返回结果数组
        return ans;
    }

    /**
     * ACM 模式主函数：用于在本地或 VS Code 中测试
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dailyTemperaturesSolution sol = new dailyTemperaturesSolution();

        System.out.println("请输入数组长度 n 和数组元素（以空格分隔）：");
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] temperatures = new int[n];
            for (int i = 0; i < n; i++) {
                temperatures[i] = sc.nextInt();
            }

            int[] result = sol.dailyTemperatures(temperatures);
            
            // 格式化输出结果
            System.out.println("结果为：" + Arrays.toString(result));
        }
        sc.close();
    }
}