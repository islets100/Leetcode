import java.util.Scanner;

/**
 * 题号：LeetCode 169
 * 题目：多数元素 (Majority Element)
 * 题目内容：给定一个大小为 n 的数组 nums ，返回其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 进阶要求：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class majorityElementSolution {

    /**
     * 核心算法：摩尔投票法 (Boyer-Moore Voting Algorithm)
     * @param nums 输入的整数数组
     * @return 数组中的多数元素
     */
    public int majorityElement(int[] nums) {
        // 1. 初始化计数器 count。它代表当前候选人的“净支持率”。
        int count = 0;
        
        // 2. 初始化候选人 candidate。
        // 我们用 Integer 是为了处理一些初始化逻辑，但在本题中直接用 int 也可以。
        int candidate = 0;

        // 3. 遍历数组中的每一个数字。
        for (int num : nums) {
            
            // 4. 【逻辑分支一】：如果当前计数器为 0
            // 这意味着之前的候选人已经被“抵消”完了，或者是刚开始。
            // 我们需要重新选一个候选人。
            if (count == 0) {
                candidate = num;
            }

            // 5. 【逻辑分支二】：更新计数器
            // 如果当前的数字等于候选人，票数 +1（支持）。
            // 如果当前的数字不等于候选人，票数 -1（反对/抵消）。
            count += (num == candidate) ? 1 : -1;
        }

        // 6. 最终剩下的 candidate 就是多数元素。
        // 注意：题目保证了多数元素一定存在，所以不需要二次检查。
        return candidate;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        majorityElementSolution sol = new majorityElementSolution();

        // 循环读取数据
        while (sc.hasNextInt()) {
            // 第一步：读取数组长度 n
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 第二步：填充数组内容
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 第三步：调用算法并输出
            System.out.println(sol.majorityElement(nums));
        }
        
        // 关闭扫描器
        sc.close();
    }
}