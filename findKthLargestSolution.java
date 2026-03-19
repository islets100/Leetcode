import java.util.Scanner;
import java.util.Random;

/**
 * 题号：LeetCode 215
 * 题目：数组中的第 K 个最大元素 (Kth Largest Element in an Array)
 * 题目内容：给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 注意：你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * * 解题思路：快速选择 (Quickselect)
 * 1. 目标转化：找“第 k 大”等价于找“升序排序后下标为 n - k”的元素。
 * 2. 分治思想：选取一个基准值 x，通过双指针将数组划分为两部分，左边 <= x，右边 >= x。
 * 3. 递归选择：判断目标下标在左区间还是右区间，只对目标所在的区间进行递归。
 */
public class findKthLargestSolution {

    /**
     * 快速选择核心函数
     * @param nums 数组
     * @param l 左边界
     * @param r 右边界
     * @param k 目标下标 (n - k)
     * @return 目标元素值
     */
    public int quickselect(int[] nums, int l, int r, int k) {
        // 1. 递归出口：如果区间只剩一个元素，那它必然就是目标
        if (l == r) return nums[k];

        // 2. 选取基准值 x：这里直接取左端点（在极端有序数据下可能退化，实际建议随机选取）
        int x = nums[l]; 
        
        // 3. 初始化双指针：i 从左往右扫，j 从右往左扫
        // 使用 l-1 和 r+1 是为了配合下面的 do-while 循环逻辑
        int i = l - 1;
        int j = r + 1;

        // 4. Hoare 划分过程：将数组分为两部分
        
        while (i < j) {
            // 从左向右找第一个不小于 x 的数
            do i++; while (nums[i] < x);
            // 从右向左找第一个不大于 x 的数
            do j--; while (nums[j] > x);

            // 5. 如果指针没相遇，说明找到了两个“站错队”的元素，交换它们
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        // 6. 递归决策：划分结束后，j 是分界线，区间被分为 [l, j] 和 [j+1, r]
        // 检查目标下标 k 落在哪个区间
        if (k <= j) {
            // 目标在左半部分，继续在左侧递归
            return quickselect(nums, l, j, k);
        } else {
            // 目标在右半部分，继续在右侧递归
            return quickselect(nums, j + 1, r, k);
        }
    }

    /**
     * 外部调用接口
     */
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        // 转化为求“升序排序后”的索引位置
        return quickselect(_nums, 0, n - 1, n - k);
    }

    /**
     * ACM 模式主函数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        findKthLargestSolution sol = new findKthLargestSolution();

        System.out.println("请输入数组长度 n 和第 k 大的 k 值：");
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 计算结果
            int result = sol.findKthLargest(nums, k);
            System.out.println("第 " + k + " 大的元素是：" + result);
        }
        sc.close();
    }
}