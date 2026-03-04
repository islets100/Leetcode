import java.util.*;
/*
56. 合并区间
以数组 intervals 表示若干个区间的集合，
其中单个区间为 intervals[i] = [starti, endi] 。
请你合并所有重叠的区间，并返回 一个不重叠的区间数组，
该数组需恰好覆盖输入中的所有区间 。
*/

class mergeIntervalsSolution {
    public int[][] merge(int[][] intervals) {
        // 1. 安全检查：如果输入数组为空，直接返回一个空的二维数组
        if (intervals.length == 0) {
            return new int[0][2];
        }

        // 2. 排序：这是合并区间的前提
        // 使用 Arrays.sort 对二维数组进行排序
        // new Comparator<int[]>() 定义了排序规则：
        // 比较两个区间 interval1 和 interval2 的左端点（索引为 0 的位置）
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0]; // 升序排列
            }
        });

        // 3. 准备一个 List 来存放合并后的结果
        // 因为我们不知道最终会剩下多少个区间，List（动态数组）比普通数组更方便添加元素
        List<int[]> merged = new ArrayList<int[]>();

        // 4. 遍历排序后的所有区间
        for (int i = 0; i < intervals.length; ++i) {
            // 取出当前区间的左端点 L 和右端点 R
            int L = intervals[i][0], R = intervals[i][1];
            
            // 情况 A：如果结果集为空，或者【当前区间的左端点】大于【结果集中最后一个区间的右端点】
            // 说明这两个区间完全不重叠（例如 [1,3] 和 [4,5]）
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                // 直接作为一个新的独立区间加入结果集
                merged.add(new int[]{L, R});
            } 
            // 情况 B：发生了重叠（当前区间的左端点 <= 结果集最后一个区间的右端点）
            else {
                // 合并区间：更新结果集中最后一个区间的右端点
                // 取【原终点】和【当前区间终点】的最大值（防止大区间吞掉小区间的情况）
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }

        // 5. 类型转换：将 List<int[]> 转换回 Java 要求的二维数组 int[][] 格式并返回
        return merged.toArray(new int[merged.size()][]);
    }
}

public class mergeIntervals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取區間個數
        int n = scanner.nextInt();
        int[][] intervals = new int[n][2];

        // 讀取每個區間的起點與終點
        for (int i = 0; i < n; i++) {
            intervals[i][0] = scanner.nextInt();
            intervals[i][1] = scanner.nextInt();
        }

        // 調用解法類進行合併
        mergeIntervalsSolution solution = new mergeIntervalsSolution();
        int[][] merged = solution.merge(intervals);

        // 使用 Arrays.deepToString 直接輸出二維陣列，例如 [[1, 6], [8, 10], [15, 18]]
        System.out.println(Arrays.deepToString(merged));

        scanner.close();
    }
}
