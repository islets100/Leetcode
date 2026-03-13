import java.util.*;

/*
230.二叉搜索树中第k小的元素
给定一个二叉搜索树的根节点 root ，和一个整数 k ，
请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
*/
public class kthSmallestSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;                // 节点值
        TreeNode left;          // 左孩子
        TreeNode right;         // 右孩子

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 2. 核心算法：寻找二叉搜索树中第 k 小的元素
     * 使用迭代法（非递归）中序遍历
     */
    public int kthSmallest(TreeNode root, int k) {
        // 创建一个双端队列 Deque 当作栈（Stack）使用
        // 相比于老旧的 Stack 类，ArrayDeque 性能更好
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();

        // 只要当前节点不为空，或者栈里还有节点，就继续遍历
        while (root != null || !stack.isEmpty()) {
            
            // 【步骤 A】：一路向左扎到底
            // BST 的最小值一定在最左边，所以先把左侧路径全部压入栈中
            while (root != null) {
                stack.push(root);     // 将当前节点压栈
                root = root.left;      // 移向左子节点
            }

            // 【步骤 B】：处理当前节点（根）
            // 当无法再向左走时，从栈中弹出的就是当前最小的节点
            root = stack.pop();
            
            // 计数：每弹出一个，说明我们找到了一个更小的数
            --k;
            
            // 如果 k 减到 0，说明当前弹出的就是我们要找的第 k 小的数
            if (k == 0) {
                break; // 跳出循环，此时 root 指向的就是目标节点
            }

            // 【步骤 C】：转向右子树
            // 按照 左 -> 根 -> 右 的顺序，现在该去右子树重复上述过程了
            root = root.right;
        }

        // 返回目标节点的值
        return root.val;
    }

    /**
     * 3. 主函数：处理 ACM 输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        kthSmallestSolution solution = new kthSmallestSolution();

        // 习惯性处理：第一行输入 k，第二行输入层序遍历字符串
        // （或者按具体题目要求调整读取顺序）
        while (sc.hasNext()) {
            // 读取第 k 小的 k
            int k = sc.nextInt();
            sc.nextLine(); // 消耗掉整数后的换行符

            // 读取二叉树的层序遍历字符串
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 构建树
            TreeNode root = buildTree(line);

            // 执行算法并输出结果
            System.out.println(solution.kthSmallest(root, k));
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