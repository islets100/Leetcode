import java.util.Scanner;


/*这里使用的是归并排序
148. 排序链表
给你链表的头结点 head ，
请将其按 升序 排列并返回 排序后的链表 。
*/

public class sortList {

    // 1. 定义单链表节点
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：循环读取输入行
        while (sc.hasNextLine()) {
            ListNode head = buildList(sc); // 使用重载的自由输入构建函数
            if (head == null) continue;

            // 调用核心算法
            ListNode result = sortListSolution(head);

            // 打印排序后的结果
            printList(result);
        }
        sc.close();
    }

    /**
     * 核心算法：入口函数
     */
    public static ListNode sortListSolution(ListNode head) {
        // 初始调用，排序范围从 head 到 null (左闭右开区间)
        return sortListRecursive(head, null);
    }

    /**
     * 递归函数：排序区间 [head, tail) 内的节点
     */
    public static ListNode sortListRecursive(ListNode head, ListNode tail) {
        // 1. 基础情况：如果 head 为空，无需排序
        if (head == null) {
            return head;
        }
        // 2. 基础情况：如果区间内只有一个节点 (head.next 为 tail)
        if (head.next == tail) {
            // 【关键动作】断开连接。只有物理上断开，merge 函数才能通过判断 null 结束
            head.next = null;
            return head;
        }

        // 3. 寻找区间中点：快慢指针法
        ListNode fast = head, slow = head;
        // 当快指针没有达到“终点线” tail 时继续移动
        while (fast != tail) {
            slow = slow.next;        // 慢指针走 1 步
            fast = fast.next;        // 快指针走 1 步
            if (fast != tail) {
                fast = fast.next;    // 快指针再走 1 步，总共走 2 步
            }
        }
        // 循环结束时，slow 指向的就是区间的“中点”
        ListNode mid = slow;

        // 4. 分治：分别递归排序左半部分和右半部分
        // 注意：区间是左闭右开，所以左边是 [head, mid)，右边是 [mid, tail)
        ListNode list1 = sortListRecursive(head, mid);
        ListNode list2 = sortListRecursive(mid, tail);

        // 5. 合并：将两个有序的子链表合并为一个
        return merge(list1, list2);
    }

    /**
     * 合并函数：拉链法合并两个有序链表
     */
    public static ListNode merge(ListNode list1, ListNode list2) {
        // 使用哑节点简化头部连接逻辑
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // 只要两个链表都不为空，就比较它们的值
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;   // 接上小的
                list1 = list1.next;  // 指针后移
            } else {
                curr.next = list2;   // 接上小的
                list2 = list2.next;  // 指针后移
            }
            curr = curr.next;        // “施工员”后移
        }

        // 6. 收尾：如果其中一个链表走完了，直接接上剩下的那一部分
        curr.next = (list1 == null) ? list2 : list1;

        return dummy.next;
    }

    // --- 辅助工具：构建链表 (支持空格分隔的一行数字) ---
    private static ListNode buildList(Scanner sc) {
        if (!sc.hasNextLine()) return null;
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return null;

        String[] nums = line.split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String num : nums) {
            curr.next = new ListNode(Integer.parseInt(num));
            curr = curr.next;
        }
        return dummy.next;
    }

    // --- 辅助工具：打印链表 ---
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
        System.out.println();
    }
}