// 导入处理输入输出、列表、队列和链表的工具包
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/*
226.翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */

public class invertTreeSolution {

    /**
     * 1. 节点的定义
     * 就像造房子需要砖块，二叉树的砖块就是 TreeNode
     */
    static class TreeNode {
        int val;                // 节点里存的数字
        TreeNode left;          // 指向左边孩子的“手”
        TreeNode right;         // 指向右边孩子的“手”

        TreeNode() {}           // 空构造方法：为了能 new TreeNode()
        
        TreeNode(int val) {     // 带值的构造方法：创建节点时直接塞入数字
            this.val = val; 
        }
    }

    /**
     * 2. 核心算法：翻转二叉树
     * 思路：像照镜子一样，把左右两只“手”互换
     */
    public static TreeNode invertTree(TreeNode root) {
        // [第1步] 递归的出口：如果手里的节点是空的，说明到底了，直接返回 null
        if (root == null) {
            return null;
        }

        // [第2步] 递归处理：先不管当前节点，先让左孩子去把自己那棵小树翻转好
        // 返回的结果暂存在 left 变量里
        TreeNode left = invertTree(root.left);

        // [第3步] 递归处理：再让右孩子去把自己那棵小树翻转好
        // 返回的结果暂存在 right 变量里
        TreeNode right = invertTree(root.right);

        // [第4步] 交换位置：把刚才翻转好的右边小树，接到当前节点的左手边
        root.left = right;

        // [第5步] 交换位置：把刚才翻转好的左边小树，接到当前节点的右手边
        root.right = left;

        // [第6步] 处理完毕：把这个已经交换好左右手的节点（根）向上返回
        return root;
    }

    /**
     * 3. 运行的主函数
     */
    public static void main(String[] args) {
        // 创建一个扫描器，用来读取你在键盘输入的内容
        Scanner sc = new Scanner(System.in);
        
        // 只要你还在输入（按回车没结束），就一直循环运行
        while (sc.hasNextLine()) {
            // 读取你输入的那一行字符串，并删掉两头的空格
            String line = sc.nextLine().trim();
            
            // 如果输入是空的，就跳过这次，等下次输入
            if (line.isEmpty()) {
                continue;
            }
            
            // [A 步骤]：把字符串（比如 "4 2 7"）转成内存里的一棵树
            TreeNode root = buildTree(line);
            
            // [B 步骤]：调用我们上面写好的翻转函数
            TreeNode invertedRoot = invertTree(root);
            
            // [C 步骤]：把翻转后的树打印出来看看对不对
            printTree(invertedRoot);
        }
        
        // 关闭扫描器，节省电脑内存资源
        sc.close();
    }

    /**
     * 4. 辅助函数：如何把输入的字符串变成一棵树
     * 这个逻辑稍微复杂，是用“队列”按层级填充的
     */
    private static TreeNode buildTree(String data) {
        // 将字符串按空格切开，得到一个字符串数组，比如 ["4", "2", "7"]
        String[] nodes = data.split("\\s+");
        
        // 如果第一个是 null，直接返回空树
        if (nodes[0].equals("null")) return null;
        
        // 1. 先创建最顶上的根节点，把字符串转成整数：Integer.parseInt
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        
        // 2. 创建一个队列，用来暂存那些“还没分配孩子”的父亲节点
        // 这里用 LinkedList 是因为 Java 里 Queue 是接口，需要实现类
        Queue<TreeNode> queue = new LinkedList<>();
        
        // 3. 把根节点塞进队列，它作为第一个“待分配”的父亲
        queue.add(root);
        
        // i 是数组的下标，从 1 开始（因为 0 已经被根节点用了）
        int i = 1;
        
        // 只要队列不空，且数组里还有数字没用完，就继续
        while (!queue.isEmpty() && i < nodes.length) {
            // 从队列头取出一个父亲
            TreeNode curr = queue.poll();
            
            // --- 处理左边 ---
            // 如果数组里不是 "null"，说明这个父亲有左孩子
            if (!nodes[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(nodes[i]));
                // 关键：左孩子也要进队，因为它以后也要当父亲，去领它的孩子
                queue.add(curr.left);
            }
            i++; // 数组下标往后走一位
            
            // --- 处理右边 ---
            // 注意这里要再次判断 i 是否越界
            if (i < nodes.length && !nodes[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(nodes[i]));
                // 右孩子也要进队待命
                queue.add(curr.right);
            }
            i++; // 数组下标再往后走一位
        }
        // 返回构建好的整棵树
        return root;
    }

    /**
     * 5. 辅助函数：打印树（把树变回字符串输出）
     */
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        // 用来存打印结果的列表
        List<String> res = new ArrayList<>();
        // 还是用队列层序遍历一遍
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                res.add("null");
            } else {
                res.add(String.valueOf(node.val)); // 存入节点的值
                // 把左右孩子加进去（哪怕是 null 也要加，才能看到空位）
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        // 把结果用空格拼起来打印
        System.out.println(String.join(" ", res));
    }
}