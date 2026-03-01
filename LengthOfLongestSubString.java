/*
3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
*/

class lengthOfLongestSubstringSolution {
    public int lengthOfLongestSubstring(String S) {
        int ans = 0;             // 记录最长子串的长度（最终答案）
        int[] cnt = new int[128]; // 【核心】计数数组，用来统计窗口内每个字符出现的次数
        int left = 0;            // 滑动窗口的左边界指针
        int n = S.length();      // 字符串的总长度
        char[] s = S.toCharArray(); // 将字符串转为字符数组，提高访问效率（charAt 略慢）

        // right 是窗口的右边界指针，不断向右扩张
        for(int right = 0; right < n; right++){
            char c = s[right];   // 获取当前右指针指向的字符
            cnt[c]++;            // 将该字符在计数数组中的次数加 1

            // 如果当前字符 c 的次数大于 1，说明窗口内出现了重复字符
            // 这时需要不断收缩左边界 left，直到把重复的那个字符剔除出去
            while (cnt[c] > 1){
                cnt[s[left]]--;  // 将左边界指向的字符次数减 1
                left++;          // 左边界向右移动
            }

            // 此时窗口 [left, right] 内保证没有重复字符
            // 更新答案：计算当前窗口长度，取历史最大值
            ans = Math.max(ans, right - left + 1);
        }
        return ans;              // 返回全局最大长度
    }
}


public class LengthOfLongestSubString {
    public static void main(String[] args) {
        lengthOfLongestSubstringSolution solution = new lengthOfLongestSubstringSolution();
        String S1 = "abcabcbb";
        int ans1 = solution.lengthOfLongestSubstring(S1);
        System.out.println(ans1);
    }
}
/*
2. 为什么 128 个空间就够了？
这是一个非常经典的空间换时间技巧。
ASCII 码范围：在计算机中，标准的 ASCII 码表
 一共定义了 128 个字符
 （包含数字 0-9、大写字母 A-Z、小写字母 a-z、标点符号以及控制字符）。
 字符即索引：在 Java 中，char 类型在参与运算时会自动转换
 为它对应的 ASCII 整数值。例如，字符 'a' 的值是 97，'A' 是 65，
 空格 ' ' 是 32。直接映射：如果我们声明 int[] cnt = new int[128]，
 那么：cnt[97] 实际上就是用来存储字符 'a' 出现的次数。
 cnt[32] 就是用来存储空格出现的次数。
 由于题目通常考察的是标准 ASCII 字符集内的字符串，
 128 个坑位足以覆盖所有可能的单字节字符。
 这样我们就不需要使用昂贵的 HashMap，
 直接通过数组下标访问，速度极快（$O(1)$ 时间复杂度）。
 */