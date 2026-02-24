import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/*
1. 两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  
的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。
*/


/**
 * 在找的过程中动态地建立登记表。这种写法的代码更简洁，也巧妙地避开了“自己匹配自己”的问题。
 */


class Solution { // 定义一个名为 Solution 的类，这是 Java 程序的通用结构。
    public int[] twoSum(int[] nums, int target) { 
        // 定义一个方法：输入是一个整数数组 nums 和一个目标值 target。
        // 输出是一个包含两个数字下标的数组（例如 [0, 1]）。

        // 1. 创建一个“登记表”（哈希表）
        // Key 存数字的值，Value 存这个数字在数组里的下标。
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();

        // 2. 开始一个一个看数组里的数字
        for(int i = 0; i < nums.length; ++i){

            // 3. 计算：如果我想凑成 target，我需要的另一个数字是多少？
            // 比如 target 是 9，现在的数字 nums[i] 是 2，那我需要找的就是 7。
            if (hashtable.containsKey(target - nums[i])){

                // 4. 如果登记表里已经有这个“需要的数字”了：
                // 那就直接返回两个人的下标：[那个数字的下标, 当前数字的下标 i]
                // 注意：你这里的代码 return 处有个小语法错误（下面会纠正）
                return new int[]{hashtable.get(target - nums[i]), i};
            }

            // 5. 如果登记表里还没出现过那个数字：
            // 就把自己存进去，告诉后面的人：“我叫 nums[i]，我的位置在 i，有人找我就联系我。”
            hashtable.put(nums[i], i);
        }

        // 6. 如果整个数组都看完了还没找到，就返回一个空数组。
        return new int[0];
    }
}
public class TwoSum {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = s.twoSum(nums, target);
        System.out.println(Arrays.toString(result)); // 預期輸出: [0, 1]
    }
}