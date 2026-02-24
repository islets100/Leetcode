/*
167. 两数之和 II - 输入有序数组

给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列 
 ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
 则 1 <= index1 < index2 <= numbers.length 。

以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。

你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

你所设计的解决方案必须只使用常量级的额外空间。
*/
import java.util.Arrays;

class twoSum2Solution {
    public int[] twoSum(int[] numbers, int target) {
        // 1. 设置两个哨兵
        // left 指向数组的最左边（最小的数）
        int left = 0;
        // right 指向数组的最右边（最大的数）
        int right = numbers.length - 1;

        // 2. 开启一个死循环（因为题目保证一定能找到答案，所以可以用 true）
        while(true){
            // 3. 计算当前这两个哨兵指向的数字之和
            int s = numbers[left] + numbers[right];

            // 4. 情况一：正好等于目标值
            if(s == target){
                // 注意：题目要求返回的是“从 1 开始”的下标
                // 所以要把我们程序员习惯的 0 和 1 变成 1 和 2
                return new int[] {left + 1, right + 1};  
            }

            // 5. 情况二：和太大（s > target）
            if(s > target){
                // 既然和太大了，而左边已经是最小的了，只能把右边的大数调小一点
                // 所以右指针往左移 (right--)
                right --;
            }
            
            // 6. 情况三：和太小（s < target）
            else{
                // 既然和太小了，而右边已经是最大的了，只能把左边的小数调大一点
                // 所以左指针往右移 (left++)
                left ++;
            }
        }
    }
}
public class twoSum2 {
    public static void main(String[] args) {
        twoSum2Solution solution = new twoSum2Solution();
        int [] nums = {0,1,2,4,6,8};
        int ans = 8;
        int[] res = solution.twoSum(nums, ans);
        // 正確打印返回的陣列結果
        System.out.println(Arrays.toString(res));
    }
}
