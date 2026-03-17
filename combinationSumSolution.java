import java.util.*;

/**
 * 题号：LeetCode 39
 * 题目：组合总和 (Combination Sum)
 * 内容：给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 。
 * 注意：candidates 中的 同一个 数字可以 无限制重复被选取 。
 */
public class combinationSumSolution {

    /**
     * 主方法：初始化结果集并开启回溯
     * @param candidates 候选数字数组
     * @param target     目标和
     * @return 所有符合条件的组合列表
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 1. 创建结果集 res，用于存放最终找到的所有满足条件的组合
        List<List<Integer>> res = new ArrayList<>();
        
        // 2. 这里的辅助优化：如果对数组进行排序，可以在循环中提前结束（剪枝更高效）
        // 虽然不排序也能跑通，但在处理大数据时排序后性能更好
        //Arrays.sort(candidates);

        // 3. 调用回溯函数
        // 参数依次为：原数组、搜索起始索引(0)、当前剩余目标值(target)、临时路径篮子(new ArrayList)、结果集
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        
        // 4. 返回最终结果
        return res;
    }

    /**
     * 回溯递归函数
     * @param candidates 候选数组
     * @param index      当前这一层搜索的“起点”下标。这是去重的关键！
     * @param target     当前还需要凑齐的数值（随着递归深入，它会不断减小）
     * @param list       当前的临时路径（也就是我们已经捡到篮子里的数字）
     * @param res        全局结果大仓库
     */
    void backtrack(int[] candidates, int index, int target, List<Integer> list, List<List<Integer>> res) {
        // [出口条件]：如果 target 刚好减到了 0，说明我们现在的 list 里的数字加起来正好等于最初的 target
        if (target == 0) {
            // 将当前的临时路径 list 拷贝一份（拍照），存入结果仓库
            // 必须 new 一个新的，否则后续 list 的修改会破坏已经存入 res 的数据
            res.add(new ArrayList<>(list));
            return;
        }

        // [递归主体]：从 index 开始遍历数组，i 就是当前尝试选中的数字下标
        for (int i = index; i < candidates.length; i++) {
            
            // 【剪枝核心】：如果当前数字已经比目标 target 还要大了，那选它肯定会超标
            // 由于我们在主方法里做过 Arrays.sort，如果当前的 candidates[i] 太大，
            // 那么 i 之后的数字肯定也太大，所以直接跳过（continue）或停止（break）
            if (target < candidates[i]) {
                // 如果数组有序，这里直接 break 性能最高；如果是无序数组，则只能用 continue
                continue; 
            }

            // 1. 【做选择】：把当前的数字加入到篮子 list 中
            list.add(candidates[i]);

            // 2. 【递归】：深入下一层搜索。
            // 注意这里第二个参数传的是 i，而不是 i + 1。
            // 含义是：在下一层递归中，我们依然可以从“当前这个数字”开始选，从而实现“无限次重复使用”。
            // 同时，因为不从 0 开始，所以避免了产生 [2,3,3] 和 [3,2,3] 这种内容相同顺序不同的重复解。
            backtrack(candidates, i, target - candidates[i], list, res);

            // 3. 【撤销选择/回溯】：这是回溯算法的灵魂。
            // 当上面的递归函数执行完毕（无论找没找到解），我们需要把刚才加进去的数字从末尾删掉。
            // 这样循环到下一个 i 时，我们的 list 才是干净的，就像回到了刚才的岔路口。
            list.remove(list.size() - 1);
        }
    }

    /**
     * 主函数：处理 ACM 模式的输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        combinationSumSolution sol = new combinationSumSolution();

        // 循环读取输入（假设格式：第一行数字个数n，第二行数组内容，第三行target）
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] candidates = new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i] = sc.nextInt();
            }
            int target = sc.nextInt();

            // 获取并打印结果
            List<List<Integer>> result = sol.combinationSum(candidates, target);
            System.out.println(result);
        }
        sc.close();
    }
}