import java.util.*;

/**
 * 题号：LeetCode 118
 * 题目：杨辉三角 (Pascal's Triangle)
 * 内容：给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 */
public class generateSolution {

    public List<List<Integer>> generate(int numRows) {
        // 1. 创建大容器 c，类型是 List 的 List。
        // 指定容量为 numRows，可以稍微提高效率。
        List<List<Integer>> c = new ArrayList<>(numRows);
        
        // 2. 特殊处理第一行：杨辉三角的第一行永远只有一个数字 1。
        // List.of(1) 会创建一个不可变的集合 [1]。
        c.add(List.of(1));

        // 3. 从第二行（索引 i = 1）开始循环构造，直到第 numRows 行。
        for (int i = 1; i < numRows; i++) {
            
            // 4. 创建当前行的 List，容量为 i + 1。
            // 例如第 2 行（索引 1）有 2 个元素，第 3 行（索引 2）有 3 个元素。
            List<Integer> row = new ArrayList<>(i + 1);

            // 5. 每一行的开头：固定为 1。
            row.add(1);

            // 6. 构造中间的元素：
            // 中间元素的索引 j 从 1 开始，直到倒数第二个位置（j < i）。
            for (int j = 1; j < i; j++) {
                // 7. 【核心逻辑】：当前位置的值 = 上一行的左肩数字 + 上一行的右肩数字。
                // 上一行是 c.get(i - 1)。
                // 左肩索引是 j - 1，右肩索引是 j。
                row.add(c.get(i - 1).get(j - 1) + c.get(i - 1).get(j));
            }

            // 8. 每一行的结尾：固定为 1。
            row.add(1);

            // 9. 将构造好的这一行存入大容器 c 中。
            c.add(row);
        }

        // 10. 返回构造完成的杨辉三角。
        return c;
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        generateSolution sol = new generateSolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            List<List<Integer>> result = sol.generate(n);
            
            // 格式化输出
            for (List<Integer> row : result) {
                System.out.println(row);
            }
        }
        sc.close();
    }
}