import java.util.*;

/*
23. 合并K个升序链表 ##
给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
*/
class mergeKLists {

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

    public ListNode mergeKListsSolution(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;

        return dummy.next;
    }

    private static ListNode buildList(Scanner sc) {
        if (!sc.hasNextLine())
            return null;
        String line = sc.nextLine().trim();
        if (line.isEmpty())
            return null;
        // 支持空格分隔的一行数字
        String[] nums = line.split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String num : nums) {
            curr.next = new ListNode(Integer.parseInt(num));
            curr = curr.next;
        }
        return dummy.next;
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mergeKLists solution = new mergeKLists();

        while (sc.hasNextLine()) {
            if (!sc.hasNextInt())
                break;
            int k = sc.nextInt();
            sc.nextLine(); // 消耗读取 k 后的换行符
            ListNode[] lists = new ListNode[k];

            for (int i = 0; i < k; i++) {
                lists[i] = buildList(sc);
            }

            // 调用核心算法
            ListNode result = solution.mergeKListsSolution(lists);
            // 打印排序后的结果
            printList(result);
        }
        sc.close();

    }
}