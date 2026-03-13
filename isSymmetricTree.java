// 导入 Java 必备的工具包：用于输入、列表、队列等
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/*
101.对称二叉树
给你一个二叉树的根节点 root ， 检查它是否轴对称。
*/
public class isSymmetricTree {

    /**
     * 1. 定义二叉树的节点结构
     */
    private static class TreeNode {
        int val;                // 节点存储的数值
        TreeNode left;          // 左孩子指针
        TreeNode right;         // 右孩子指针

        TreeNode() {}           // 无参构造函数
        TreeNode(int val) {     // 带参数构造函数，方便赋值
            this.val = val;
        }
    }

    /**
     * 2. 核心算法：判断是否对称
     */
    public static boolean isSymmetric(TreeNode root) {
        // 如果根节点为空，它显然是对称的
        if (root == null) {
            return true;
        }
        // 调用辅助函数，比较根节点的左子树和右子树
        return check(root.left, root.right);
    }

    /**
     * 3. 辅助递归函数：比较两棵树是否互为镜像
     * p 是左边树的节点，q 是右边树的节点
     */
    public static boolean check(TreeNode p, TreeNode q) {
        // [情况1]：两个节点都为空，说明这一条路径对称，返回 true
        if (p == null && q == null) {
            return true;
        }
        // [情况2]：其中一个为空（说明结构不对称）或者两个都不为空但值不相等
        // 这里利用了短路特性，p == null || q == null 成立说明只有一个为空
        if (p == null || q == null) {
            return false;
        }
        
        // [情况3]：两个都不为空。必须满足三个条件：
        // 1. 当前两个节点的值相等
        // 2. p 的左孩子要等于 q 的右孩子（镜像位置比较）
        // 3. p 的右孩子要等于 q 的左孩子（镜像位置比较）
        return (p.val == q.val) && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 4. 主函数：处理输入和输出
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象，接收控制台输入
        Scanner sc = new Scanner(System.in);

        // 循环读取每一行层序遍历字符串（例如：1 2 2 3 4 4 3）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            // 如果输入为空行则跳过
            if (line.isEmpty()) continue;

            // 调用 buildTree 工具将字符串转为二叉树对象
            TreeNode root = buildTree(line);

            // 调用算法并输出结果（true 或 false）
            System.out.println(isSymmetric(root));
        }
        // 关闭扫描器
        sc.close();
    }

    /**
     * 5. 辅助函数：将层序遍历字符串转为二叉树
     */
    private static TreeNode buildTree(String data) {
        // 按空格拆分字符串为数组
        String[] nodes = data.split("\\s+");
        // 如果第一个元素是 null，返回空树
        if (nodes[0].equals("null")) return null;

        // 创建根节点并加入队列
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 使用指针 i 遍历数组
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            // 从队列取出当前的父亲节点
            TreeNode curr = queue.poll();

            // 构建左孩子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left); // 左孩子入队
            }
            i++; // 指针后移

            // 构建右孩子
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right); // 右孩子入队
            }
            i++; // 指针后移
        }
        return root;
    }
}