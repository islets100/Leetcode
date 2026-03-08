import java.util.*;

/*
19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
*/
public class removeNthFromEnd {

    // 1. 定义链表节点为静态内部类
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：处理输入
        // 格式：n (长度) k (倒数第几个) 然后是 n 个元素
        while (sc.hasNextInt()) {
            int len = sc.nextInt();
            int n = sc.nextInt();
            ListNode head = buildList(sc, len);

            // 调用核心算法
            ListNode result = removeNthFromEndSolution(head, n);

            // 打印结果
            printList(result);
        }
        sc.close();
    }

    /**
     * 核心算法：利用栈删除倒数第 n 个节点
     */
    public static ListNode removeNthFromEndSolution(ListNode head, int n) {
        // 1. 创建哑节点（dummy），并指向 head。
        // 这样即使删除的是头节点，由于 dummy 依然存在，逻辑也能统一。
        ListNode dummy = new ListNode(0, head);
        
        // 2. 创建一个栈（双端队列实现），用于存储链表的所有节点引用。
        Deque<ListNode> stack = new LinkedList<ListNode>(); 
        
        // 3. 遍历链表（包括 dummy），将所有节点按顺序压入栈中。
        ListNode cur = dummy;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        // 4. 弹出 n 个节点。
        // 根据栈的特性，弹出的第 n 个节点就是我们要删除的那个节点。
        for(int i = 0; i < n; i++) {
            stack.pop();
        }

        // 5. 此时，栈顶的节点就是被删除节点的“前一个节点”（prev）。
        // 因为我们之前压入了 dummy，所以即使删除 head，栈里也至少剩下一个 dummy。
        ListNode prev = stack.peek();

        // 6. 执行删除操作：跳过被删节点，让 prev 指向“被删节点的下一个”。
        // prev.next 原本指向要删的节点，现在改为指向 prev.next.next。
        prev.next = prev.next.next;

        // 7. 返回真正的链表头。
        return dummy.next; 
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