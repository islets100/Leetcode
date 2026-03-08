package ispalindrome;
import java.util.*;


/*
234. 回文链表
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
如果是，返回 true ；否则，返回 false 。
*/

// 定义链表节点
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class isPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // --- 构建链表部分 ---
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt(); // 读取节点数量
        
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (int i = 0; i < n; i++) {
            p.next = new ListNode(sc.nextInt());
            p = p.next;
        }
        
        // 执行算法并输出结果
        boolean result = isPalindromeSolution(dummy.next);
        System.out.println(result);
        sc.close();
    }

    /**
     * 核心算法：利用 ArrayList 转换后使用双指针判断
     */
    public static boolean isPalindromeSolution(ListNode head) {
        // 1. 创建一个动态数组，用来存储链表中的值
        List<Integer> vals = new ArrayList<Integer>();

        // 2. 遍历链表，将每个节点的值“复制”到数组中
        ListNode currentNode = head;
        while(currentNode != null){
            vals.add(currentNode.val); // 将值存入数组
            currentNode = currentNode.next; // 移动到下一个节点
        }

        // 3. 使用双指针（一个头，一个尾）向中间靠拢
        int front = 0; // 头指针
        int back = vals.size() - 1; // 尾指针
        
        while(front < back){
            // 4. 比较两个指针指向的值
            // 注意：Java 中 Integer 比较建议用 .equals() 
            // 主要是为了处理超过 -128 到 127 范围的自动装箱缓存问题
            if(!vals.get(front).equals(vals.get(back))){
                return false; // 只要有一对不相等，就不是回文
            }
            front++; // 头指针后移
            back--;  // 尾指针前移
        }
        
        // 5. 如果指针相遇都没有发现不等的，说明是回文
        return true;
    }
}