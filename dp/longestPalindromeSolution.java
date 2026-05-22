import java.util.Scanner;

/**
 * 题号：LeetCode 5
 * 题目：最长回文子串 (Longest Palindromic Substring)
 * 题目内容：给你一个字符串 s，找到 s 中最长的回文子串。
 * * 解题思路：动态规划 (DP)
 * 1. 状态定义：dp[l][r] 表示字符串 s 从下标 l 到 r 的子串是否为回文串。
 * 2. 状态转移：若 s[l] == s[r]，则 dp[l][r] 是否为回文取决于内部 dp[l+1][r-1]。
 * 3. 循环顺序：由于 dp[l][r] 依赖左下角的 dp[l+1][r-1]，所以我们需要先固定右端点 r，再遍历左端点 l。
 */
public class longestPalindromeSolution {

    /**
     * 算法实现：二维动态规划
     * @param s 输入字符串
     * @return 最长的回文子串
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        // 1. 基础判断：如果长度小于 2，直接返回原字符串
        if (n < 2) return s;

        // 2. 创建 DP 表，存储子串的回文性（true/false）
        boolean[][] dp = new boolean[n][n];
        
        // 初始化记录最长子串的变量
        int start = 0;   // 最长回文串的起始索引
        int maxLen = 1;  // 最长回文串的长度

        // 3. 外层循环：枚举子串的右端点 r
        for (int r = 0; r < n; r++) {
            
            // 4. 内层循环：枚举子串的左端点 l，l 必须小于等于 r
            for (int l = 0; l <= r; l++) {
                
                // 5. 核心判断一：头尾字符必须相等
                if (s.charAt(r) == s.charAt(l)) {
                    
                    /**
                     * 6. 核心判断二：
                     * r - l <= 2：表示子串长度为 1, 2 或 3（如 "a", "aa", "aba"）。
                     * 这种情况下，只要头尾相等，中间部分不需要额外判断，必为回文。
                     * dp[l + 1][r - 1]：若子串更长，则看去掉头尾后的内部是否是回文。
                     */
                    if (r - l <= 2 || dp[l + 1][r - 1]) {
                        // 记录当前子串为回文
                        dp[l][r] = true;

                        // 7. 计算当前回文串长度
                        int len = r - l + 1;
                        
                        // 8. 如果当前长度超过了历史记录，更新起点和最大长度
                        if (len > maxLen) {
                            maxLen = len;
                            start = l;
                        }
                    }
                }
                // 若 s[r] != s[l]，dp[l][r] 默认为 false，无需额外操作
            }
        }

        // 9. 使用 substring(start, end) 截取结果，注意 Java 的截取是左闭右开
        return s.substring(start, start + maxLen);
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 使用 Scanner 读取标准输入
        Scanner sc = new Scanner(System.in);
        longestPalindromeSolution sol = new longestPalindromeSolution();

        // 循环读取每一行字符串输入
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // 过滤空行
            if (input.trim().isEmpty()) continue;
            
            // 调用算法并打印最长回文子串
            System.out.println(sol.longestPalindrome(input));
        }

        // 关闭扫描器
        sc.close();
    }
}