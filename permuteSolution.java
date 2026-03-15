import java.util.*;

/**
 * 题号：LeetCode 46
 * 题目：全排列 (Permutations)
 * 内容：给定一个不含重复数字的数组 nums ，返回其所有可能的全排列。
 * 你可以按任意顺序返回答案。
 * 例如：输入 [1,2,3] -> 输出 [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class permuteSolution {

    /**
     * 主逻辑方法：生成全排列
     * @param nums 输入的整数数组
     * @return 包含所有排列方案的列表
     */
    public List<List<Integer>> permute(int[] nums) {
        // 获取数组的长度，即我们需要填多少个位置
        int n = nums.length;
        
        // 用于存储所有最终生成的排列结果（大容器）
        List<List<Integer>> ans = new ArrayList<>();
        
        // 创建一个长度为 n 的 List，并用 null 预先占满。
        // Arrays.asList(new Integer[n]) 会创建一个固定长度的列表，
        // 这样我们后面可以使用 path.set(i, value) 直接修改指定位置的值，而不用频繁 add/remove。
        List<Integer> path = Arrays.asList(new Integer[n]);
        
        // 布尔数组，用于登记：第 j 个位置的数字是否已经被当前的排列使用了。
        // 这是回溯算法中经典的“标记位”思想。
        boolean[] onPath = new boolean[n];

        // 开启深度优先搜索 (DFS)，从第 0 个格子开始尝试填数
        dfs(0, nums, ans, path, onPath);
        
        // 返回最终结果集
        return ans;
    }

    /**
     * 递归函数（深度优先搜索）
     * @param i      当前正在填写的格子索引（即我们要填第几个坑）
     * @param nums   给定的备选数字数组
     * @param ans    最终结果的大容器
     * @param path   当前正在构建的排列（临时结果）
     * @param onPath 登记本，记录哪些数字被占用了
     */
    private void dfs(int i, int[] nums, List<List<Integer>> ans, List<Integer> path, boolean[] onPath) {
        // [递归边界/出口]：如果填写的索引 i 等于数组长度，说明坑位已经全部填满
        if (i == nums.length) {
            // 注意：path 是一个引用，它在递归过程中会不断被修改。
            // 必须使用 new ArrayList<>(path) 拷贝一份当前 path 的“快照”存入结果。
            // 否则，ans 里面最后存的都会是同一组数据。
            ans.add(new ArrayList<>(path));
            // 填满了，返回上一层递归
            return;
        }

        // [递归主体]：遍历备选数组中的每一个数字，尝试把它放到当前的第 i 个坑里
        for (int j = 0; j < nums.length; j++) {
            // 检查：如果 nums[j] 这个数字还没被之前的格子占用过
            if (!onPath[j]) {
                
                // 1. 【做选择】：把数字填入当前第 i 个坑
                // path.set(i, ...) 比 path.add(...) 在全排列中更直观
                path.set(i, nums[j]);
                
                // 2. 【登记】：在登记本上标记，表示下标为 j 的数字被用了
                onPath[j] = true;
                
                // 3. 【递归】：当前位置填好了，让程序去填下一个位置 (i + 1)
                dfs(i + 1, nums, ans, path, onPath);
                
                // 4. 【回溯/撤销】：这是最关键的一步！
                // 当上面的递归返回后，我们需要把刚才占用的数字“释放”掉，
                // 标记回 false。这样在 for 循环的下一次迭代中，
                // 后面的分支才能重新使用这个数字。
                onPath[j] = false;
            }
        }
    }

    /**
     * 5. ACM 模式主函数：负责读取输入并输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象读取标准输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        permuteSolution solution = new permuteSolution();

        // 持续读取输入直到结束
        while (sc.hasNextLine()) {
            // 读取一行输入，例如 "1 2 3"
            String line = sc.nextLine().trim();
            // 如果是空行则跳过
            if (line.isEmpty()) continue;

            // 将字符串按空格切分，并转换为 int 数组
            String[] parts = line.split("\\s+");
            int[] nums = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }

            // 调用核心方法获得全排列结果
            List<List<Integer>> result = solution.permute(nums);

            // 打印结果：例如 [[1, 2, 3], [1, 3, 2], ...]
            System.out.println(result);
        }
        
        // 关闭扫描器
        sc.close();
    }
}