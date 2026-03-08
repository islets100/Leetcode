package wherehascycle;
import java.util.Scanner;


/*
142. 环形链表 II
给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
如果链表中存在环，则返回 环 的入口节点。 否则，返回 null。
*/

// 1. 定义链表节点
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class whereHasCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- 标准输入处理 ---
        // 期望输入格式：
        // 第一行：n (节点个数), pos (环连接的位置，从0开始；-1表示无环)
        // 第二行：n个节点的具体数值
        //while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int pos = sc.nextInt();

            if (n == 0) {
                System.out.println("null");
                //continue;
            }

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            // --- 构造带环链表 ---
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            ListNode cycleNode = null; // 用于记录环的入口
            ListNode tail = null;      // 用于记录尾节点

            for (int i = 0; i < n; i++) {
                curr.next = new ListNode(nums[i]);
                curr = curr.next;
                if (i == pos) {
                    cycleNode = curr; // 记录下 pos 位置的节点
                }
                if (i == n - 1) {
                    tail = curr;      // 记录尾部
                }
            }

            // 如果 pos 不是 -1，制造环
            if (pos != -1 && tail != null) {
                tail.next = cycleNode;
            }

            // --- 执行算法 ---
            ListNode result = detectCycleSolution(dummy.next);

            // --- 输出结果 ---
            if (result != null) {
                // ACM 模式通常要求输出入环节点的数值或索引
                System.out.println("tail connects to node index " + pos + " with value " + result.val);
            } else {
                System.out.println("no cycle");
            }
        //}
        sc.close();
    }

    /**
     * 核心算法：寻找环入口节点
     * 注意：在 Main 类中直接调用需要定义为 static
     */
    public static ListNode detectCycleSolution(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // 阶段一：快慢指针从起点同步出发
        ListNode slow = head;
        ListNode fast = head;

        

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 发现相遇点
            if (slow == fast) {
                // 阶段二：利用数学原理寻找到入口
                // a = (n-1)(b+c) + c
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr; // 再次相遇点即为入口
            }
        }
        return null;
    }
}
