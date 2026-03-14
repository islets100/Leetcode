import java.util.*;

/**403.路径总和|||  前缀和&dfs
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，
 * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class pathSumSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;                // 节点值
        TreeNode left;          // 左孩子指针
        TreeNode right;         // 右孩子指针

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 2. 核心算法：路径总和 III
     * 利用前缀和思想，在 O(n) 时间内解决
     */
    public int pathSum(TreeNode root, int targetSum) {
        // 创建哈希表记录前缀和出现的次数
        // Key: 前缀和的值 (使用 Long 防止溢出)
        // Value: 该前缀和出现的次数
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        
        // 【核心初始化】：代表路径和刚好为 0 的情况出现过 1 次
        // 如果当前路径和 curr 恰好等于 targetSum，那么 curr - targetSum = 0，
        // 我们需要能从 map 中找到这个 0，从而计数。
        prefix.put(0L, 1);
        
        // 开始深度优先搜索遍历整棵树
        return dfs(root, prefix, 0, targetSum);
    }

    /**
     * 辅助递归函数 (DFS)
     * @param root      当前遍历到的节点
     * @param prefix    存储当前路径上所有前缀和及其次数的 Map
     * @param curr      从根节点到当前节点路径上的累加和
     * @param targetSum 目标路径和
     * @return 返回经过当前节点及其子树中符合条件的路径总数
     */
    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        // [出口条件]：如果节点为空，返回 0
        if (root == null) {
            return 0;
        }

        int ret = 0; // 用于累计当前节点及其后代中发现的合法路径数
        
        // 更新当前路径的累加和
        curr += root.val;

        // 【关键逻辑】：检查之前是否存在一个前缀和，使得 (当前累加和 - 那个前缀和) == targetSum
        // 即：判断 curr - targetSum 是否在 Map 中
        ret = prefix.getOrDefault(curr - targetSum, 0);

        // 将当前的累加和 curr 存入 Map，或者次数加 1
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        // 递归计算左子树中符合条件的路径
        ret += dfs(root.left, prefix, curr, targetSum);
        
        // 递归计算右子树中符合条件的路径
        ret += dfs(root.right, prefix, curr, targetSum);

        // 【回溯关键点】：当我们要离开当前节点回到父节点时，
        // 必须把当前节点的累加和从 Map 中减掉一次！
        // 原因是：Map 只能记录“当前路径”（从根到当前节点）的前缀和。
        // 如果不删除，当递归去到另一条支路时，会错误地用到这条已经走完的路径上的前缀和。
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    /**
     * 3. 主函数：处理 ACM 输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pathSumSolution solution = new pathSumSolution();

        while (sc.hasNext()) {
            // 输入格式示例:
            // 8 (目标值)
            // 10 5 -3 3 2 null 11 3 -2 null 1 (层序序列)
            int target = sc.nextInt();
            sc.nextLine(); // 消耗换行符

            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            TreeNode root = buildTree(line);
            System.out.println(solution.pathSum(root, target));
        }
        sc.close();
    }

    /**
     * 4. 辅助工具：构建二叉树
     */
    private static TreeNode buildTree(String data) {
        String[] nodes = data.split("\\s+");
        if (nodes[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll();
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}