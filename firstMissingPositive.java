import java.util.*;

/*
41. 缺失的第一个正数
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
*/
class firstMissingPositiveSolution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length; // 获取数组的长度

        // --- 第一阶段：清理现场 ---
        // 我们只关心正整数 [1, n]，所以负数和 0 都是干扰项。
        for(int i = 0; i < n; i++) {
            if(nums[i] <= 0) {
                // 将所有小于等于 0 的数改成一个不可能的“大正数” n + 1
                // 这样做是为了保证稍后我们看到的负号一定是我们自己打上去的“标记”
                nums[i] = n + 1;
            }
        }

        // --- 第二阶段：打标记（核心逻辑） ---
        // 遍历数组，如果发现数字 x 出现了，就去下标 x-1 的位置把数变负
        for (int i = 0; i < n; i++) {
            // 取绝对值是因为当前的 nums[i] 可能已经被之前的操作变成负数了
            int num = Math.abs(nums[i]); 
            
            // 我们只标记落在 [1, n] 范围内的数字
            if (num <= n) {
                // 找到该数字对应的“坑位”下标：num - 1
                // Math.abs(nums[num - 1]) 确保取到该坑位原本的正数值
                // 在前面加负号，表示“这个坑位对应的数字已经出现过了”
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // --- 第三阶段：寻找第一个正数 ---
        // 经过上面的标记，如果某个坑位 nums[i] 还是正数，说明数字 i+1 从没出现过
        for (int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                // 找到第一个没被“打负号”的位置，返回它代表的数字
                return i + 1;
            }
        }

        // --- 第四阶段：兜底情况 ---
        // 如果 1 到 n 所有的坑位都被打上了负号，说明 1~n 都在数组里
        // 那么缺失的第一个正整数就是 n + 1
        return n + 1;
    }
}
public class firstMissingPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入數組元素，以空格分隔，例如：1 2 0");

        // 讀取一整行，再按空格切分
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("輸入為空，無法計算。");
            return;
        }

        String[] parts = line.split("\\s+");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        firstMissingPositiveSolution solution = new firstMissingPositiveSolution();
        int ans = solution.firstMissingPositive(nums);
        System.out.println("第一個缺失的正整數為：" + ans);
    }
}