import java.util.Scanner;

/**
 * 题号：LeetCode 136
 * 题目：只出现一次的数字 (Single Number)
 * 题目内容：给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 * 限制条件：你的算法应该具有线性时间复杂度 O(n) ，且不使用额外空间 O(1)。
 */
public class singleNumberSolution {

    /**
     * 核心算法：位运算 - 异或 (XOR)
     * @param nums 输入的整数数组
     * @return 只出现一次的那个数字
     */
    public int singleNumber(int[] nums) {
        // 1. 初始化结果变量 single 为 0。
        // 根据异或的恒等律：0 ^ x = x，所以 0 是异或运算的理想初始值。
        int single = 0;

        // 2. 遍历数组中的每一个数字。
        // 使用增强型 for 循环（foreach），代码更简洁。
        for (int num : nums) {
            /**
             * 3. 【核心逻辑】：将当前数字与结果变量进行异或运算。
             * 符号 '^' 是 Java 中的按位异或运算符。
             * 原理：
             * 如果 num 是第一次遇到，它会被记录在 single 中。
             * 如果 num 是第二次遇到（之前已经异或过一次），它会与之前的自己抵消变成 0。
             */
            single ^= num;
        }

        // 4. 遍历结束后，所有成对的数字都抵消成了 0，single 中只剩下那个唯一的数字。
        return single;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取控制台输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        singleNumberSolution sol = new singleNumberSolution();

        // 持续读取输入，直到没有更多整数
        while (sc.hasNextInt()) {
            // 第一步：读取数组的长度 n
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 第二步：填充数组元素
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 第三步：调用算法并打印结果
            System.out.println(sol.singleNumber(nums));
        }
        
        // 资源回收，关闭扫描器
        sc.close();
    }
}