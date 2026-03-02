import java.util.*;

/* 
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t(会包含重复字符) 。
返回 s 中涵盖 t 所有字符的最小子串。
如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
*/

class minWindowSolution {
    public String minWindow(String S, String t) {
        // --- 1. 准备工作 ---
        // cntT: 存储目标字符串 t 的字符频率（我们的目标清单）
        // cntS: 存储S当前窗口 s 子串的字符频率（我们已有的货品）
        Map<Character, Integer> cntT = new HashMap<>();
        Map<Character, Integer> cntS = new HashMap<>();

        // 统计 t 中每个字符出现的次数
        for (char c : t.toCharArray()) {
            cntT.put(c, cntT.getOrDefault(c, 0) + 1);
        }

        char[] s = S.toCharArray(); // 转成数组，访问字符更快
        int m = s.length;
        
        // 用于记录最终答案的左、右坐标
        // 初始设 ansLeft = -1 表示还没找到任何满足条件的子串
        int ansLeft = -1;
        int ansRight = m;

        // --- 2. 滑动窗口核心逻辑 ---
        int left = 0; // 窗口左边界
        for (int right = 0; right < m; right++) { // right 是窗口右边界，不断向右扩展
            char curRight = s[right];
            // 把右指针指向的字符存入窗口记账本 cntS
            cntS.put(curRight, cntS.getOrDefault(curRight, 0) + 1);

            // 【严谨逻辑】：如果当前窗口已经涵盖了 t 中的所有字母
            // isCovered 是下面定义的检查函数
            while (isCovered(cntS, cntT)) { 
                
                // 找到一个更短的子串时，更新我们记录的坐标
                // 注意：ansRight - ansLeft 初始是 m - (-1) = m + 1，第一次找到必更新
                if (right - left < ansRight - ansLeft) {
                    ansLeft = left;
                    ansRight = right;
                }

                // 尝试收缩窗口：把左边界字符移出
                char curLeft = s[left];
                cntS.put(curLeft, cntS.get(curLeft) - 1);
                
                left++; // 左指针向右移动
            }
        }

        // --- 3. 返回结果 ---
        // 如果 ansLeft 还是 -1，说明没找到，返回空串 ""
        // substring 是左闭右开，所以结束位置要填 ansRight + 1
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }

    // --- 4. 严谨的检查函数 ---
    // 检查窗口 cntS 是否完全覆盖了清单 cntT
    private boolean isCovered(Map<Character, Integer> cntS, Map<Character, Integer> cntT) {
        // 遍历清单里的每一个字符及其要求的数量
        for (Map.Entry<Character, Integer> entry : cntT.entrySet()) {
            Character key = entry.getKey();
            Integer targetCount = entry.getValue();
            
            // 如果当前窗口里这个字符的数量，少于清单要求的数量，说明还没涵盖住
            if (cntS.getOrDefault(key, 0) < targetCount) {
                return false;
            }
        }
        return true; // 所有字符都达标了
    }
}

public class minWindow {
    public static void main(String[] args) {
        minWindowSolution solution = new minWindowSolution();
        String S = "ADOBECODEBANC";
        String t = "ABC";
        String ans = solution.minWindow(S, t);
        System.out.println(ans);
        String S2 = "ABCDB";
        String t2 = "BD";
        String ans2 = solution.minWindow(S2, t2);
        System.out.println(ans2);
        String S3 = "a";
        String t3 = "aa";
        String ans3 = solution.minWindow(S3, t3);
        System.out.println(ans3);
    }
}