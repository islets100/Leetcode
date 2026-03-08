import java.util.Scanner;

/*
24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，
并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
*/

public class swapListPairsNodes {

    // 定义静态内部类 ListNode，保证隔离性
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：循环处理输入
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            ListNode head = buildList(sc, n);

            // 调用核心算法
            ListNode result = swapPairsSolution(head);

            // 打印结果
            printList(result);
        }
        sc.close();
    }

    /**
     * 核心算法：迭代法两两交换节点
     */
    public static ListNode swapPairsSolution(ListNode head) {
        // 1. 创建哑节点，解决头节点被交换后的定位问题
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // 2. temp 是“控制指针”，它始终位于要交换的一对节点之前
        ListNode temp = dummy;

        // 3. 只有当后面至少有两个节点时，才需要交换
        while (temp.next != null && temp.next.next != null) {
            // 准备工作：标记出要交换的两个节点
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;

            // --- 开始“大换血” ---
            // 第一步：让前面的节点指向 node2（node2 变成这一对的新头）
            temp.next = node2;
            
            // 第二步：让 node1 指向 node2 的下一个（node1 变成这一对的新尾，连接后面）
            node1.next = node2.next;
            
            // 第三步：让 node2 指向 node1（完成两者的原地旋转）
            node2.next = node1;

            // 4. 指针重置：temp 挪到 node1 的位置，准备处理下一对
            // 此时 node1 已经是这一对的尾巴了，所以它后面就是下一组的开始
            temp = node1;
        }

        return dummy.next;
    }

// /**
//      * 重载版本 2：自动读取整行构建 (最方便，适合自由输入)
//      */
// private static ListNode buildList(Scanner sc) {
//     if (!sc.hasNextLine()) return null;
//     String line = sc.nextLine().trim();
    
//     // 过滤空行
//     while (line.isEmpty() && sc.hasNextLine()) {
//         line = sc.nextLine().trim();
//     }
//     if (line.isEmpty()) return null;

//     String[] nums = line.split("\\s+");
//     ListNode dummy = new ListNode(0);
//     ListNode curr = dummy;
//     for (String num : nums) {
//         try {
//             curr.next = new ListNode(Integer.parseInt(num));
//             curr = curr.next;
//         } catch (NumberFormatException e) {
//             // 防止输入了非数字字符
//             continue;
//         }
//     }
//     return dummy.next;
// }

    // --- 辅助工具：构建链表 ---
    private static ListNode buildList(Scanner sc, int n) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        return dummy.next;
    }

    

    // --- 辅助工具：打印链表 ---
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
        System.out.println();
    }
}