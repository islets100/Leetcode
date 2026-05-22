import java.util.Scanner;


/*
160. 相交链表
给你两个单链表的头节点 headA 和 headB ，
请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
*/

// 定义链表节点
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class getIntersectionNode {
// 核心算法
    public static ListNode getIntersectionNodeSolution(ListNode headA, ListNode headB) {
        // 1. 基础判断：如果有一个是空的，那肯定没戏（不相交），直接返回 null
        if (headA == null || headB == null) {
            return null;
        }

        // 2. 初始化：两个指针分别指在两个链表的头
        ListNode pA = headA, pB = headB;

        // 3. 循环条件：只要 pA 和 pB 还没指到同一个地方，就继续走
        while (pA != pB) {
            // pA 往后挪：如果走到尽头（null）了，就换到 B 链表头；没到尽头就继续 next
            pA = (pA == null) ? headB : pA.next;

            // pB 往后挪：如果走到尽头（null）了，就换到 A 链表头；没到尽头就继续 next
            pB = (pB == null) ? headA : pB.next;
        }

        // 4. 退出循环只有两种可能：
        // (1) pA == pB，且它们都不是 null：说明找到了交点。
        // (2) pA == pB == null：说明两条路都走完了还没遇到，那就是不相交。
        return pA;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 为了模拟测试，我们需要手动构建相交逻辑
        // 假设输入格式：
        // 3 3 2 (A独有长度, B独有长度, 公共部分长度)
        // 4 1 (A独有元素)
        // 5 6 1 (B独有元素)
        // 8 4 5 (公共元素)
        
        if (!sc.hasNextInt()) return;
        int lenA = sc.nextInt();
        int lenB = sc.nextInt();
        int lenCommon = sc.nextInt();

        // 构造 A 独有部分
        ListNode headA = new ListNode(-1), pA = headA;
        for (int i = 0; i < lenA; i++) {
            pA.next = new ListNode(sc.nextInt());
            pA = pA.next;
        }

        // 构造 B 独有部分
        ListNode headB = new ListNode(-1), pB = headB;
        for (int i = 0; i < lenB; i++) {
            pB.next = new ListNode(sc.nextInt());
            pB = pB.next;
        }

        // 构造公共部分并连接
        ListNode commonHead = new ListNode(-1), pC = commonHead;
        for (int i = 0; i < lenCommon; i++) {
            pC.next = new ListNode(sc.nextInt());
            pC = pC.next;
        }

        // 斩断哑节点的头，并连接公共部分
        headA = headA.next;
        headB = headB.next;
        pA.next = commonHead.next;
        pB.next = commonHead.next;

        // 执行算法
        ListNode result = getIntersectionNodeSolution(headA, headB);

        // 输出结果
        if (result != null) {
            System.out.println("Intersected at '" + result.val + "'");
        } else {
            System.out.println("No intersection");
        }
    }


}