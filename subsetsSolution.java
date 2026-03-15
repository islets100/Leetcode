import java.util.*;

/**
 * 题号：LeetCode 78
 * 题目：子集 (Subsets)
 * 内容：给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 * 解法：位运算迭代法 (Bitmasking)
 * 说明：利用二进制的 0/1 特性来代表每个元素“不选/选”的状态。
 */
public class subsetsSolution {

    // 临时存储每一个生成的子集（相当于一个临时的篮子）
    List<Integer> t = new ArrayList<Integer>();
    
    // 存储最终所有的子集结果（相当于一个大仓库）
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 核心算法：生成所有子集
     * @param nums 输入的整数数组
     * @return 包含所有子集的列表
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 1. 获取数组长度
        int n = nums.length;

        // 2. 总共有 2^n 个子集。
        // (1 << n) 是位运算，意思是 1 左移 n 位，结果等于 2 的 n 次方。
        // 比如 n=3 时，1 << 3 等于 8。
        // 我们用变量 mask 从 0 循环到 2^n - 1，每一个数字代表一种组合方案。
        for (int mask = 0; mask < (1 << n); mask++) {
            
            // 3. 开启新的一轮方案前，先清空临时篮子 t
            t.clear();

            // 4. 检查当前这个方案 mask 的每一位二进制
            // 循环变量 i 代表数组 nums 的索引，同时也代表二进制的第 i 位
            for (int i = 0; i < n; i++) {
                
                // 5. 【核心位运算判断】
                // (1 << i) 会产生一个只有第 i 位是 1 的探测器（如 001, 010, 100）
                // (mask & (1 << i)) 会把 mask 和探测器做“按位与”运算。
                // 如果结果不为 0，说明 mask 的第 i 位确实是 1。
                if ((mask & (1 << i)) != 0) {
                    
                    // 6. 如果第 i 位是 1，说明在这种方案中，我们要选中 nums[i]
                    t.add(nums[i]);
                }
            }

            // 7. 【关键拷贝操作】
            // 这里使用了 ArrayList 的拷贝构造方法：new ArrayList<Integer>(t)。
            // 因为 Java 中 add(t) 只是把 t 的地址传了过去。
            // 为了防止下一轮 t.clear() 把存进去的结果也弄空，我们必须给当前状态“拍个照”（创建快照）。
            ans.add(new ArrayList<Integer>(t));
        }

        // 8. 返回装满快照的大仓库
        return ans;
    }

    /**
     * 主函数：负责处理标准输入输出 (ACM 模式)
     */
    public static void main(String[] args) {
        // 创建扫描器读取控制台输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类对象
        subsetsSolution solution = new subsetsSolution();

        // 持续读取输入
        while (sc.hasNextLine()) {
            // 读取一行输入，例如 "1 2 3"
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 将字符串切分并转为整数数组
            String[] parts = line.split("\\s+");
            int[] nums = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }

            // 调用核心方法生成子集
            List<List<Integer>> result = solution.subsets(nums);

            // 打印结果，例如 [[], [1], [2], [1, 2] ...]
            System.out.println(result);

            // 【重要】ACM 模式复用对象时，处理完一组数据要清空全局变量，防止数据重叠
            solution.ans.clear();
        }
        
        // 关闭扫描器
        sc.close();
    }
}