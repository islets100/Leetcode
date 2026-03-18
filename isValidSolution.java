import java.util.*;

/**
 * 题号：LeetCode 20
 * 题目：有效括号 (Valid Parentheses)
 * 题目内容：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * * 解题思路：栈 (Stack)
 * - 括号匹配具有“后进先出”的特性：最后一个左括号必须最先被匹配。
 * - 使用 Map 存储左括号到右括号的映射，简化查找逻辑。
 * - 使用占位符 '?' 预填充栈底，优雅地处理边界情况（防止空栈弹出异常）。
 */
public class isValidSolution {

    // 1. 定义静态常量 Map，用于存储括号的配对关系
    // Key 为左括号，Value 为对应的右括号
    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
        put('?', '?'); // 占位符配对，用于逻辑保底
    }};

    /**
     * 算法实现：辅助栈法
     * @param s 输入字符串
     * @return 是否为有效括号
     */
    public boolean isValid(String s) {
        // 2. 剪枝判断：
        // 如果字符串不为空，且第一个字符就是右括号（Map 不包含该 Key），显然无效。
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) {
            return false;
        }

        // 3. 创建 Deque 作为栈使用（ArrayDeque 性能优于 Stack 类和 LinkedList）
        Deque<Character> stack = new ArrayDeque<>();
        
        // 4. 关键点：在栈底压入一个占位符 '?'
        // 这样在遇到第一个右括号执行 pop 时，栈永远不会为空，且匹配必然失败（除非右括号也是 '?'）。
        stack.push('?');

        // 5. 将字符串转换为字符数组并遍历
        for (Character c : s.toCharArray()) {
            // 6. 如果当前字符 c 是 Map 中的 Key，说明它是左括号
            if (map.containsKey(c)) {
                // 将左括号压入栈顶
                stack.push(c);
            } else {
                // 7. 如果当前字符是右括号：
                // a. 弹出栈顶的左括号：stack.pop()
                // b. 获取该左括号对应的目标右括号：map.get(...)
                // c. 检查目标是否与当前字符 c 相等
                if (map.get(stack.pop()) != c) {
                    // 如果不相等，说明括号类型不匹配或顺序错误
                    return false;
                }
            }
        }

        // 8. 遍历结束后，检查栈内是否只剩下初始的占位符 '?'
        // 如果 size == 1，说明所有的括号都已正确匹配并弹出。
        return stack.size() == 1;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取控制台输入
        Scanner sc = new Scanner(System.in);
        isValidSolution sol = new isValidSolution();

        // 循环读取每一行输入字符串
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // 如果输入为空行，则跳过
            if (input.trim().isEmpty()) continue;
            
            // 执行并输出结果
            System.out.println(sol.isValid(input));
        }

        // 资源清理
        sc.close();
    }
}