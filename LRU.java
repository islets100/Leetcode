import java.util.*;

/*
146.LRU缓存
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
*/

public class LRU {
    // 1. 定义双向链表节点类
    static class DLinkedNode {
        int key, value;         // 存储键和值（删除尾部节点时需要 key 来同步删除 Map 中的数据）
        DLinkedNode prev, next; // 指向前驱和后继的指针
        
        public DLinkedNode() {} // 无参构造用于创建伪节点
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 2. LRUCache 核心类
    static class LRUCache {
        // 哈希表：Key 为整数，Value 对应链表中的节点，实现快速定位
        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size, capacity; // size 记录当前数量，capacity 是最大容量
        private DLinkedNode head, tail; // 伪头节点和伪尾节点（哨兵节点）

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            // 初始化伪头和伪尾，避免复杂的空指针判断
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail; // 头指向尾
            tail.prev = head; // 尾指向头
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1; // 缓存中不存在该 key
            moveToHead(node); // 访问成功，说明该节点变“新鲜”了，移到链表头部
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode); // 放入哈希表
                addToHead(newNode);      // 放入链表头部
                size++;
                if (size > capacity) {
                    // 如果超过容量，执行淘汰策略：删除链表最末尾的真实节点
                    DLinkedNode tailNode = removeTail();
                    cache.remove(tailNode.key); // 同步从 Map 中移除
                    size--;
                }
            } else {
                // 如果 key 已存在，更新其 value 并将其移动到头部
                node.value = value;
                moveToHead(node);
            }
        }

        // --- 双向链表基础操作（指针操作的艺术） ---

        // 将节点插入到伪头节点之后（即逻辑上的第一个位置）
        private void addToHead(DLinkedNode node) {
            node.prev = head;          // 新节点的 prev 指向头
            node.next = head.next;     // 新节点的 next 指向原来的第一个节点
            head.next.prev = node;     // 原第一个节点的 prev 指向新节点
            head.next = node;          // 伪头的 next 指向新节点
        }

        // 将已有节点从当前链表位置中“抠出来”
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next; // 前一个节点跳过自己，指向后一个
            node.next.prev = node.prev; // 后一个节点跳过自己，指向前一个
        }

        // 组合操作：将一个节点先删再插，从而移动到头
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        // 删除靠近伪尾部的那个真实节点（即最久没用的节点）
        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev; // 找到倒数第一个真实节点
            removeNode(res);             // 将其删除
            return res;                  // 返回该节点，以便 Map 执行 remove
        }
    }

    // 3. ACM 驱动逻辑
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 第一行通常读入缓存容量
        if (!sc.hasNextInt()) return;
        int capacity = sc.nextInt();
        LRUCache lru = new LRUCache(capacity);
        
        // 模拟指令交互
        // 输入格式示例：1 1 10 (表示 put(1, 10))，2 1 (表示 get(1))
        while (sc.hasNextInt()) {
            int op = sc.nextInt(); // 读取操作码
            if (op == 1) {
                int key = sc.nextInt();
                int val = sc.nextInt();
                lru.put(key, val);
                System.out.println("PUT [" + key + ", " + val + "] OK");
            } else if (op == 2) {
                int key = sc.nextInt();
                int res = lru.get(key);
                System.out.println("GET [" + key + "] -> " + res);
            }
        }
        sc.close();
    }
}