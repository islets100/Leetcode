import java.util.*;

/**236.二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class lowestCommonAncestorSolution {

    // 1. 定义二叉树节点类
    static class TreeNode {
        int val;        // 节点的值
        TreeNode left;  // 指向左孩子的指针
        TreeNode right; // 指向右孩子的指针
        
        // 构造方法
        TreeNode(int x) { 
            val = x; 
        }
    }

    // 2. 成员变量
    // parent 用于存储关系：Key 为孩子节点的值，Value 为该孩子的父亲节点对象
    // 通过这个 Map，我们可以实现从孩子“逆流而上”找到父亲
    Map<Integer, TreeNode> parent = new HashMap<>();
    
    // visited 用于存储路径：记录节点 p 在向上爬到根节点的搜索过程中路过的所有节点
    Set<Integer> visited = new HashSet<Integer>();

    /**
     * 3. 辅助函数：深度优先搜索 (DFS)
     * 作用：遍历整棵树，把所有的“父子关系”反向记录到 parent 这个 Map 里
     */
    public void dfs(TreeNode root) {
        // 如果左孩子不为空
        if (root.left != null) {
            // 记录：左孩子(root.left.val) 的父亲是当前的 root
            parent.put(root.left.val, root);
            // 继续递归遍历左子树
            dfs(root.left);
        }
        // 如果右孩子不为空
        if (root.right != null) {
            // 记录：右孩子(root.right.val) 的父亲是当前的 root
            parent.put(root.right.val, root);
            // 继续递归遍历右子树
            dfs(root.right);
        }
    }

    /**
     * 4. 核心算法：寻找最近公共祖先 (LCA)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // [第一步]：执行 DFS，建立全树的父子关系映射表
        dfs(root);

        // [第二步]：让 p 沿着父节点指针一路向上爬
        // 只要 p 没爬出根节点（即 p != null）
        while (p != null) {
            // 将当前爬到的节点值记录到“已访问”集合中
            visited.add(p.val);
            // 关键：p 指向自己的父亲。如果 parent.get 找不到，说明 p 已经是根，返回 null 给 p
            p = parent.get(p.val);
        }

        // [第三步]：让 q 也沿着父节点指针一路向上爬
        while (q != null) {
            // 每爬到一个节点，就检查这个节点是否被 p 访问过
            if (visited.contains(q.val)) {
                // 如果在 visited 里找到了，说明这个节点是 p 和 q 向上爬过程中第一个遇到的共同节点
                // 也就是我们要找的“最近公共祖先”
                return q;
            }
            // 没找到就继续向上爬，去找 q 的父亲
            q = parent.get(q.val);
        }

        // 如果树的结构正常且 p, q 都在树中，理论上不会走到这里
        return null;
    }

    /**
     * 5. 主函数：ACM 模式处理输入输出
     */
    public static void main(String[] args) {
        // 创建扫描器读取输入
        Scanner sc = new Scanner(System.in);
        // 实例化解题类
        lowestCommonAncestorSolution solution = new lowestCommonAncestorSolution();

        // 循环读取每一组数据
        while (sc.hasNextLine()) {
            // 输入示例：
            // 3 5 1 6 2 0 8 null null 7 4 (树的层序遍历)
            // 5 1 (节点 p 和 q 的值)
            String treeLine = sc.nextLine().trim();
            if (treeLine.isEmpty()) continue;
            
            String pqLine = sc.nextLine().trim();
            String[] pqVals = pqLine.split("\\s+");
            int pVal = Integer.parseInt(pqVals[0]);
            int qVal = Integer.parseInt(pqVals[1]);

            // [A 步骤]：根据层序序列构建二叉树
            TreeNode root = buildTree(treeLine);
            // [B 步骤]：在构建好的树中找到值等于 pVal 和 qVal 的节点对象
            TreeNode p = findNode(root, pVal);
            TreeNode q = findNode(root, qVal);

            // [C 步骤]：执行算法并输出结果
            TreeNode lca = solution.lowestCommonAncestor(root, p, q);
            System.out.println(lca != null ? lca.val : "null");
            
            // 重要：为了下一组数据，清空成员变量
            solution.parent.clear();
            solution.visited.clear();
        }
        sc.close();
    }

    /**
     * 辅助工具：根据值查找树中的节点对象
     */
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }

    /**
     * 辅助工具：层序遍历构建二叉树
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
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.left);
            }
            i++;
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}