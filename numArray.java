/*
303. 区域和检索 - 数组不可变

给定一个整数数组  nums，处理以下类型的多个查询:

计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
实现 NumArray 类：

NumArray(int[] nums) 使用数组 nums 初始化对象
int sumRange(int left, int right) 返回数组 nums 中
索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点
（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
*/



class NumArraySolution {
    private int[] s;

    // 構造函數：初始化前綴和陣列
    public NumArraySolution(int[] nums) {
        s = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++){
            s[i + 1] = s[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return s[right + 1] - s[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

public class numArray {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArraySolution obj = new NumArraySolution(nums);

        System.out.println("nums = [-2, 0, 3, -5, 2, -1]");
        System.out.println("sumRange(0, 2) = " + obj.sumRange(0, 2)); // -2 + 0 + 3 = 1
        System.out.println("sumRange(2, 5) = " + obj.sumRange(2, 5)); // 3 - 5 + 2 - 1 = -1
        System.out.println("sumRange(0, 5) = " + obj.sumRange(0, 5)); // 整體和 = -3
    }
}