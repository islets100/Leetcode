import java.util.*;


/*
138. 复制带随机指针的链表
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
该指针可以指向链表中的任何节点或空节点。
构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，
其中每个新节点的值都设为其对应的原节点的值。
新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
并使原链表和复制链表中的这些指针能够表示相同的链表状态。
复制链表中的指针都不应指向原链表中的节点 。
*/

public class copyRandomList {
// 1. 定义节点类
     private static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入格式：n (节点数)
        // 接下来 n 行，每行两个数：val 和 random_index (-1 表示 null)
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("[]");
                continue;
            }

            int[][] input = new int[n][2];
            Node[] nodes = new Node[n];

            // 第一次读入：先创建所有节点并连成普通链表
            for (int i = 0; i < n; i++) {
                input[i][0] = sc.nextInt();
                input[i][1] = sc.nextInt();
                nodes[i] = new Node(input[i][0]);
            }

            for (int i = 0; i < n; i++) {
                if (i < n - 1) nodes[i].next = nodes[i + 1];
                // 根据读入的 index 连接 random 指针
                int randIdx = input[i][1];
                if (randIdx != -1) {
                    nodes[i].random = nodes[randIdx];
                }
            }

            // 执行核心算法
            Node newHead = copyRandomListSolution(nodes[0]);

            // 打印结果 (按照 val, random_index 格式验证)
            printRandomList(newHead);
        }
    }

    /**
     * 核心算法类方法
     */
    public static Node copyRandomListSolution(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 辅助工具：打印随机链表
    private static void printRandomList(Node head) {
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            int randIdx = list.indexOf(node.random);
            System.out.print("[" + node.val + "," + randIdx + "]" + (i == list.size() - 1 ? "" : ","));
        }
        System.out.println("]");
    }
}