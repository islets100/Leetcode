import java.util.*;

/**
 * 题号：LeetCode 17
 * 题目：电话号码的字母组合
 * 内容：给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class letterCombinationsSolution {

    /**
     * 主方法：初始化并启动回溯
     * @param digits 输入的数字字符串，例如 "23"
     */
    public List<String> letterCombinations(String digits) {
        // 1. 创建结果集：用于存放所有生成的字母组合
        List<String> combinations = new ArrayList<String>();
        
        // 2. 边界检查：如果输入字符串长度为 0，根据题意应返回空列表
        if (digits.length() == 0) {
            return combinations;
        }

        // 3. 定义映射表：使用双括号初始化 HashMap，建立数字字符到字母字符串的对应关系
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");  put('3', "def");  put('4', "ghi");
            put('5', "jkl");  put('6', "mno");  put('7', "pqrs");
            put('8', "tuv");  put('9', "wxyz");
        }};

        // 4. 调用回溯算法：
        // 参数依次为：结果列表、映射表、输入字符串、当前处理的数字索引(0)、临时路径缓存(StringBuffer)
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        
        // 5. 返回最终生成的所有组合
        return combinations;
    }

    /**
     * 回溯递归函数：深度优先搜索所有组合
     * @param combinations 结果列表，存储所有完成的组合
     * @param phoneMap     数字到字母的映射表
     * @param digits       输入的原始数字串
     * @param index        当前正在处理 digits 里的第几个数字（树的深度）
     * @param combination  当前已经拼凑出来的字母路径（像是一个半成品）
     */
    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        
        // 【递归出口】：如果当前的索引等于输入数字串的长度
        // 说明我们已经为每一个按下的数字都挑选了一个对应的字母
        if (index == digits.length()) {
            // combination.toString() 会创建一个全新的字符串对象（快照）
            // 将这个完成的成品放入结果集
            combinations.add(combination.toString());
        } else {
            // 【当前层的处理逻辑】：
            
            // 1. 获取当前索引处的数字字符，比如 '2'
            char digit = digits.charAt(index);
            
            // 2. 根据数字从映射表中拿到对应的候选字母串，比如 "abc"
            String letters = phoneMap.get(digit);
            
            // 3. 获取该数字对应的字母数量
            int lettersCount = letters.length();
            
            // 4. 遍历这些字母，分别进行尝试（分叉路径）
            for (int i = 0; i < lettersCount; i++) {
                
                // 【做选择】：把当前的字母 append（追加）到我们临时的“半成品”末尾
                combination.append(letters.charAt(i));
                
                // 【递归调用】：处理下一个数字（即 index + 1），进入更深的一层
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                
                // 【撤销选择/回溯】：这是最重要的步骤！
                // 当递归函数返回时，意味着刚才选这个字母所能产生的所有组合都已经尝试完了。
                // 我们需要把刚刚 append 进去的那个字母删掉，
                // 这样下一轮循环才能在同一个位置（index位）尝试放入不同的字母。
                // index 位刚好对应我们刚刚 append 进去的那个字符的位置。
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * ACM 模式主函数：读取输入并打印结果
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        letterCombinationsSolution solution = new letterCombinationsSolution();

        // 循环读取每一行输入（ACM 模式常见写法）
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            // 如果输入不为空则执行算法
            if (!input.isEmpty()) {
                List<String> result = solution.letterCombinations(input);
                // 直接打印 List，Java 会自动调用 toString 格式化输出
                System.out.println(result);
            }
        }
        // 关闭资源
        sc.close();
    }
}