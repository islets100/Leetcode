
import java.util.Scanner;

/*
206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
*/

public class reverseList {
    // 定义链表节点为静态内部类，和其他链表题保持一致
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：循环处理输入
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            ListNode head = buildList(sc, n);

            // 调用核心算法
            ListNode result = reverseListSolution(head);

            // 打印结果
            printList(result);
        }
        sc.close();
    }

    // --- 核心算法：迭代反转 ---
    public static ListNode reverseListSolution(ListNode head) {
        // prev 记录当前节点的前一个节点，初始化为 null（因为反转后原来的头变成了尾）
        ListNode prev = null;
        // curr 记录当前正在处理的节点
        ListNode curr = head;

        while (curr != null) {
            // 1. 暂时保存当前节点的下一个节点（不然断开链接后就找不到了）
            ListNode next = curr.next;

            // 2. 反转指针方向：让当前节点指向它的前一个节点
            curr.next = prev;

            // 3. 准备处理下一个节点：prev 往前挪到当前位置
            prev = curr;

            // 4. curr 往前挪到刚才保存好的下一个位置
            curr = next;
        }

        // 当 curr 为空时，说明所有节点都反转完了，此时 prev 正好指向原链表的末尾（新链表的头）
        return prev;
    }

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