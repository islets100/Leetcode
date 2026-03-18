import java.util.Arrays;
import java.util.Scanner;

/**
 * 题号：LeetCode 31
 * 题目：下一个排列 (Next Permutation)
 * 题目内容：实现“下一个排列”函数，将数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * * 核心逻辑（四步走）：
 * 1. [找降序起点] 从后向前找第一个相邻升序对 (i, j)，使得 nums[i] < nums[j]。
 * 2. [找替代者] 若找到 i，从后向前找第一个大于 nums[i] 的数 nums[k]。
 * 3. [交换] 交换 nums[i] 和 nums[k]。
 * 4. [反转] 将 j 之后的部分（原本是降序）反转为升序，使增量最小。
 */
public class NextPermutationSolution {

    /**
     * 算法实现：扫描交换法
     * @param nums 待排列的整数数组
     */
    public void nextPermutation(int[] nums) {
        // 1. 边界检查：若数组为空或只有一个元素，直接返回
        if (nums == null || nums.length <= 1) {
            return;
        }

        // 2. 初始化指针：i 指向倒数第二个，j 指向倒数第一个，k 同样初始化在末尾
        int i = nums.length - 2;
        int j = nums.length - 1;
        int k = nums.length - 1;

        // 3. 【第一步】：从后往前扫描，寻找第一个“左边比右边小”的位置
        // 如果 nums[i] >= nums[j]，说明这一段是降序的，无法通过重排变大，继续往前找
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        // 4. 【第二步】：判断是否找到了这样一个 i
        // 如果 i < 0，说明整个数组是降序的（如 [3,2,1]），已经是最大排列，直接跳到反转步骤
        if (i >= 0) {
            // 此时 j 到末尾一定是降序的。我们在这一段中从后往前找
            // 寻找第一个比 nums[i] 大的数字 nums[k]
            while (nums[i] >= nums[k]) {
                k--;
            }
            // 5. 【第三步】：交换这两个数。
            // 把一个稍大的数换到前面，数字变大了，但我们要保证它变大的幅度最小。
            swap(nums, i, k);
        }

        // 6. 【第四步】：反转 j 及其之后的所有元素
        // 既然 j 后面原本是降序的，反转后就变成了升序。
        // 在低位使用升序排列，可以保证这个排列是当前头部锁定后的“最小值”。
        reverse(nums, j, nums.length - 1);
    }

    /**
     * 辅助函数：交换数组中两个下标对应的元素
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 辅助函数：反转数组中从 start 到 end 的部分
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NextPermutationSolution sol = new NextPermutationSolution();

        // 持续读取输入
        while (sc.hasNextInt()) {
            // 输入格式：n (数组长度)，然后是 n 个整数
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            
            // 执行算法
            sol.nextPermutation(nums);
            
            // 输出格式化后的结果
            System.out.println(Arrays.toString(nums));
        }
        sc.close();
    }
}