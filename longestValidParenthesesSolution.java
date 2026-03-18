import java.util.*;

/**
 * 题号：LeetCode 32
 * 题目：最长有效括号 (Longest Valid Parentheses)
 * 题目内容：给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * * 核心思路：利用栈存储下标。
 * 1. 栈底元素始终记录“最后一个未匹配的右括号的下标”（即有效区间的左边界前一个位置）。
 * 2. 遇到 '('：将其下标入栈。
 * 3. 遇到 ')'：
 * a. 先弹出栈顶（尝试匹配）。
 * b. 若弹出后栈为空，说明当前的 ')' 没有左括号匹配，它成为了新的“边界起点”，将其下标入栈。
 * c. 若弹出后栈不为空，则当前有效长度 = 当前下标 i - 弹出后的新栈顶下标。
 */
public class longestValidParenthesesSolution {

    /**
     * 算法实现：基于栈的下标计数法
     * @param s 输入括号字符串
     * @return 最长有效子串长度
     */
    public int longestValidParentheses(String s) {
        // 1. 初始化最大长度为 0
        int maxans = 0;

        // 2. 创建一个双端队列作为栈使用，存储字符的下标 (Integer)
        Deque<Integer> stack = new LinkedList<Integer>();

        // 3. 【极其关键】：初始化压入 -1
        // 这个 -1 是为了处理边界情况。如果字符串从下标 0 开始就匹配成功，
        // 比如 "()"，i=1 时弹出 0，栈顶剩 -1，计算 1 - (-1) = 2，结果才正确。
        stack.push(-1);

        // 4. 遍历字符串的每一个字符
        for (int i = 0; i < s.length(); i++) {
            // 5. 如果当前字符是左括号 '('
            if (s.charAt(i) == '(') {
                // 将左括号的下标压入栈中，等待右括号来匹配
                stack.push(i);
            } else {
                // 6. 如果当前字符是右括号 ')'
                // 无论如何，先弹出一个元素（尝试与之前的左括号配对）
                stack.pop();

                // 7. 弹出后判断栈的状态
                if (stack.isEmpty()) {
                    // 如果栈空了，说明刚才弹出的不是左括号，而是之前的“边界下标”。
                    // 此时当前的右括号 i 无法被匹配，它成为了新的“合法区间左边界”。
                    // 我们将其下标压入栈底，作为以后计算长度的参照物。
                    stack.push(i);
                } else {
                    // 8. 如果栈不为空，说明当前右括号成功匹配了一个左括号！
                    // 有效长度计算公式：当前下标 i - 现在的栈顶下标。
                    // 现在的栈顶下标是“上一个没匹配上的右括号”或“上一个没匹配上的左括号”的位置。
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }

        // 9. 返回遍历过程中记录到的最大长度
        return maxans;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器处理输入
        Scanner sc = new Scanner(System.in);
        longestValidParenthesesSolution sol = new longestValidParenthesesSolution();

        // 循环读取每一行字符串输入
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // 如果输入为空行，则跳过
            if (input.trim().isEmpty()) continue;
            
            // 执行算法并输出结果
            System.out.println(sol.longestValidParentheses(input));
        }

        // 资源清理
        sc.close();
    }
}