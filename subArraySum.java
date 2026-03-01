import java.util.Map;
import java.util.HashMap;

/*
560. 和为 K 的子数组

给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
*/

class subArraySumSolution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        
        // 1. 创建前缀和数组 s
        // 长度为 n+1 是为了存那个初始的 0（代表还没开始加数时的和）
        int[] s = new int[n + 1];
        for(int i = 0; i < n; i++){
            // 当前的总和 = 上一次的总和 + 当前数组里的数
            s[i+1] = s[i] + nums[i];
        }

        // 2. 创建哈希表（账本）
        // Key: 某个前缀和的值
        // Value: 这个值出现了几次
        Map<Integer, Integer> cnt = new HashMap<>(n+1, 1);
        
        int ans = 0; // 记录最终找到了多少个符合条件的子数组

        // 3. 遍历前缀和数组
        for(int sj : s){
            // 核心逻辑：
            // 我们在找有没有一个以前的面积 si，使得 sj - si = k
            // 变形一下就是：sj - k = si
            
            // 查账：看看账本里有没有出现过 (sj - k) 这个值？
            // 如果有，就说明从那个位置到当前位置的子数组和刚好是 k
            ans += cnt.getOrDefault(sj - k, 0);

            // 记账：把当前这个总和 sj 存进去，或者把它出现的次数 +1
            // 这样以后后面的数如果要找 sj，就能找到了
            cnt.put(sj, cnt.getOrDefault(sj, 0) + 1);
        }

        return ans;
    }
}

public class subArraySum {
    public static void main(String[] args) {
        subArraySumSolution solution = new subArraySumSolution();
        
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("test1: [1,1,1], k=2 -> " + solution.subarraySum(nums1, k1)); // 2

        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("test2: [1,2,3], k=3 -> " + solution.subarraySum(nums2, k2)); // 2

        int[] nums3 = {1, -1, 0};
        int k3 = 0;
        System.out.println("test3: [1,-1,0], k=0 -> " + solution.subarraySum(nums3, k3)); // 3

        int[] nums4 = {3,4,7,2,-3,1,4,2};
        int k4 = 7;
        System.out.println("test4: [3,4,7,2,-3,1,4,2], k=7 -> " + solution.subarraySum(nums4, k4)); // 4
    }
}
