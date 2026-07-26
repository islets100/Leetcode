import java.util.*;

/*
102.二叉树的层序遍历
给你二叉树的根节点 root 
，返回其节点值的 层序遍历 。
 （即逐层地，从左到右访问所有节点）。
*/

public class levelOrderSolution {

    // 1. 二叉树节点定义
    static class TreeNode {
        int val; // 节点值
        TreeNode left; // 左孩子指针
        TreeNode right; // 右孩子指针
        
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 创建扫描器读取输入
        Scanner sc = new Scanner(System.in);
        
        // 循环读取每一行层序序列（例如输入: 3 9 20 null null 15 7）
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue; // 跳过空行
            
            // 2. 将输入的字符串构建成二叉树
            TreeNode root = buildTree(line);
            
            // 3. 调用层序遍历算法
            List<List<Integer>> result = levelOrder(root);
            
            // 4. 打印结果，格式为 [[3], [9, 20], [15, 7]]
            System.out.println(result);
        }
        sc.close();
    }

    /**
     * 核心算法：层序遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 1. 初始化大列表，用于存储最终每一层的结果
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        // 2. 特判：如果根节点为空，直接返回空的结果列表
        if(root == null){
            return ret;
        }
    
        // 3. 创建队列，利用其先进先出 (FIFO) 的特性进行广度优先搜索
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        // 4. 将根节点放入队列作为起点
        queue.offer(root);
        
        // 5. 只要队列不为空，就说明还有节点没有被遍历到
        while(!queue.isEmpty()){
            // 6. 创建小列表，用于存储当前这一层的所有节点值
            List<Integer> level = new ArrayList<Integer>();
            
            // 7. 【关键】获取当前时刻队列的长度。
            // 这个长度代表了“当前这一层”一共有多少个节点
            int currentLevelSize = queue.size();
            
            // 8. 启动循环，只处理当前层的节点（循环 currentLevelSize 次）
            for(int i = 1; i <= currentLevelSize; i++){
                // 9. 从队首弹出一个节点
                TreeNode node = queue.poll();
                
                // 10. 将节点值加入本层的列表
                level.add(node.val);
                
                // 11. 如果左孩子不为空，将左孩子入队（它们会排在队尾，在下一层处理）
                if(node.left != null){
                    queue.offer(node.left);
                }
                // 12. 如果右孩子不为空，将右孩子入队
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            // 13. 当 for 循环结束，代表这一层所有节点都处理完了，将其加入大列表
            ret.add(level);
        }
    
        // 14. 返回最终结果
        return ret;
    }

    /**
     * 辅助工具：根据层序遍历序列构建二叉树
     */
    private static TreeNode buildTree(String data) {
        String[] nodes = data.split("\\s+"); // 切分输入
        if (nodes[0].equals("null")) return null; // 空树处理

        // 创建根节点并入队
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1; // 索引指针
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll(); // 弹出父节点

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
}