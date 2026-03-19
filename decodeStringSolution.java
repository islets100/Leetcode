import java.util.*;

/**
 * 题号：LeetCode 394
 * 题目：字符串解码 (Decode String)
 * 题目内容：给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 示例：s = "3[a2[c]]" -> 解码为 "accaccacc"
 * * 解题思路：栈 (Stack)
 * 1. 遍历字符串，将数字、字母、左括号 '[' 依次入栈。
 * 2. 遇到右括号 ']' 时，说明当前这一层嵌套结束，开始出栈：
 * - 弹出字符直到遇到 '['，这些字符构成了需要重复的“基础子串”。
 * - 弹出 '[' 后，再弹出一个元素，这个元素一定是该子串的重复次数 k。
 * - 根据 k 构造重复后的新字符串，并将其重新压回栈中。
 */
public class decodeStringSolution {

    // 全局指针，用于记录当前处理到字符串 s 的哪个位置
    private int ptr;

    /**
     * 核心算法：单栈模拟解码
     * @param s 编码后的字符串
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        // 使用 LinkedList 作为栈（虽然 Deque 更推荐，但为了完全对应官方题解逻辑使用 LinkedList）
        // stk.addLast(e) 相当于入栈，stk.removeLast() 相当于出栈
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        // 1. 遍历整个字符串
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);

            // 2. 如果是数字：说明是一个重复次数 k 的开始
            if (Character.isDigit(cur)) {
                // 调用辅助函数获取完整的数字（处理如 "100" 这种多位数）并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } 
            // 3. 如果是字母或者是左括号 '['：直接进栈，等待后续处理
            else if (Character.isLetter(cur) || cur == '[') {
                stk.addLast(String.valueOf(s.charAt(ptr++))); 
            } 
            // 4. 如果是右括号 ']'：触发解码逻辑
            else {
                // 指针跳过 ']'
                ++ptr; 
                
                // sub 用于存放当前括号内的所有字母片段
                LinkedList<String> sub = new LinkedList<String>();
                
                // 5. 不断弹出栈顶元素，直到遇到左括号 '[' 为止
                while (!"[".equals(stk.peekLast())) {
                    // 将弹出的片段存入 sub 列表
                    sub.addLast(stk.removeLast());
                }
                
                // 6. 重要：因为栈是后进先出，弹出的片段顺序是反的，需要反转恢复正常顺序
                Collections.reverse(sub);
                
                // 7. 将左括号 '[' 从栈中弹出（它已经完成了它的使命）
                stk.removeLast();
                
                // 8. 此时栈顶剩下的第一个元素一定是刚才存入的数字（重复次数 k）
                int repTime = Integer.parseInt(stk.removeLast());
                
                // 9. 构造重复后的字符串
                StringBuilder t = new StringBuilder();
                String content = getString(sub); // 先将 sub 列表拼接成完整的子串
                
                // 根据 repTime 进行重复拼接
                while (repTime-- > 0) {
                    t.append(content);
                }
                
                // 10. 【核心关键】：将构造好的新字符串重新压回栈中
                // 为什么要压回去？因为它可能还是外层更大括号的一部分！
                stk.addLast(t.toString());
            }
        }

        // 11. 最后栈里剩下的所有片段，就是解码完成的结果，拼接并返回
        return getString(stk);
    }

    /**
     * 辅助函数：连续读取数字字符
     * 因为数字可能是多位的（如 "12["），不能只读一个字符
     */
    public String getDigits(String s) {
        StringBuilder ret = new StringBuilder();
        while (ptr < s.length() && Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    /**
     * 辅助函数：将列表中的字符串片段依次拼接成一个大字符串
     */
    public String getString(LinkedList<String> v) {
        StringBuilder ret = new StringBuilder();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        decodeStringSolution sol = new decodeStringSolution();

        // 持续读取输入
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            
            // 执行解码并打印结果
            System.out.println(sol.decodeString(line));
        }
        sc.close();
    }
}