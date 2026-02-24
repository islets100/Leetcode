import java.util.HashSet;
import java.util.Set;
/**
 * 128. 最长连续序列
 * 这道题是 “最长连续序列” (Longest Consecutive Sequence)。
 * 它的目标是在一堆乱七八糟的数字里，找到最长的一串能排成“1, 2, 3, 4...”
 * 这样连续的数字有多长。这段代码最精妙的地方在于：它只从每一段连续数字
 * 的“头儿”开始数，不浪费时间。
 */

class longestConsecutiveSolution {
    public int longestConsecutive(int[] nums) {
        // 1. 把所有数字丢进一个“去重盒子” (HashSet)
        // Set 的特点：1. 里面没有重复数字；2. 查某个数字在不在里面，速度极快（秒查）。
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums){
            num_set.add(num); // 把数组里的数一个一个存进去
        }

        int longestStreak = 0; // 用来记录“全球最高纪录”（最长连续长度）

        // 2. 遍历盒子里的每一个数字
        for(int num : num_set){
            
            // --- 核心逻辑：找“领头羊” ---
            // 如果盒子里面没有 (num - 1)，说明当前这个 num 就是一段连续数字的“起点”。
            // 比如有 1, 2, 3。看 2 的时候，发现 1 在里面，2 就不是起点，跳过。
            // 看到 1 的时候，发现 0 不在里面，OK，1 就是起点！
            if (!num_set.contains(num - 1)){
                
                int currentNum = num;      // 当前正在数的数
                int currentStreak = 1;     // 当前这一段的长度，刚开始肯定是 1

                // 3. 开始“接龙”
                // 问盒子：有没有比我大 1 的数？有的话，长度加 1，继续往后找。
                while (num_set.contains(currentNum + 1)){
                    currentNum += 1;       // 数字变大 1
                    currentStreak += 1;    // 长度增加 1
                }

                // 4. 更新最高纪录
                // Math.max 的意思是：取“原纪录”和“新纪录”里较大的那个。
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        // 5. 返回最终拿到的最高纪录
        return longestStreak;
    }
}
public class longestConsecutive{
    public static void main(String[] args) {
        longestConsecutiveSolution solution = new longestConsecutiveSolution();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("測試1：{100, 4, 200, 1, 3, 2} -> " + solution.longestConsecutive(nums1));

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("測試2：{0, 3, 7, 2, 5, 8, 4, 6, 0, 1} -> " + solution.longestConsecutive(nums2));

        int[] nums3 = {};
        System.out.println("測試3：{} -> " + solution.longestConsecutive(nums3));

        int[] nums4 = {1, 2, 0, 1};
        System.out.println("測試4：{1, 2, 0, 1} -> " + solution.longestConsecutive(nums4));
    }
}