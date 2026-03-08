import java.util.Scanner;

/*
21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。
新链表是通过拼接给定的两个链表的所有节点组成的。 
*/

public class mergeTwoLists {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：处理多组输入或读取数组长度
        while (sc.hasNextInt()) {
            // 读取第一条链表的长度
            int n1 = sc.nextInt();
            ListNode list1 = buildList(sc, n1);

            // 读取第二条链表的长度
            int n2 = sc.nextInt();
            ListNode list2 = buildList(sc, n2);

            // 执行核心算法
            ListNode result = mergeTwoListsSolution(list1, list2);

            // 打印结果
            printList(result);
        }
        sc.close();
    }

    /**
     * 核心算法：迭代合并两个有序链表
     */
    public static ListNode mergeTwoListsSolution(ListNode list1, ListNode list2) {
        // 1. 创建哑节点 dummy，它是新链表的“锚点”，val 为 -1 无实际意义
        ListNode dummy = new ListNode(-1);
        // 2. curr 是“施工员”，负责不断往 dummy 后面接节点
        ListNode curr = dummy;

        // 3. 当两个链表都不为空时，进行比较
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                // 如果 list1 的值小，就把 curr 接到 list1 上
                curr.next = list1;
                // list1 往前走一步
                list1 = list1.next;
            } else {
                // 如果 list2 的值小，就把 curr 接到 list2 上
                curr.next = list2;
                // list2 往前走一步
                list2 = list2.next;
            }
            // 施工员 curr 也要往前挪，准备接下一个
            curr = curr.next;
        }

        // 4. 重点：当其中一个链表走完了，另一个还没走完
        // 因为链表本身是有序的，我们直接把没走完的那一整串接到 curr 后面即可
        curr.next = (list1 == null) ? list2 : list1;

        // 5. 返回哑节点的下一个，即真正的合并后头节点
        return dummy.next;
    }

    // --- 辅助方法：构建链表 ---
    private static ListNode buildList(Scanner sc, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        return dummy.next;
    }

    // --- 辅助方法：打印链表 ---
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }
}