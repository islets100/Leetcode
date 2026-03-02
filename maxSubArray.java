/* 动态规划
53. 最大子数组和
给你一个整数数组 nums ，
请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），
返回其最大和。
子数组是数组中的一个连续部分。
*/

class maxSubArraySolution {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for(int x : nums){
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}

public class maxSubArray {
    public static void main(String[] args) {
        maxSubArraySolution solution = new maxSubArraySolution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int ans = solution.maxSubArray(nums);
        System.out.println(ans);
    }
}
