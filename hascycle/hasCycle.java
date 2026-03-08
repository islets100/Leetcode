package hascycle;
import java.util.Scanner;


/*
141. 环形链表
给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
如果链表中存在环，则返回 true 。 否则，返回 false 。
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

public class hasCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- ACM 标准输入处理 ---
        // 约定输入格式：
        // 第一行：n (节点个数), pos (环连接的位置，从0开始索引；-1表示无环)
        // 第二行：n个节点的具体数值
        //while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int pos = sc.nextInt();
            
            if (n == 0) {
                System.out.println("false");
                //continue;
            }

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            // --- 构建链表并制造环 ---
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            ListNode cycleNode = null; // 用来记录环开始的那个节点
            ListNode tail = null;      // 用来记录最后一个节点

            for (int i = 0; i < n; i++) {
                curr.next = new ListNode(nums[i]);
                curr = curr.next;
                if (i == pos) {
                    cycleNode = curr; // 记下第 pos 个节点
                }
                if (i == n - 1) {
                    tail = curr;      // 记下最后一个节点
                }
            }

            // 如果 pos 不是 -1，则把尾巴连到指定节点形成环
            if (pos != -1 && tail != null) {
                tail.next = cycleNode;
            }

            // --- 执行算法 ---
            hasCycleSolution sol = new hasCycleSolution();
            System.out.println(sol.hasCycle(dummy.next));
            sc.close();
        //}
    }
}

class hasCycleSolution {
    /**
     * 核心算法：快慢指针 (Floyd's Cycle-Finding Algorithm)
     */
    public boolean hasCycle(ListNode head) {
        // 边界处理：空链表或单节点链表（无自环）肯定没环
        if (head == null || head.next == null) {
            return false;
        }

        // 初始化：慢指针走一步，快指针走两步（起点错开以启动循环）
        ListNode slow = head;
        ListNode fast = head.next;

        // 追逐逻辑
        while (slow != fast) {
            // 只要快指针发现尽头(null)，说明是直线，没环
            if (fast == null || fast.next == null) {
                return false;
            }

            // 慢指针步长为 1
            slow = slow.next;
            // 快指针步长为 2
            fast = fast.next.next;
        }

        // 能够跳出循环说明 slow == fast，即快指针套圈追上了慢指针
        return true;
    }
}