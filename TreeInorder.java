import java.util.*;


/*
94.二叉树的中序遍历
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
*/


public class TreeInorder {
    // 1. 定义二叉树节点类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入二叉树的层序遍历序列（null表示空节点，空格分隔）：");
        
        // ACM模式：循环处理每一行输入
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            
            // 将输入的字符串转为二叉树
            TreeNode root = buildTree(line);
            
            // 执行核心算法
            List<Integer> result = inorderTraversal(root);
            
            // 打印结果
            System.out.println("中序遍历结果: " + result);
        }
        sc.close();
    }

    /**
     * 核心算法：中序遍历（递归法）
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res); // 调用递归辅助函数
        return res;
    }

    /**
     * 递归辅助函数
     * 遵循：左 -> 根 -> 右 的顺序
     */
    public static void inorder(TreeNode root, List<Integer> res) {
        // 递归终止条件：如果节点为空，直接返回（不进行任何操作）
        if (root == null) {
            return;
        }
        
        // 1. 递归处理左子树
        inorder(root.left, res);
        
        // 2. 处理当前节点（根）：将当前节点的值存入结果集合
        res.add(root.val);
        
        // 3. 递归处理右子树
        inorder(root.right, res);
    }

    /**
     * 辅助工具：层序遍历构建二叉树
     * 例如输入: "1 null 2 3" 构建出以1为根，右子树为2，2的左子树为3的树
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
            
            // 处理左子节点
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;
            
            // 处理右子节点
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}