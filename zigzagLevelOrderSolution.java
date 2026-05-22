import java.util.*;

/**
 * 题号：LeetCode 103
 * 题目：二叉树的锯齿形层序遍历 (Binary Tree Zigzag Level Order Traversal)
 * 题目内容：给你二叉树的根节点 root，返回其节点值的锯齿形层序遍历。
 * 即：奇数层（从下标 0 起）从左到右，偶数层从右到左。
 *
 * 解题思路：BFS + 按层翻转入队顺序
 * 1. 用队列做层序遍历，每次处理完一整层再翻转 direction。
 * 2. direction = false 时先压右子再左子（本层从左到右读出）；
 *    direction = true 时先压左子再右子（配合层内读取顺序实现锯齿效果）。
 * 3. 也可在每层结束后对 List 做 Collections.reverse，本题采用 interview 的入队顺序法。
 */
public class zigzagLevelOrderSolution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 核心算法：锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 1. false 表示当前层按“左→右”顺序收集；每处理完一层再取反
        boolean direction = false;
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                // 2. 根据当前层方向，决定子节点入队顺序
                if (direction) {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                } else {
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                }
            }

            // 3. 一层处理完毕后再翻转方向（修正 interview 原稿在节点循环内翻转的 bug）
            direction = !direction;
            ans.add(level);
        }

        return ans;
    }

    private static TreeNode buildTree(String line) {
        String[] nodes = line.split("\\s+");
        if (nodes[0].equals("null")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode node = queue.poll();
            if (!nodes[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.left);
            }
            i++;

            if (i < nodes.length && !nodes[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        zigzagLevelOrderSolution sol = new zigzagLevelOrderSolution();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            TreeNode root = buildTree(line);
            List<List<Integer>> res = sol.zigzagLevelOrder(root);
            System.out.println(res);
        }
        sc.close();
    }
}
