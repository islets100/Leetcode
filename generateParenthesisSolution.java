import java.util.*;

/**
 * 题号：LeetCode 22
 * 题目：括号生成
 * 内容：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class generateParenthesisSolution {

    /**
     * 主方法
     * @param n 括号对数
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        // 1. 定义路径数组，长度固定为 2n
        char[] path = new char[n * 2];
        
        // 2. 开启深度优先搜索
        // 参数：左括号已用数量，右括号已用数量，路径数组，结果集，总对数 n
        dfs(0, 0, path, ans, n);
        return ans;
    }

    /**
     * 递归函数
     * @param left  当前已填写的左括号数量
     * @param right 当前已填写的右括号数量
     * @param path  当前的字符数组路径
     * @param ans   结果仓库
     * @param n     总对数
     */
    private void dfs(int left, int right, char[] path, List<String> ans, int n) {
        // 【出口条件】：当右括号填够了 n 个，说明左括号肯定也填够了 n 个（因为有约束）
        // 此时 path 已经填满，直接转化为字符串存入结果
        if (right == n) {
            ans.add(new String(path));
            return;
        }

        // 【选择 1】：尝试填左括号
        // 只要左括号数量还没达到 n，就可以填
        if (left < n) {
            // 当前位置是 (left + right)，填入左括号
            path[left + right] = '(';
            // 递归：左括号数量加 1
            dfs(left + 1, right, path, ans, n);
        }

        // 【选择 2】：尝试填右括号
        // 只有当“已填的右括号”少于“已填的左括号”时，填右括号才是合法的
        if (right < left) {
            // 当前位置是 (left + right)，填入右括号
            path[left + right] = ')';
            // 递归：右括号数量加 1
            dfs(left, right + 1, path, ans, n);
        }
    }

    /**
     * ACM 主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        generateParenthesisSolution solution = new generateParenthesisSolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            List<String> result = solution.generateParenthesis(n);
            System.out.println(result);
        }
        sc.close();
    }
}