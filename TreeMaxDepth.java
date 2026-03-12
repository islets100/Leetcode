import java.util.*;

/*后序
104.二叉树的最大深度
给定一个二叉树 root ，返回其最大深度。

二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 */

public class  TreeMaxDepth{

    // 1. 定义二叉树节点类
    public static class TreeNode {
        int val; // 节点存储的值
        TreeNode left; // 指向左子节点的指针
        TreeNode right; // 指向右子节点的指针
        
        TreeNode() {} // 无参构造函数
        TreeNode(int val) { this.val = val; } // 带值的构造函数
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 创建扫描器读取标准输入
        Scanner sc = new Scanner(System.in);
        
        // 循环读取每一行输入，适应多组测试数据
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue; // 过滤空行
            
            // 2. 将输入的层序遍历字符串构建成二叉树对象
            TreeNode root = buildTree(input);
            
            // 3. 调用核心算法计算最大深度
            int depth = maxDepth(root);
            
            // 4. 输出计算结果
            System.out.println(depth);
        }
        sc.close(); // 养成良好习惯，关闭扫描器
    }

    /**
     * 核心算法：计算二叉树最大深度
     * 思路：后序遍历（分治法）
     */
    public static int maxDepth(TreeNode root) {
        // 如果当前节点为空，说明下潜到了尽头，深度贡献为 0
        if (root == null) {
            return 0;
        }
        // 递归获取左子树的高度
        int leftHeight = maxDepth(root.left);
        // 递归获取右子树的高度
        int rightHeight = maxDepth(root.right);
        
        // 返回左右子树中较高的那个，加上当前这一层的 1
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 辅助工具：层序遍历构建二叉树
     * 使用队列（Queue）按层级顺序填充节点
     */
    private static TreeNode buildTree(String data) {
        // 将输入字符串按空格切分为数组
        String[] nodes = data.split("\\s+");
        // 如果第一个元素是 null，说明树本身为空
        if (nodes[0].equals("null")) return null;
        
        // 创建根节点并将其放入队列
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int i = 1; // 数组索引指针，指向当前要处理的子节点值
        while (!queue.isEmpty() && i < nodes.length) {
            // 从队列中弹出一个父亲节点，为其安排左右儿子
            TreeNode curr = queue.poll();
            
            // 尝试构建左子节点
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left); // 左儿子入队，未来它也会成为别人的父亲
            }
            i++; // 索引向后移动一格
            
            // 尝试构建右子节点（注意检查数组是否越界）
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right); // 右儿子入队
            }
            i++; // 索引再次移动
        }
        // 返回构建完成的树根
        return root;
    }
}