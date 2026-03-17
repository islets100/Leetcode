import java.util.Scanner;

/**
 * 题号：LeetCode 153
 * 题目：寻找旋转排序数组中的最小值 (Find Minimum in Rotated Sorted Array)
 * 题目内容：已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次旋转后，得到输入数组。
 * 例如，原数组 [0,1,2,4,5,6,7] 在变化后可能得到 [4,5,6,7,0,1,2]。
 * 假设数组中所有元素都是【互不相同】的。
 * 请你找出并返回数组中的【最小值】。
 * 时间复杂度必须为 O(log n)。
 */
public class findMinSolution {

    /**
     * 核心算法：二分查找旋转点
     * @param nums 旋转后的排序数组
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        // 1. 初始化左右指针
        int low = 0;
        int high = nums.length - 1;

        // 2. 【关键：循环条件】使用 low < high
        // 因为我们的目标不是找一个具体的 target，而是缩小区间直到只剩一个元素。
        // 当 low == high 时，区间缩为一个点，那个点就是我们要找的最小值。
        while (low < high) {
            
            // 3. 计算中点索引，防溢出写法
            int pivot = low + (high - low) / 2;

            // 4. 【核心判断】：拿中点值 nums[pivot] 和当前区间的右端点值 nums[high] 比较
            if (nums[pivot] < nums[high]) {
                // 5. 如果中点值比右端点小，说明从 pivot 到 high 是升序的。
                // 最小值一定不在 pivot 的右边，但 pivot 自己可能是最小值。
                // 因此，我们将右边界收缩到 pivot（不能是 pivot - 1）。
                high = pivot;
            } else {
                // 6. 如果中点值比右端点大 (nums[pivot] > nums[high])
                // 说明旋转点（最小值）一定在 pivot 的右侧。
                // 且此时 pivot 指向的是左侧较大的升序段，它绝对不可能是最小值。
                // 因此，我们可以放心地将左边界跨过 pivot，设为 pivot + 1。
                low = pivot + 1;
            }
        }

        // 7. 循环退出时，low == high，搜索空间只剩下一个元素。
        // 这个元素就是旋转点，即最小值。
        return nums[low];
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        findMinSolution sol = new findMinSolution();

        // 持续读取输入
        while (sc.hasNextInt()) {
            // 读取数组长度
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 填充数组
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            // 执行算法并打印结果
            System.out.println(sol.findMin(nums));
        }
        
        // 关闭资源
        sc.close();
    }
}