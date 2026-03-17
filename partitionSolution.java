import java.util.*;

/**
 * 题号：LeetCode 131
 * 题目：分割回文串 (Palindrome Partitioning)
 * 内容：给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 示例：输入 s = "aab" -> 输出 [["a","a","b"],["aa","b"]]
 */
public class partitionSolution {

    /**
     * 主逻辑方法：初始化并启动回溯
     * @param s 输入字符串
     * @return 所有可能的分割方案
     */
    public List<List<String>> partition(String s) {
        // 1. 创建结果集 ans，用于存放所有成功的分割组合
        List<List<String>> ans = new ArrayList<>();
        
        // 2. 创建 path 列表，作为“临时篮子”，存放当前路径中切下来的一个个回文子串
        List<String> path = new ArrayList<>();
        
        // 3. 开启深度优先搜索（DFS）
        // 参数：起始切割位置(0)，原始字符串，临时路径，结果集
        dfs(0, s, path, ans);
        
        // 4. 返回最终所有满足条件的分割方案
        return ans;
    }

    /**
     * 递归函数：深度优先搜索
     * @param i    当前这一层切割的“起始位置”
     * @param s    原始字符串
     * @param path 当前已切割好的回文串列表
     * @param ans  总结果集
     */
    private void dfs(int i, String s, List<String> path, List<List<String>> ans) {
        // [递归边界/出口]：如果起始位置 i 已经达到了字符串的末尾
        // 说明我们已经成功地将整个字符串切成了若干段回文串
        if (i == s.length()) {
            // 拍照留存：必须创建 path 的新拷贝放入结果集
            ans.add(new ArrayList<>(path));
            return;
        }

        // [递归主体]：枚举子串的“结束位置” j
        // 我们尝试在 i 和 i 之后的每一个位置画一刀
        for (int j = i; j < s.length(); j++) {
            
            // 1. 【核心判断】：判断当前切下来的子串 s[i...j] 是否为回文串
            // 注意：isPalindrome 的范围是左闭右闭 [i, j]
            if (isPalindrome(s, i, j)) {
                
                // 2. 【做选择】：如果是回文串，则切下这一块，放入篮子
                // substring(i, j + 1) 是左闭右开，正好截取到索引 j 的字符
                path.add(s.substring(i, j + 1));
                
                // 3. 【递归】：从 j + 1 的位置开始，继续尝试切割剩下的字符串
                dfs(j + 1, s, path, ans);
                
                // 4. 【撤销选择/回溯】：
                // 当从递归返回时，说明以当前 j 为结尾的这种切割方式已经探索完毕
                // 我们需要把刚才加入篮子的子串拿掉，尝试换个更长的 j 来切割
                // 这里使用 path.remove(path.size() - 1) 或者 Java 21 的 removeLast()
                path.remove(path.size() - 1);
            }
            // 如果 s[i...j] 不是回文串，则循环继续，尝试把 j 往后移，切一个更长的子串看看
        }
    }

    /**
     * 辅助函数：判断字符串 s 在 [left, right] 范围内是否为回文
     * @param s     原始字符串
     * @param left  左边界索引
     * @param right 右边界索引
     * @return 是否为回文
     */
    private boolean isPalindrome(String s, int left, int right) {
        // 使用双指针法：一头一尾向中间靠拢
        while (left < right) {
            // 只要左右字符不等，就一定不是回文
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        // 指针相遇或交叉，说明匹配成功
        return true;
    }

    /**
     * ACM 模式主函数：处理输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        partitionSolution sol = new partitionSolution();

        while (sc.hasNext()) {
            // 读取输入的字符串
            String s = sc.next();
            // 执行算法
            List<List<String>> result = sol.partition(s);
            // 打印结果，例如：[[a, a, b], [aa, b]]
            System.out.println(result);
        }
        sc.close();
    }
}