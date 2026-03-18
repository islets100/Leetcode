import java.util.Scanner;

/**
 * 题号：LeetCode 287
 * 题目：寻找重复数 (Find the Duplicate Number)
 * 题目内容：给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）。
 * 可知至少存在一个重复的整数。假设 nums 只有一个重复的整数 ，返回这个重复的数 。
 * * 限制条件：
 * 1. 不能修改数组（不能排序）。
 * 2. 只能使用 O(1) 的额外空间。
 * 3. 时间复杂度需小于 O(n^2)。
 * * 核心思路：快慢指针（寻找链表环入口）
 */
public class findDuplicateSolution {

    /**
     * 算法实现：弗洛伊德判圈算法
     * @param nums 输入数组
     * @return 重复的那个数字
     */
    public int findDuplicate(int[] nums) {
        // 1. 初始化快慢指针。
        // 我们从起点开始（索引 0）。
        int slow = 0;
        int fast = 0;

        /**
         * 2. 第一阶段：寻找相遇点
         * 慢指针每次走一步：slow = nums[slow]
         * 快指针每次走两步：fast = nums[nums[fast]]
         * 只要有环，快指针最终一定会追上慢指针并相遇。
         */
        while (true) {
            slow = nums[slow];             // 走一步
            fast = nums[nums[fast]];       // 走两步
            
            // 当快慢指针重合时，说明在环内相遇了
            if (fast == slow) {
                break;
            }
        }

        /**
         * 3. 第二阶段：寻找环的入口（重复数）
         * 根据数学证明，此时设置一个新指针 head 从起点出发，
         * 慢指针 slow 继续从相遇点出发，两人都每次只走一步。
         * 它们最终相遇的地方，就是环的入口，即重复的数字。
         */
        int head = 0;
        while (slow != head) {
            slow = nums[slow]; // 慢指针继续走
            head = nums[head]; // 新指针从头开始走
        }

        // 4. 相遇时的值就是重复的数字
        return slow;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        findDuplicateSolution sol = new findDuplicateSolution();

        // 持续读取输入
        while (sc.hasNextInt()) {
            // 输入格式：n+1 (数组长度)，然后是数组元素
            int len = sc.nextInt();
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 调用算法并打印结果
            System.out.println(sol.findDuplicate(nums));
        }
        
        sc.close();
    }
}