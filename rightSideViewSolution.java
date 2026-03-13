import java.util.*;

/**
 * 199.返回二叉树的右视图
 * 给定一个二叉树的 根节点 root，
 * 想象自己站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧所能看到的节点值。
 */
public class rightSideViewSolution {

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

    /**
     * 2. 核心算法：二叉树的右视图
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 创建用于存储结果的动态数组
        List<Integer> res = new ArrayList<>();
        
        // 边界处理：如果根节点为空，直接返回空的结果列表
        if (root == null) {
            return res;
        }

        // 使用 Deque 作为队列进行广度优先搜索 (BFS)
        // LinkedList 实现了 Deque 接口，适合作为队列使用
        Deque<TreeNode> queue = new LinkedList<>();
        
        // 将根节点放入队尾，开始遍历
        queue.offer(root);

        // 只要队列不为空，就继续按层遍历
        while (!queue.isEmpty()) {
            // 获取当前层的节点总数
            int size = queue.size();
            
            // 循环处理当前层的所有节点
            while (size > 0) {
                // 从队首取出一个节点
                TreeNode cur = queue.poll();
                
                // 每取出一个节点，当前层待处理计数就减 1
                size--;

                // 【核心判断】：如果当前 size 减到了 0，说明它是本层最后一个节点
                // 根据右视图定义，层序遍历的最后一个节点就是从右侧能看到的节点
                if (size == 0) {
                    res.add(cur.val);
                }

                // 按照从左到右的顺序将下一层节点加入队列
                // 先放左孩子
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                // 后放右孩子
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        // 返回最终收集到的所有层的最右侧节点
        return res;
    }

    /**
     * 3. 主函数：ACM 模式输入输出处理
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        rightSideViewSolution solution = new rightSideViewSolution();

        // 循环读取每一行层序遍历输入的字符串（例如: 1 2 3 null 5 null 4）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 调用 buildTree 辅助方法构建树结构
            TreeNode root = buildTree(line);

            // 执行算法并获取结果
            List<Integer> result = solution.rightSideView(root);

            // 按照题目要求的格式打印结果
            System.out.println(result);
        }
        sc.close();
    }

    /**
     * 4. 辅助工具：根据层序遍历序列构建二叉树
     */
    private static TreeNode buildTree(String data) {
        // 按空格切分字符串
        String[] nodes = data.split("\\s+");
        // 如果数组为空或者第一个是 null，说明树不存在
        if (nodes.length == 0 || nodes[0].equals("null")) return null;

        // 创建根节点并加入队列准备为其寻找左右子节点
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1; // 数组索引指针
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll();

            // 处理左孩子：如果不是 null 则创建节点并关联，同时入队
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;

            // 处理右孩子：逻辑同上，需注意判断数组是否越界
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}