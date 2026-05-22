import java.util.Scanner;

/**
 * 题号：LeetCode 72
 * 题目：编辑距离 (Edit Distance)
 * 题目内容：给你两个单词 word1 和 word2，请返回将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行三种操作：插入一个字符、删除一个字符、替换一个字符。
 * * 解题思路：动态规划 (DP)
 * 1. 状态定义：dp[i][j] 表示将 word1 的前 i 个字符转换成 word2 的前 j 个字符所需的最少操作数。
 * 2. 状态转移：
 * - 若当前字符相等 (word1[i-1] == word2[j-1])：不需要操作，dp[i][j] = dp[i-1][j-1]。
 * - 若不相等：取 替换 (dp[i-1][j-1])、删除 (dp[i-1][j])、插入 (dp[i][j-1]) 三者的最小值 + 1。
 * 3. 初始状态：空字符串变成长度为 n 的字符串需要 n 次操作。
 */
public class minDistanceSolution {

    /**
     * 核心算法实现：二维动态规划
     * @param word1 源字符串
     * @param word2 目标字符串
     * @return 最少操作次数
     */
    public int minDistance(String word1, String word2) {
        // 1. 获取两个字符串的长度
        int n1 = word1.length();
        int n2 = word2.length();
        
        // 2. 创建 DP 数组，大小为 (n1+1) x (n2+1)
        // 多出来的这一行和一列是为了处理其中一个字符串为空的情况
        int[][] dp = new int[n1 + 1][n2 + 1];

        // 3. 初始化第一列：word2 为空。
        // 要把长度为 i 的 word1 变成空串，只能执行 i 次“删除”操作。
        for (int i = 1; i <= n1; i++) {
            // dp[i][0] 代表 word1 前 i 个字符变为空串的步数
            dp[i][0] = dp[i - 1][0] + 1;
        }

        // 4. 初始化第一行：word1 为空。
        // 要把空串变成长度为 j 的 word2，只能执行 j 次“插入”操作。
        for (int j = 1; j <= n2; j++) {
            // dp[0][j] 代表空串变为 word2 前 j 个字符的步数
            dp[0][j] = dp[0][j - 1] + 1;
        }

        // 5. 开始双重循环，遍历 word1 和 word2 的每一个字符组合
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                
                // 6. 比较当前位置的字符是否相同（注意下标从 0 开始，所以是 i-1 和 j-1）
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    /**
                     * 7. 如果字符相同，说明当前位不需要任何操作。
                     * 最小距离直接继承“去掉这两个相等字符”后的状态。
                     */
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /**
                     * 8. 如果字符不相同，我们需要在三种操作中选代价最小的一种：
                     * - dp[i - 1][j - 1]: 代表“替换”操作，修改 word1[i-1] 变成 word2[j-1]
                     * - dp[i - 1][j]: 代表“删除”操作，删掉 word1 当前多余的字符
                     * - dp[i][j - 1]: 代表“插入”操作，在 word1 后面插入一个与 word2[j-1] 匹配的字符
                     * 最后 +1 是代表当前这一次的操作步数。
                     */
                    dp[i][j] = Math.min(dp[i - 1][j - 1], 
                               Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        // 9. 最终 dp[n1][n2] 存储的就是将 word1 完整转换为 word2 的最少步数
        return dp[n1][n2];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 使用 Scanner 读取输入
        Scanner sc = new Scanner(System.in);
        minDistanceSolution sol = new minDistanceSolution();

        // 循环读取输入流中的单词对
        while (sc.hasNext()) {
            // 输入格式通常为：word1 word2
            String word1 = sc.next();
            String word2 = sc.next();
            
            // 执行算法并输出结果
            System.out.println(sol.minDistance(word1, word2));
        }

        // 资源清理
        sc.close();
    }
}