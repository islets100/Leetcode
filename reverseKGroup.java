import java.util.Scanner;

/*hard
25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，
请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。
如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
*/
public class reverseKGroup {

    // 1. 定义链表节点
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ACM 模式：输入格式通常为 n (总长) k (翻转组大小) 然后是 n 个数字
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            
            // 调用你指定的 buildList(sc, n) 形式
            ListNode head = buildList(sc, n);

            // 调用核心算法
            ListNode result = reverseKGroupSolution(head, k);

            // 打印结果
            printList(result);
        }
        sc.close();
    }

    /**
     * 核心算法：K个一组翻转链表
     */
    public static ListNode reverseKGroupSolution(ListNode head, int k) {
        // 创建哑节点，hair.next 永远指向链表的开头
        ListNode hair = new ListNode(0);
        hair.next = head;
        // pre 指向“当前待翻转小组”的前一个节点，初始指向哑节点
        ListNode pre = hair;

        while (head != null) {
            // tail 初始指向 pre，我们要向后走 k 步找到这一组的结尾
            ListNode tail = pre;
            
            // 1. 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                // 如果不足 k 个，说明到了链表末尾且不满一组，根据题意不需要翻转
                if (tail == null) {
                    return hair.next;
                }
            }
            
            // 2. 记录下一组的开头（nex），防止翻转这一组后找不到后面的路
            ListNode nex = tail.next;
            
            // 3. 翻转这一组 [head, tail]
            // reverse 数组：index 0 是翻转后的新头，index 1 是翻转后的新尾
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0]; // 对应原来的 tail
            tail = reverse[1]; // 对应原来的 head

            // 4. 把子链表重新接回原链表
            // 让上一组的结尾指向这一组的新开头
            pre.next = head;
            // 让这一组的新结尾指向下一组的开头
            tail.next = nex;
            
            // 5. 准备处理下一组：pre 挪到这一组的末尾，head 指向下一组开头
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    /**
     * 翻转子函数：翻转从 head 到 tail 的部分
     */
    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        // 【核心】prev 初始设为 tail.next，而不是 null
        // 这样在翻转第一个节点时，它的 next 就会直接连上下一组的开头
        ListNode prev = tail.next;
        ListNode p = head;
        
        // 当 prev 还没有变成 tail 的时候，说明这一组还没翻转完
        while (prev != tail) {
            // 1. 临时保存下一个要翻转的节点
            ListNode nex = p.next;
            // 2. 指针反转：让当前节点指向它前面的节点 (第一次执行时，指向下一组开头)
            p.next = prev;
            // 3. 两个指针顺次后移，准备翻转下一个
            prev = p;
            p = nex;
        }
        
        // 翻转完成后，原来的 tail 变成了头，原来的 head 变成了尾
        return new ListNode[]{tail, head};
    }

    // --- 辅助工具：构建链表 (Scanner + 限制长度 n) ---
    private static ListNode buildList(Scanner sc, int n) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) {
                curr.next = new ListNode(sc.nextInt());
                curr = curr.next;
            }
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