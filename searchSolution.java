import java.util.Scanner;

/**
 * 题号：LeetCode 33
 * 题目：搜索旋转排序数组 (Search in Rotated Sorted Array)
 * 题目内容：整数数组 nums 按升序排列，数组中的值互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k 处进行了旋转。
 * 例如，[0,1,2,4,5,6,7] 在下标 3 处旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你旋转后的数组 nums 和一个整数 target ，如果数组中存在这个目标值，则返回它的下标，否则返回 -1 。
 */
public class searchSolution {

    /**
     * 核心算法：二分查找旋转数组
     * @param nums 旋转后的排序数组
     * @param target 目标值
     * @return 目标值所在的索引，若不存在则返回 -1
     */
    public int search(int[] nums, int target) {
        // 1. 获取数组长度
        int n = nums.length;
        
        // 2. 边界处理：如果数组为空，直接返回 -1
        if (n == 0) return -1;
        
        // 3. 初始化左右指针
        int l = 0, r = n - 1;
        
        // 4. 进入二分查找循环
        while (l <= r) {
            // 5. 计算中点，采用防溢出且位运算更快的写法
            int mid = ((r - l) >> 1) + l;
            
            // 6. 如果中点值正好等于目标值，直接返回索引
            if (nums[mid] == target) return mid;
            
            // 7. 【关键判断】：判断左半部分 [l, mid] 是否是有序的
            // 如果左边界值 <= 中点值，说明旋转点不在左边，左边是升序的
            if (nums[l] <= nums[mid]) {
                
                // 8. 在左边有序的情况下，检查 target 是否在 [nums[l], nums[mid]) 范围内
                if (nums[l] <= target && target < nums[mid]) {
                    // 9. 如果在范围内，说明目标在左侧，收缩右边界
                    r = mid - 1;
                } else {
                    // 10. 如果不在范围内，说明目标肯定在右半部分（哪怕右边是乱的）
                    l = mid + 1;
                }
                
            } 
            // 11. 如果 nums[l] > nums[mid]，说明旋转点在左半部分，那么右半部分 [mid, r] 必然是有序的
            else {
                
                // 12. 在右边有序的情况下，检查 target 是否在 (nums[mid], nums[r]] 范围内
                if (nums[mid] < target && target <= nums[r]) {
                    // 13. 如果在范围内，说明目标在右侧，收缩左边界
                    l = mid + 1;
                } else {
                    // 14. 如果不在范围内，说明目标肯定在左半部分
                    r = mid - 1;
                }
            }
        }
        
        // 15. 循环结束仍未找到，返回 -1
        return -1;
    }

    /**
     * ACM 模式主函数：处理标准输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器
        Scanner sc = new Scanner(System.in);
        searchSolution sol = new searchSolution();

        // 循环读取数据
        while (sc.hasNextInt()) {
            // 读取数组长度
            int n = sc.nextInt();
            int[] nums = new int[n];
            // 填充数组内容
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            // 读取目标值
            int target = sc.nextInt();

            // 执行搜索并输出结果
            System.out.println(sol.search(nums, target));
        }
        
        // 关闭资源
        sc.close();
    }
}