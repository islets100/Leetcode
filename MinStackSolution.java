import java.util.*;

/**
 * 题号：LeetCode 155
 * 题目：最小栈 (Min Stack)
 * 题目内容：设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * - push(val) 将元素 val 推入栈中。
 * - pop() 删除栈顶的元素。
 * - top() 获取栈顶元素。
 * - getMin() 检索栈中的最小元素。
 * * 核心思路：双栈法
 * 1. 使用一个主栈 xStack 存放实际数据。
 * 2. 使用一个辅助栈 minStack 存放对应的“当前最小值”。
 * 3. 两个栈保持同步：每压入一个数，minStack 就压入【当前值】与【旧最小值】中的较小者。
 */
public class MinStackSolution {

    // 1. 定义两个 Deque 接口的实现，用于模拟栈操作
    // ArrayDeque 性能优于 LinkedList 和 Stack 类
    private Deque<Integer> xStack;   // 数据栈
    private Deque<Integer> minStack; // 辅助栈（存储最小值序列）

    /**
     * 初始化构造函数
     */
    public MinStackSolution() {
        xStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        
        // 2. 关键点：在最小栈中预压入一个最大整数
        // 这样在第一次 push 时，Math.min(val, Integer.MAX_VALUE) 必然等于 val
        minStack.push(Integer.MAX_VALUE);
    }

    /**
     * 入栈操作
     * @param val 待压入的数值
     */
    public void push(int val) {
        // 3. 数据栈正常入栈
        xStack.push(val);
        
        // 4. 辅助栈同步入栈：
        // 比较当前值 val 和 辅助栈顶（当前的最小值），
        // 压入两者中的较小值，确保辅助栈顶永远是全局最小值。
        int currentMin = minStack.peek();
        minStack.push(Math.min(val, currentMin));
    }

    /**
     * 出栈操作
     */
    public void pop() {
        // 5. 两个栈必须同步弹出，保持长度和状态一致
        xStack.pop();
        minStack.pop();
    }

    /**
     * 获取栈顶元素
     * @return 数据栈的栈顶值
     */
    public int top() {
        return xStack.peek();
    }

    /**
     * 获取栈中最小值
     * @return 辅助栈的栈顶值
     */
    public int getMin() {
        return minStack.peek();
    }

    /**
     * ACM 模式主函数：模拟测试
     */
    public static void main(String[] args) {
        // 初始化最小栈
        MinStackSolution minStack = new MinStackSolution();
        
        // 模拟一系列操作
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        
        System.out.println("当前最小值: " + minStack.getMin()); // 返回 -3
        
        minStack.pop();
        System.out.println("弹出后栈顶: " + minStack.top());    // 返回 0
        System.out.println("弹出后最小值: " + minStack.getMin()); // 返回 -2
    }
}