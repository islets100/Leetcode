/*
713. 乘积小于 K 的子数组
给定一个正整数数组 nums 和整数 k ，
请找出该数组内乘积小于 k 的连续的子数组的个数。
*/

class numSubarrayProductLessThanKSolution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1){
            return 0;
        }
        int ans = 0;
        int prod = 1;
        int left = 0;
        for(int right = 0;right< nums.length;right++){
            prod *= nums[right];
            while (prod >= k){
                prod /= nums[left];
                left++;
            }
            ans += right -left +1;
        }
        return ans;
    }
}


public class numSubarrayProductLessThanK {
    public static void main(String[] args) {
        numSubarrayProductLessThanKSolution solution = new numSubarrayProductLessThanKSolution();
        int[] nums1 = {10,5,2,6};
        int k1 = 100;
        int ans1 = solution.numSubarrayProductLessThanK(nums1, k1);
        System.out.println(ans1);
    }
}
