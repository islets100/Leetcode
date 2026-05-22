import java.util.Scanner;

/**
 * 题号：LeetCode 1143
 * 题目：最长公共子序列 (LCS)
 * 题目内容：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符相对顺序的情况下删除某些字符（也可以不删除任何字符）后形成的新字符串。
 * 例如，"ace" 是 "abcde" 的子集，但 "aec" 不是。
 * * 解题思路：二维动态规划
 * dp[i][j] 表示 text1[0...i-1] 和 text2[0...j-1] 的最长公共子序列长度。
 */
public class longestCommonSubsequenceSolution {

    /**
     * 算法实现：二维 DP
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // 1. 获取两个字符串的长度
        int m = text1.length();
        int n = text2.length();

        // 2. 创建 DP 数组。长度设为 m+1 和 n+1 是为了处理空字符串的情况。
        // dp[i][j] 对应的是 text1 的前 i 个字符和 text2 的前 j 个字符。
        // 默认初始化全为 0。
        int[][] dp = new int[m + 1][n + 1];

        // 3. 开始外层循环，遍历 text1 的每个字符
        for (int i = 1; i <= m; i++) {
            // 获取 text1 的当前字符，注意下标偏移 1
            char c1 = text1.charAt(i - 1);

            // 4. 开始内层循环，遍历 text2 的每个字符
            for (int j = 1; j <= n; j++) {
                // 获取 text2 的当前字符
                char c2 = text2.charAt(j - 1);

                // 5. 【核心判断】：如果当前两个字符相等
                if (c1 == c2) {
                    /**
                     * 6. 状态转移一：
                     * 既然这两个字符相同，那么最长公共子序列长度就是在
                     * 去掉这两个字符后的 LCS 基础上加 1。
                     */
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    /**
                     * 7. 状态转移二：如果字符不相等
                     * 我们无法同时使用 c1 和 c2。
                     * 我们取“text1 删掉当前字符”和“text2 删掉当前字符”两种情况下的最大值。
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 8. 最终 dp[m][n] 存储的就是两个完整字符串的 LCS 长度
        return dp[m][n];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象读取输入
        Scanner sc = new Scanner(System.in);
        longestCommonSubsequenceSolution sol = new longestCommonSubsequenceSolution();

        // 循环读取每一行（假设每行输入两个以空格分隔的字符串）
        while (sc.hasNext()) {
            String text1 = sc.next();
            String text2 = sc.next();
            
            // 调用算法并输出结果
            System.out.println(sol.longestCommonSubsequence(text1, text2));
        }

        // 资源清理
        sc.close();
    }
}