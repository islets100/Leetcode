import java.util.*;

/**
 * 题号：LeetCode 763
 * 题目：划分字母区间 (Partition Labels)
 * 题目内容：字符串 s 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 同一字母最多出现在一个片段中。返回一个表示每个字符串片段长度的列表。
 * * 核心逻辑（贪心策略）：
 * 1. 预处理：记录每个字母最后一次出现的下标。
 * 2. 确定边界：遍历字符串，维护一个当前片段的“最远预期终点” end。
 * 3. 切分点：当你走到的位置 i 恰好等于 end 时，说明当前片段内的所有字母
 * 都再也不会在后面出现了，这就是一个完美的切分点。
 */
public class partitionLabelsSolution {

    /**
     * 算法实现：双指针 + 贪心
     * @param s 输入字符串
     * @return 各个片段的长度列表
     */
    public List<Integer> partitionLabels(String s) {
        // 1. 创建一个长度为 26 的数组，记录 26 个小写字母各自最后一次出现的索引位置
        int[] last = new int[26];
        int length = s.length();
        
        // 2. 第一次遍历：填充 last 数组
        // 例如：s = "abaccb..."，字符 'a' 最后出现在索引 2，则 last['a'-'a'] = 2
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        // 3. 定义存放结果的列表
        List<Integer> partition = new ArrayList<Integer>();
        
        // 4. 定义双指针：
        // start：当前片段的起始位置
        // end：当前片段内所有字符中，最远的那个“最后出现位置”
        int start = 0, end = 0;

        // 5. 第二次遍历：决定在哪里切一刀
        for (int i = 0; i < length; i++) {
            /**
             * 6. 【贪心更新边界】：
             * 每遇到一个新字符，都要看它的“最后位置”是不是比当前的 end 还要远。
             * 如果更远，就必须把 end 往后推，以包含该字符的所有出现位置。
             */
            end = Math.max(end, last[s.charAt(i) - 'a']);

            /**
             * 7. 【切分判定】：
             * 如果当前下标 i 追上了我们维护的最远边界 end，
             * 说明从 start 到 i 这一段里的所有字母，在 i 之后都不会再出现了。
             */
            if (i == end) {
                // 8. 计算当前片段的长度：(末尾索引 - 起始索引 + 1)
                partition.add(end - start + 1);
                
                // 9. 更新下一个片段的起点为当前切分点的下一个位置
                start = end + 1;
            }
        }
        
        // 10. 返回最终的所有片段长度
        return partition;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        partitionLabelsSolution sol = new partitionLabelsSolution();

        // 持续读取输入
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // 过滤空行
            if (input.trim().isEmpty()) continue;
            
            // 执行算法并输出
            List<Integer> result = sol.partitionLabels(input);
            System.out.println(result);
        }
        sc.close();
    }
}