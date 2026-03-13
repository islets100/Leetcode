import java.util.Scanner;
import java.util.Arrays;

/**75.颜色分类
 * 定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
 * 原地 对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class sortColorsSolution {

    /**
     * 核心算法：对颜色进行排序
     * 0 代表红色，1 代表白色，2 代表蓝色
     * @param nums 待排序的数组
     */
    public void sortColors(int[] nums) {
        // 获取数组的长度
        int n = nums.length;
        
        // 定义一个指针 ptr，它代表“下一个该存放特定颜色的位置”
        int ptr = 0;

        // 【第一轮】：处理所有的 0 (红色)
        for (int i = 0; i < n; i++) {
            // 如果发现当前元素是 0
            if (nums[i] == 0) {
                // 交换 nums[i] 和 nums[ptr] 的值
                // 这样就把 0 换到了数组的前面
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                
                // 成功安放了一个 0，ptr 指针向后移动一位
                ptr++;
            }
        }

        // 【第二轮】：处理所有的 1 (白色)
        // 注意：这里 i 从当前的 ptr 开始即可，因为之前的全是 0
        for (int i = ptr; i < n; i++) {
            // 如果发现当前元素是 1
            if (nums[i] == 1) {
                // 交换 nums[i] 和 nums[ptr] 的值
                // 把 1 换到所有 0 的后面
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                
                // 成功安放了一个 1，ptr 指针继续向后移动
                ptr++;
            }
        }
        // 处理完 0 和 1，剩下的 2 自然就在末尾了
    }

    /**
     * 主函数：处理 ACM 模式的输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        sortColorsSolution solution = new sortColorsSolution();

        // 循环读取每一组输入（例如：2 0 2 1 1 0）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 将输入的字符串分割并转为整数数组
            // 这里使用了 Java 8 的流处理，也可以用传统的 for 循环转换
            int[] nums = Arrays.stream(line.split("\\s+"))
                               .mapToInt(Integer::parseInt)
                               .toArray();

            // 执行核心排序算法
            solution.sortColors(nums);

            // 按照题目要求格式打印结果数组
            // Arrays.toString 会输出 [0, 0, 1, 1, 2, 2] 格式
            System.out.println(Arrays.toString(nums));
        }
        
        // 记得关闭扫描器
        sc.close();
    }
}