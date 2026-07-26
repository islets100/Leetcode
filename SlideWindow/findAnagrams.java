import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词
的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
*/

class findAnagramsSolution {
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        // 情况 1：如果 s 比 p 还短，绝对不可能包含 p 的异位词
        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        // 使用 26 个长度的数组记录字母出现的频率 (a -> index 0, b -> index 1...)
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        // 【初始化阶段】
        // 统计字符串 p 的所有字母频率，同时统计 s 中前 pLen 个字母的频率（初始化窗口）
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a']; // s 的第一个窗口
            ++pCount[p.charAt(i) - 'a']; // 目标 p 的频率
        }

        // 检查第一个窗口是否匹配（索引 0）
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        // 【滑动阶段】
        // 窗口的大小固定为 pLen。每次向右移动一位：
        // 1. 扔掉窗口左边的一个字符 (i)
        // 2. 加入窗口右边的一个新字符 (i + pLen)
        //先移动再判断，所以最后一次是i指向窗口前一个字符，循环内移动一次正好最后一个窗口
        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];          // 移除旧的左边界字符
            ++sCount[s.charAt(i + pLen) - 'a'];   // 加入新的右边界字符

            // 检查滑动后的新窗口是否与 p 匹配
            // 注意：此时新窗口的起始索引是 i + 1
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
 

public class findAnagrams {
    public static void main(String[] args) {
        findAnagramsSolution solution = new findAnagramsSolution();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = solution.findAnagrams(s, p);
        System.out.println(result);
    }
}