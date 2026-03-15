import java.util.Scanner;

/**208.实现前缀树（trie)
 * Trie（发音类似 "try"）或者说 
 * 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
请你实现 Trie 类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，
返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串
 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class insertSolution {

    /**
     * Trie 类既代表整个前缀树，也代表树中的每一个节点
     */
    static class Trie {
        // 子节点数组，长度为 26，对应 a-z 26 个小写字母
        private Trie[] children;
        // 标记位：表示从根到当前节点的路径是否构成了一个完整的单词
        private boolean isEnd;

        /**
         * 初始化节点
         */
        public Trie() {
            // 初始化 26 个槽位，初始值均为 null
            children = new Trie[26];
            // 默认不是单词结尾
            isEnd = false;
        }

        /**
         * 插入一个单词到前缀树中
         */
        public void insert(String word) {
            // 从当前节点（通常是根节点）开始往下走
            Trie node = this;
            // 遍历单词中的每一个字符
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                // 计算字符对应的数组下标，'a' -> 0, 'b' -> 1 ...
                int index = ch - 'a';
                // 如果对应字母的路径还没建立（null），就新建一个 Trie 节点
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                // 移动指针到子节点，继续处理下一个字符
                node = node.children[index];
            }
            // 单词的所有字符处理完毕，将最后一个节点标记为单词结尾
            node.isEnd = true;
        }

        /**
         * 在前缀树中查找是否存在精确匹配的单词
         */
        public boolean search(String word) {
            // 调用辅助方法查找前缀对应的最后一个节点
            Trie node = searchPrefix(word);
            // 单词存在的条件：1. 路径完整存在；2. 最后一个节点是单词结尾(isEnd 为 true)
            return node != null && node.isEnd;
        }

        /**
         * 检查前缀树中是否有以指定 prefix 开头的单词
         */
        public boolean startsWith(String prefix) {
            // 只要前缀的路径完整存在，就返回 true，不需要管是不是单词结尾
            return searchPrefix(prefix) != null;
        }

        /**
         * 辅助方法：在前缀树中沿着 prefix 的字符路径走到底
         * @return 如果路径存在，返回最后一个节点；否则返回 null
         */
        public Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                // 如果中间某个字母的路径不存在，直接返回 null
                if (node.children[index] == null) {
                    return null;
                }
                // 沿着路径继续往下走
                node = node.children[index];
            }
            // 走完了所有字符，返回停留的那个节点
            return node;
        }
    }

    /**
     * 主函数：处理 ACM 模式的输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 创建前缀树的根节点
        Trie trie = new Trie();

        // 模拟指令操作：输入包含操作指令和参数
        // 例如：insert apple, search apple, startsWith app
        while (sc.hasNext()) {
            String op = sc.next(); // 读取操作命令
            
            switch (op) {
                case "insert":
                    String wordToInsert = sc.next();
                    trie.insert(wordToInsert);
                    System.out.println("Insert: " + wordToInsert + " Done");
                    break;
                case "search":
                    String wordToSearch = sc.next();
                    System.out.println("Search '" + wordToSearch + "': " + trie.search(wordToSearch));
                    break;
                case "startsWith":
                    String prefix = sc.next();
                    System.out.println("StartsWith '" + prefix + "': " + trie.startsWith(prefix));
                    break;
                default:
                    System.out.println("Unknown Operation");
            }
        }
        sc.close();
    }
}