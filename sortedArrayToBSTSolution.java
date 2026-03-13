// 导入处理输入输出和队列的工具包
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/*
108.将有序数组转化为二叉平衡树
给你一个整数数组 nums ，其中元素已经按升序排列，
请你将其转换为一棵 平衡 二叉搜索树。
*/

public class sortedArrayToBSTSolution{

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
     * 2. 核心算法：将有序数组转为平衡 BST
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        // 调用递归辅助函数，初始范围是从 0 到数组最后一个索引
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 递归辅助函数
     * @param nums 原数组
     * @param left 当前处理区间的左边界
     * @param right 当前处理区间的右边界
     */
    public static TreeNode helper(int[] nums, int left, int right) {
        // [出口条件] 如果左边界大于右边界，说明区间已经空了，返回 null
        if (left > right) {
            return null;
        }

        // [找根节点] 选取区间的中间位置作为根节点，这样左右分布最均匀
        // 这里 (left + right) / 2 等同于选择中间偏左的元素
        int mid = (left + right) / 2;

        // [创建节点] 用中间的数字创建一个新的树节点
        TreeNode root = new TreeNode(nums[mid]);

        // [递归构造左子树] 区间变为：从当前的 left 到 mid - 1
        root.left = helper(nums, left, mid - 1);

        // [递归构造右子树] 区间变为：从 mid + 1 到当前的 right
        root.right = helper(nums, mid + 1, right);

        // 返回当前构造好的子树根节点
        return root;
    }

    /**
     * 3. 主函数：处理 ACM 输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 循环读取每一行输入（假设输入是一行由空格分隔的有序数字）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 将字符串切分并转为整数数组
            String[] parts = line.split("\\s+");
            int[] nums = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }

            // 执行算法
            TreeNode root = sortedArrayToBST(nums);

            // 打印结果（为了验证，我们用层序遍历输出这棵树）
            printLevelOrder(root);
        }
        sc.close();
    }

    /**
     * 辅助工具：层序遍历打印树（用于验证结果）
     */
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        // 打印出层序遍历结果
        System.out.println("BST 层序结果: " + String.join(" ", result));
    }
}