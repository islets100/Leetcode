import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagramsSolution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 1. 创建一个大柜子 (HashMap)
        // Key (键): 排序后的字符串 (如 "aet")，充当“组名”
        // Value (值): 一个列表 (List)，用来存放所有属于这个组的原始单词 (如 ["eat", "tea", "ate"])
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        // 2. 遍历数组里的每一个单词
        for (String str : strs) {
            
            // --- 这一步是为了给单词找“组织” (生成 Key) ---
            
            // a. 把字符串变成字符数组。例如 "eat" -> ['e', 'a', 't']
            char[] array = str.toCharArray();
            
            // b. 给字符数组排队（按 A-Z）。例如 ['e', 'a', 't'] -> ['a', 'e', 't']
            Arrays.sort(array);
            
            // c. 把排好序的数组再变回字符串，作为“组名”。例如 "aet"
            String key = new String(array);

            // --- 这一步是把单词投进柜子 ---

            // d. 尝试从柜子里拿这个组的名单
            // getOrDefault 的意思是：如果有 "aet" 这个组，就把它拿出来；
            // 如果柜子里还没有这个组，就当场新建一个空名单 (new ArrayList)。
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            
            // e. 把当前还没排序的原始单词 (str) 加进名单里
            list.add(str);
            
            // f. 把更新后的名单再塞回柜子里对应的位置
            map.put(key, list);
        }

        // 3. 最后，我们只需要柜子里的“名单内容”，不需要外面的“组名标签”
        // map.values() 会把所有的 List 拿出来，打包成一个大列表返回
        return new ArrayList<List<String>>(map.values());
    }
}

public class grouAnagrams {
    public static void main(String[] args) {
        GroupAnagramsSolution solution = new GroupAnagramsSolution();

        // 測試用例：LeetCode 標準示例
        String[] sr = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = solution.groupAnagrams(sr);

        // 直接印出結果（List 本身會有較友善的 toString 形式）
        System.out.println(result);
    }
}
