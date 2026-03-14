// 导入必要的工具类
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/**124.二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，
 * 序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class maxPathSumSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;                // 节点存储的数值
        TreeNode left;          // 左子节点
        TreeNode right;         // 右子节点

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 设置全局变量记录最大路径和，初始值为 int 的最小值
    // 这样即便全树都是负数，也能捕获到最大的那个负值
    int maxSum = Integer.MIN_VALUE;

    /**
     * 2. 核心算法入口
     */
    public int maxPathSum(TreeNode root) {
        // 调用递归函数开始计算贡献值
        maxGain(root);
        // 返回全局累积的最大值
        return maxSum;
    }

    /**
     * 递归函数：计算节点向父节点提供的“最大贡献值”
     */
    public int maxGain(TreeNode node) {
        // [出口条件]：如果是空节点，贡献值为 0
        if (node == null) {
            return 0;
        }

        // 递归计算左子树的最大贡献值
        // 使用 Math.max(..., 0) 是为了“及时止损”：如果子树收益为负，则视为不选，贡献为 0
        int leftGain = Math.max(maxGain(node.left), 0);
        
        // 递归计算右子树的最大贡献值
        int rightGain = Math.max(maxGain(node.right), 0);

        // 【更新全局最优解】：
        // 假设当前节点是路径的最高点（拐点），那么当前的完整路径和 = 当前值 + 左收益 + 右收益
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新全局最大值记录
        maxSum = Math.max(maxSum, priceNewpath);

        // 【返回贡献值】：
        // 作为上一级节点的子树，当前节点只能在左路或右路中选一条最长的连上去
        // 返回：当前节点值 + 左右路中较大的贡献
        return node.val + Math.max(leftGain, rightGain);
    }

    /**
     * 3. 主函数：处理 ACM 模式输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);
        // 实例化当前解题类
        maxPathSumSolution solution = new maxPathSumSolution();

        // 循环读取每一行层序序列（例如：-10 9 20 null null 15 7）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 调用 buildTree 辅助工具构建二叉树
            TreeNode root = buildTree(line);

            // 【重点】：由于类中使用了全局变量 maxSum，
            // 每次处理新的一组测试数据前，必须手动重置它
            solution.maxSum = Integer.MIN_VALUE;

            // 执行算法并输出结果
            System.out.println(solution.maxPathSum(root));
        }
        // 关闭扫描器
        sc.close();
    }

    /**
     * 4. 辅助工具：根据层序遍历序列构建二叉树
     */
    private static TreeNode buildTree(String data) {
        String[] nodes = data.split("\\s+");
        if (nodes[0].equals("null")) return null;

        // 创建根节点并放入队列
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll();

            // 构建左孩子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;

            // 构建右孩子
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}