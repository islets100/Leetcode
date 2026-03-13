import java.util.*;

/**
 * 114.二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
展开后的单链表应该同样使用 TreeNode ，
其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class flattenSolution {

    /**
     * 1. 节点类定义
     */
    static class TreeNode {
        int val;                // 节点值
        TreeNode left;          // 左孩子指针
        TreeNode right;         // 右孩子指针

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 2. 核心算法：将二叉树展开为单链表
     */
    public void flatten(TreeNode root) {
        // 创建一个列表，用于按先序遍历的顺序存放节点
        List<TreeNode> list = new ArrayList<TreeNode>();
        
        // 调用递归函数进行先序遍历，并填充列表
        preorderTraversal(root, list);
        
        // 获取节点的总数量
        int size = list.size();
        
        // 遍历列表，重新调整节点的指针（注意循环到 size - 1 停止，最后一个节点不用处理）
        for(int i = 0; i < size - 1; i++){
            // 取出当前节点和它的先序下一个节点
            TreeNode cur = list.get(i);
            TreeNode next = list.get(i + 1);
            
            // 按照题目要求：左子树必须为空
            cur.left = null;
            
            // 右子树指向先序遍历序列中的下一个节点
            cur.right = next;
        }
    }

    /**
     * 辅助递归函数：先序遍历（根 -> 左 -> 右）
     * @param root 当前遍历到的节点
     * @param list 存放节点引用的列表
     */
    public void preorderTraversal(TreeNode root, List<TreeNode> list){
        // 递归终止条件：节点为空
        if(root != null){
            // 访问根节点：将当前节点存入列表
            list.add(root);
            
            // 递归遍历左子树
            preorderTraversal(root.left, list);
            
            // 递归遍历右子树
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 3. 主函数：处理 ACM 模式输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取键盘或文件输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        flattenSolution solution = new flattenSolution();

        // 循环读取输入的层序序列（例如: 1 2 5 3 4 null 6）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // 调用 buildTree 辅助方法将层序字符串转为二叉树
            TreeNode root = buildTree(line);

            // 执行展开算法
            solution.flatten(root);

            // 展开后打印结果（沿着右子树一直走到底）
            printFlattenedTree(root);
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

            // 填充左孩子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;

            // 填充右孩子
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 5. 辅助工具：打印展开后的链表形式二叉树
     */
    private static void printFlattenedTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        TreeNode curr = root;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.right != null) {
                sb.append(" -> ");
            }
            // 检查左孩子是否按要求设为了 null，防止死循环或错误
            if (curr.left != null) {
                System.out.println("错误：节点 " + curr.val + " 的左子树不为空！");
            }
            curr = curr.right;
        }
        System.out.println(sb.toString());
    }
}