import java.util.*;

/**
 * 题号：LeetCode 139
 * 题目：单词拆分 (Word Break)
 * 题目内容：给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
 * 如果可以利用字典中出现的一个或多个单词拼接成 s ，返回 true。
 * 注意：不要求字典中所有的单词都在 s 中出现，且字典中的单词可以重复使用。
 */
public class wordBreakSolution {

    /**
     * 核心算法：动态规划
     * @param s 待拆分的字符串
     * @param wordDict 单词字典
     * @return 是否可以成功拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 1. 将 List 转换为 HashSet。
        // 为什么要转？因为 List.contains() 是 O(n) 复杂度，
        // 而 HashSet.contains() 是 O(1)，能极大提高查询速度。
        Set<String> wordDictSet = new HashSet<>(wordDict);

        // 2. 开辟 dp 数组，长度为 s.length() + 1。
        // dp[i] 代表字符串的前 i 个字符能否被成功拆分。
        // 多出来的那个 "+1" 正是为了存放 dp[0] 这个“空字符串”的基础状态。
        boolean[] dp = new boolean[s.length() + 1];

        // 3. 基础状态：空字符串可以被看作“已被成功拆分”。
        // 它是所有后续判断的“起点”和“地基”。
        dp[0] = true;

        // 4. 外层循环 i：代表我们要考察的字符串前缀的长度，从 1 逐渐增加到总长度。
        for (int i = 1; i <= s.length(); i++) {
            
            // 5. 内层循环 j：在 0 到 i 之间寻找一个“切分点”。
            // 将前 i 个字符切成两部分：前 j 个字符（旧状态）和 j 到 i 的子串（新单词）。
            for (int j = 0; j < i; j++) {
                
                /**
                 * 6. 【核心状态转移判断】：
                 * dp[j] == true 表示前 j 个字符已经可以被拆分了。
                 * wordDictSet.contains(s.substring(j, i)) 表示剩下的这部分刚好是一个完整的单词。
                 * 两个条件同时满足，说明前 i 个字符也能被拆分。
                 */
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    
                    // 7. 记录状态：前 i 个字符可以拆分
                    dp[i] = true;
                    
                    // 8. 既然已经确定前 i 个字符可以拆分，就没必要尝试其他的切分点 j 了，直接跳出。
                    break;
                }
            }
        }

        // 9. 返回 dp 数组的最后一位，即整个字符串 s 是否可以被成功拆分。
        return dp[s.length()];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        wordBreakSolution sol = new wordBreakSolution();

        while (sc.hasNextLine()) {
            // 第一行输入字符串 s
            String s = sc.nextLine();
            // 第二行输入字典单词，以空格分隔
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine();
            List<String> wordDict = Arrays.asList(line.split(" "));

            // 输出结果
            System.out.println(sol.wordBreak(s, wordDict));
        }
        sc.close();
    }
}