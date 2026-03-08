package reverselist;
import java.util.Scanner;


/*
206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
*/

// 定义链表节点
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class reverseList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ACM 模式通常需要处理多组数据，或者先读入长度
        System.out.println("请输入链表节点个数：");
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        // 构建链表（使用哑节点简化逻辑）
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        System.out.println("请输入节点数值：");
        for (int i = 0; i < n; i++) {
            p.next = new ListNode(sc.nextInt());
            p = p.next;
        }
        
        // 调用算法
        ListNode newHead = reverseListSolution(dummy.next);
        
        // 输出结果
        System.out.print("反转后的链表为：");
        ListNode curr = newHead;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
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
}