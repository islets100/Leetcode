import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class threeSumSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 1. 排序是基石：排序后才能用双指针，且相同的数会挨在一起方便去重
        Arrays.sort(nums);
        
        // 2. 结果盒子：装符合条件的 [a, b, c]
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // 3. 固定第一个数 x = nums[i]
        // 注意 i < n - 2 是因为后面还要留两个位置给另外两个数
        for(int i = 0; i < n - 2; i++) {
            int x = nums[i];

            // --- 优化与去重逻辑 ---

            // 去重：如果当前的数和刚才处理过的数一样，跳过（避免结果集重复）
            if(i > 0 && x == nums[i - 1]) continue;

            // 剪枝 1：如果当前最小的三个数相加都 > 0，后面只会更大，直接结束循环
            if(x + nums[i + 1] + nums[i + 2] > 0) break;

            // 剪枝 2：如果当前数 x 加上数组最大的两个数都 < 0，说明 x 太小，换下一个更大的 x
            if(x + nums[n - 2] + nums[n - 1] < 0) continue;

            // --- 双指针寻找另外两个数 ---
            
            int j = i + 1; // 左指针（从 x 的右边开始）
            int k = n - 1; // 右指针（从数组最末尾开始）

            while(j < k) {
                int s = x + nums[j] + nums[k]; // 三数之和

                if (s > 0) {
                    // 和太大，右指针左移（数字变小）
                    k--;
                } else if (s < 0) {
                    // 和太小，左指针右移（数字变大）
                    j++;
                } else {
                    // 找到了！s == 0
                    // 把三个数打包进 List 并添加到结果大盒子中
                    ans.add(List.of(x, nums[j], nums[k]));

                    // --- 找到答案后的去重 (重点) ---
                    
                    // j 往右挪，直到指向一个不一样的数（跳过所有重复的 nums[j]）
                    for(j++; j < k && nums[j] == nums[j - 1]; j++); 
                    
                    // k 往左挪，直到指向一个不一样的数（跳过所有重复的 nums[k]）
                    for(k--; j < k && nums[k] == nums[k + 1]; k--);
                }
            } 
        }

        return ans;
    }
}
public class threeSum {
    public static void main(String[] args) {
        threeSumSolution solution = new threeSumSolution();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("測試1：[-1, 0, 1, 2, -1, -4]");
        System.out.println("結果：" + solution.threeSum(nums1));

        int[] nums2 = {0, 0, 0, 0};
        System.out.println("測試2：[0, 0, 0, 0]");
        System.out.println("結果：" + solution.threeSum(nums2));

        int[] nums3 = {-2, 0, 1, 1, 2};
        System.out.println("測試3：[-2, 0, 1, 1, 2]");
        System.out.println("結果：" + solution.threeSum(nums3));
    }
}
