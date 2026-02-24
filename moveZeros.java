import java.util.Arrays;

/*
283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。
*/


class moveZeroesSolution {
    public void moveZeroes(int[] nums) {
        // 1. 初始化
        // n: 数组的总长度
        // left: 左指针，指向“下一个非零数字该放的位置”
        // right: 右指针，负责遍历整个数组，寻找非零数
        int n = nums.length, left = 0, right = 0;

        // 2. 开始遍历，直到右指针走完整个数组
        while (right < n) {
            
            // 3. 核心判断：如果右指针指向的数【不是 0】
            if (nums[right] != 0) {
                
                // 4. 交换左右指针指向的数字
                // 此时把非零的 nums[right] 换到左边的“坑位” nums[left]
                swap(nums, left, right);
                
                // 5. 交换完后，左指针往后挪一位
                // 因为刚才那个位置已经填上非零数字了，得准备下一个位置
                left++;
            }
            
            // 6. 不管有没有交换，右指针都要继续往后走，去探测下一个数
            right++;
        }
    }

    // 这是一个辅助方法：交换数组中两个位置的值
    public void swap(int[] nums, int left, int right) {
        // 经典的“三步走”交换法：借用一个临时变量 temp
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

public class moveZeros {
    public static void main(String[] args) {
        moveZeroesSolution solution = new moveZeroesSolution();
        int[] nums = {0,0,1,1,1,2,2,0,3,5,6,9,0,1};
        solution.moveZeroes(nums);
        // 正確輸出陣列內容，而不是陣列物件位址
        System.out.println(Arrays.toString(nums));
    }
}
