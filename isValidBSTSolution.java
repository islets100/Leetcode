// 导入处理输入输出、队列和链表的工具类
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/*
98.验证二叉搜索树
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 严格小于 当前节点的数。
节点的右子树只包含 严格大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树
*/

public class isValidBSTSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;                // 节点存储的整数值
        TreeNode left;          // 左子节点引用
        TreeNode right;         // 右子节点引用

        TreeNode() {}           // 无参构造
        TreeNode(int val) {     // 带值的构造
            this.val = val;
        }
    }

    /**
     * 2. 核心算法：判断是否为有效的二叉搜索树 (BST)
     */
    public boolean isValidBST(TreeNode root) {
        // 使用 long 类型边界，防止 int 溢出或临界值冲突
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 辅助递归函数：通过上下界约束来验证
     * @param node  当前检查的节点
     * @param lower 当前节点必须严格大于的下限
     * @param upper 当前节点必须严格小于的上限
     */
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        // 如果走到空节点，说明当前路径符合 BST 定义
        if (node == null) {
            return true;
        }

        // 检查值是否在 (lower, upper) 的开区间内
        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        // 递归检查左子树（上限变为当前值）和右子树（下限变为当前值）
        return isValidBST(node.left, lower, node.val) && 
               isValidBST(node.right, node.val, upper);
    }

    /**
     * 3. 主函数：处理 ACM 模式输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 创建对象以调用非静态方法
        isValidBSTSolution solution = new isValidBSTSolution();

        // 循环读取每一行层序序列
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 构建二叉树并验证
            TreeNode root = buildTree(line);
            System.out.println(solution.isValidBST(root));
        }
        sc.close();
    }

    /**
     * 4. 辅助工具：层序遍历构建二叉树
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

            // 处理左孩子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;

            // 处理右孩子
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}