import java.util.Scanner;

/**
 * 题号：LeetCode 35
 * 题目：搜索插入位置 (Search Insert Position)
 * 内容：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 要求：必须使用时间复杂度为 O(log n) 的算法。
 */
public class searchInsertSolution {

    /**
     * 核心算法：二分搜索插入位置
     * @param nums   已经排好序的整数数组
     * @param target 我们要找（或准备插入）的目标值
     * @return 目标值的索引或插入位置索引
     */
    public int searchInsert(int[] nums, int target) {
        // 1. 获取数组的长度
        int n = nums.length;
        
        // 2. 初始化左右边界。left 指向数组开头，right 指向数组末尾。
        int left = 0, right = n - 1;
        
        // 3. 结果变量 ans 初始设为 n。
        // 这是一个精妙的预设：如果 target 比数组里所有的数都大，它就应该插在最后（索引 n）。
        int ans = n;

        // 4. 进入二分循环，条件是左边界不超过右边界
        while (left <= right) {
            
            // 5. 【核心计算】：计算中点位置。
            // ((right - left) >> 1) 等同于 (right - left) / 2。
            // 这种写法能有效防止 (left + right) 产生的整数溢出，且位运算速度更快。
            int mid = ((right - left) >> 1) + left;

            // 6. 逻辑判断：如果目标值 小于或等于 当前中点的值
            if (target <= nums[mid]) {
                // 7. 说明当前 mid 这个位置，或者 mid 左边的位置，可能是最终答案。
                // 我们暂时记录下这个位置。
                ans = mid;
                
                // 8. 【排除右边】：因为我们要找的是“第一个大于等于 target 的位置”，
                // 既然当前 mid 已经满足或超过了 target，那 mid 右边的数肯定更满足，
                // 但它们肯定不是“第一个”，所以我们砍掉右半边，去左边继续看。
                right = mid - 1;
            } else {
                // 9. 如果目标值 大于 当前中点的值
                // 10. 说明 mid 以及 mid 左边的所有数都太小了，不可能是插入位置。
                // 我们直接跳到右半边去搜索。
                left = mid + 1;
            }
        }
        
        // 11. 当循环结束时，ans 记录的就是数组中第一个大于等于 target 的位置
        return ans;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类对象
        searchInsertSolution solution = new searchInsertSolution();

        // 持续读取输入，直到没有更多数字
        while (sc.hasNextInt()) {
            // 第一步：读取数组长度
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 第二步：循环读取 n 个数字填入数组
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            // 第三步：读取要搜索的目标值 target
            int target = sc.nextInt();

            // 调用算法并输出结果
            System.out.println(solution.searchInsert(nums, target));
        }
        
        // 养成好习惯，关闭扫描器
        sc.close();
    }
}