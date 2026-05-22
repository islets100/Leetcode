import java.util.Scanner;

/**
 * 题号：LeetCode 92
 * 题目：反转链表 II (Reverse Linked List II)
 * 题目内容：给你单链表的头节点 head 和两个整数 left 和 right，
 * 其中 left <= right。请你反转从位置 left 到位置 right 的链表节点，
 * 返回反转后的链表。位置从 1 开始计数。
 *
 * 解题思路：头插法 / 局部反转
 * 1. 用哑节点 dummy 避免 head 在 left=1 时被改动带来的边界问题。
 * 2. 指针 p0 走到 left 的前一个节点，作为“反转段”的前驱。
 * 3. 对 [left, right] 区间做标准的迭代反转，得到 pre（新区间的头）和 cur（后继未反转部分）。
 * 4. 将 p0.next（原 left 节点，反转后变成尾）接到 cur，再把 p0.next 指向 pre，完成拼接。
 */
public class reverseListIISolution {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 核心算法：反转 [left, right] 区间
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 1. 哑节点：统一处理 left = 1 时头节点参与反转的情况
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 2. p0 停在第 left 个节点的前驱（第 left-1 次 next 后到位）
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        // 3. 区间内标准三指针反转：pre 已反转部分头，cur 待处理节点
        ListNode pre = null;
        ListNode cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 4. 拼接：p0.next 是反转前的 left 节点，反转后它变成区间尾部
        p0.next.next = cur;
        p0.next = pre;

        return dummy.next;
    }

    private static ListNode buildList(Scanner sc, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" ");
            }
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        reverseListIISolution sol = new reverseListIISolution();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            ListNode head = buildList(sc, n);
            int left = sc.nextInt();
            int right = sc.nextInt();
            ListNode result = sol.reverseBetween(head, left, right);
            printList(result);
        }
        sc.close();
    }
}
