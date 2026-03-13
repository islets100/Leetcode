import java.util.*;

/**105.用中序和前序遍历构建二叉树
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历，
 *  inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class buildTreeSolution {

    // 1. 定义二叉树节点类
    static class TreeNode {
        int val;                // 节点存储的整数值
        TreeNode left;          // 指向左子节点的指针
        TreeNode right;         // 指向右子节点的指针

        TreeNode() {}
        TreeNode(int val) { 
            this.val = val; 
        }
    }

    // 2. 成员变量
    // 使用 HashMap 存储中序遍历值与索引的映射，实现 O(1) 查找根节点位置
    Map<Integer, Integer> map = new HashMap<>();
    // 将前序遍历数组设为成员变量，避免在递归中重复传递大数组
    int[] preorder;

    /**
     * 3. 核心算法入口：根据前序和中序序列构造二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 将传入的参数赋值给成员变量
        this.preorder = preorder;
        
        // 遍历中序数组，将每个值及其对应的索引存入 Map
        // 这样后面我们可以根据值直接拿到它在 inorder 中的位置
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        
        // 开始递归构建：
        // 参数1：根节点在前序数组中的起始位置（从0开始）
        // 参数2：子树在中序数组中的左边界
        // 参数3：子树在中序数组中的右边界
        return recur(0, 0, inorder.length - 1);
    }

    /**
     * 递归构建子树的工具方法
     * @param preroot 当前子树根节点在前序遍历数组中的索引
     * @param inleft  当前子树在中序遍历数组中的左边界索引
     * @param inright 当前子树在中序遍历数组中的右边界索引
     */
    TreeNode recur(int preroot, int inleft, int inright) {
        // [递归出口]：如果左边界超过了右边界，说明当前区间为空，没有节点了，返回 null
        if (inleft > inright) {
            return null;
        }

        // [建立根节点]：前序遍历的第一个元素（preroot）永远是当前子树的根节点
        TreeNode node = new TreeNode(preorder[preroot]);

        // [查找根节点位置]：通过 Map 找到根节点在中序遍历中的位置索引 i
        // 这样我们就知道了：inleft 到 i-1 是左子树，i+1 到 inright 是右子树
        int i = map.get(preorder[preroot]);

        // [递归构建左子树]
        // 参数1：左子树根节点在前序数组中紧跟在当前根节点后面，即 preroot + 1
        // 参数2 & 3：中序数组中根节点左边的范围 [inleft, i - 1]
        node.left = recur(preroot + 1, inleft, i - 1);

        // [递归构建右子树]
        // 参数1：右子树根节点在前序数组中的索引 = 当前根索引 + 左子树长度 + 1
        // 左子树长度 = i - inleft（因为 i 是根在 inorder 的索引，inleft 是起点）
        node.right = recur(preroot + (i - inleft) + 1, i + 1, inright);

        // 返回当前构建好的节点给父级调用者
        return node;
    }

    /**
     * 4. 主函数：处理 ACM 模式的输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器，用于读取控制台输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        buildTreeSolution solution = new buildTreeSolution();

        // 持续读取输入直到结束
        while (sc.hasNextLine()) {
            // 第一行输入前序遍历：1 2 4 5 3
            String preStr = sc.nextLine().trim();
            if (preStr.isEmpty()) continue;
            
            // 第二行输入中序遍历：4 2 5 1 3
            if (!sc.hasNextLine()) break;
            String inStr = sc.nextLine().trim();

            // 将读取到的字符串转换为 int 数组
            int[] preorder = Arrays.stream(preStr.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] inorder = Arrays.stream(inStr.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            // 5. 调用核心算法构建树
            TreeNode root = solution.buildTree(preorder, inorder);

            // 6. 打印结果（使用层序遍历验证树的结构是否正确）
            printLevelOrder(root);
        }
        sc.close(); // 养成良好习惯，关闭扫描器
    }

    /**
     * 辅助工具：层序遍历打印二叉树
     */
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<String> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // 打印出层序遍历结果，方便比对
        System.out.println("构建结果(层序): " + result);
    }
}