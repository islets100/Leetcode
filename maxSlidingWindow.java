import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/* 
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从
数组的最左侧移动到数组的最右侧。
你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
*/
class maxSlidingWindowSolution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 结果数组的长度：n个数字，每k个一组，总共有 n - k + 1 个窗口
        int[] ans = new int[n - k + 1]; 
        
        // Deque 是双端队列，支持从队头和队尾两端进行插入和删除
        // 这里队列存储的是数组的【下标】而不是数值，方便后面判断元素是否过期
        Deque<Integer> q = new ArrayDeque<>(); 

        for (int i = 0; i < n; i++) {
            // --- 1. 右边入（保持单调性） ---
            // 如果新进来的数 nums[i] 大于或等于队尾的数
            // 那么队尾的数永远不可能成为最大值了（因为它既比新数小，又比新数早过期）
            // 所以直接把队尾弹出，直到队列为空或者队尾比新数大
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
                q.removeLast(); 
            }
            // 将当前元素的下标加入队尾
            q.addLast(i); 

            // --- 2. 左边出（判断是否滑出窗口） ---
            // 计算当前窗口的左边界
            int left = i - k + 1; 
            // 如果队首记录的下标小于左边界，说明这个“最大值”已经不在当前窗口内了
            if (q.getFirst() < left) { 
                q.removeFirst(); // 弹出过期的最大值下标
            }

            // --- 3. 记录答案 ---
            // 只有当窗口形成（i 至少达到 k-1）时，才开始记录最大值
            if (left >= 0) {
                // 因为队列是单调递减的，队首永远是当前窗口内最大的那个数的下标
                ans[left] = nums[q.getFirst()];
            }
        }

        return ans;
    }
}

public class maxSlidingWindow {
    public static void main(String[] args) {
        maxSlidingWindowSolution solution = new maxSlidingWindowSolution();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] ans = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ans));
    }
}