// 导入必备的工具包
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/*
543.二叉树的直径
给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的长度 。
这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。
*/


public class diameterOfBinaryTreeSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;             // 节点的值
        TreeNode left;       // 左孩子
        TreeNode right;      // 右孩子

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 定义全局变量 ans，用来记录遍历过程中发现的最大“节点数”
    // 注意：直径是“边”的数量，节点数 - 1 = 边的数量
    static int ans = 1;

    /**
     * 2. 核心算法：计算直径
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        // 重置全局变量，防止多组数据测试时干扰
        ans = 1;
        // 调用深度计算函数
        depth(root);
        // 因为 ans 存的是节点数，直径（边的数）等于节点数减一
        return ans - 1;
    }

    /**
     * 3. 递归辅助函数：计算深度
     * 在算深度的过程中，偷偷更新全局最大直径
     */
    public static int depth(TreeNode node) {
        // [出口] 如果是空节点，深度就是 0
        if (node == null) {
            return 0;
        }

        // 递归计算左子树的深度
        int L = depth(node.left);
        // 递归计算右子树的深度
        int R = depth(node.right);

        // 【关键点】更新最大路径
        // 经过当前节点的路径长度（节点数） = 左深度 + 右深度 + 1（自己）
        ans = Math.max(ans, L + R + 1);

        // 向父节点返回自己的最大深度
        // 自己的高度 = 左右子树中较高的那个 + 1（自己）
        return Math.max(L, R) + 1;
    }

    /**
     * 4. 主函数：处理 ACM 模式的输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘输入
        Scanner sc = new Scanner(System.in);

        // 循环读取每一行层序遍历字符串
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 调用工具函数：将字符串转为二叉树
            TreeNode root = buildTree(line);

            // 计算直径并打印
            System.out.println(diameterOfBinaryTree(root));
        }
        // 关闭扫描器
        sc.close();
    }

    /**
     * 5. 辅助工具：层序遍历构建二叉树
     */
    private static TreeNode buildTree(String data) {
        // 按空格拆分
        String[] nodes = data.split("\\s+");
        // 空树处理
        if (nodes[0].equals("null")) return null;

        // 创建根节点并入队
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        // 经典的队列填坑法构建树
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll();

            // 构建左子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;

            // 构建右子
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}