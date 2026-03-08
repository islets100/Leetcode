import java.util.Scanner;

/*
2. 两数相加
给你两个 非空 的链表，表示两个非负的整数。
它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
*/

public class addTwoNumbers {

    // 1. 将 ListNode 定义为静态内部类，确保独立性
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：处理多组测试数据
        while (sc.hasNextInt()) {
            // 输入格式示例：
            // 3 (第一个链表长度)
            // 2 4 3 (第一个链表元素)
            // 3 (第二个链表长度)
            // 5 6 4 (第二个链表元素)
            

            int n1 = sc.nextInt();
            ListNode l1 = buildList(sc, n1);

            int n2 = sc.nextInt();
            ListNode l2 = buildList(sc, n2);

            // 调用核心算法
            ListNode result = addTwoNumbersSolution(l1, l2);

            // 打印输出结果
            printList(result);
        }
        
        // 只有在所有输入处理完后才关闭 Scanner
        sc.close();
    }

    /**
     * 核心算法：两数相加 (使用 Dummy 哑节点改造版)
     */
    public static ListNode addTwoNumbersSolution(ListNode l1, ListNode l2) {
        // --- 逻辑解释 ---
        // 使用哑节点（dummy）可以避免在循环中判断 "是否为第一个节点"
        // 无论结果有多长，都统一地挂在 dummy 后面
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy; // curr 就像施工员，负责移动并接上新节点
        int carry = 0;         // 进位器，存储满十进一的那个 "1"

        // 只要 l1, l2 没走完，或者最后还有一个进位 (carry) 没处理，就得继续加
        while (l1 != null || l2 != null || carry != 0) {
            // 如果某条链表短，走完了就用 0 补位
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;

            // 计算当前位的总和
            int sum = n1 + n2 + carry;
            // 更新进位：例如 15 / 10 = 1
            carry = sum / 10;

            // 取余数创建新节点接在后面：例如 15 % 10 = 5
            curr.next = new ListNode(sum % 10);
            curr = curr.next; // 施工员向后挪一步

            // 移动原链表指针
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 返回哑节点的 next，即真正存储数字的起始节点
        return dummy.next;
    }

    // --- 辅助工具：根据输入构建链表 ---
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