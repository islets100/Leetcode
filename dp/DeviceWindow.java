import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * HW.2 医疗设备稳定监控
 * 有n台医疗设备，编号为1到n，每台设备都有一个当前运行状态值。
 * 为了监控设备运行是否稳定，需要找出一段连续的设备序列，
 * 使得这段序列中任意两台设备的运行状态值之差的绝对值都不超过阈值d。
 * 如果有多个最长序列，输出起始编号最小的那一个。
 *
 * 输入：
 * 第一行输入两个整数n, d，分别表示设备数量和允许的最大状态差值。
 * 第二行输入n个整数，表示n台设备的运行状态值。
 *
 * 输出：
 * 输出两个整数L R，表示最长连续设备序列的起始和结束编号。
 * 如果有多个长度相同的序列，输出起始编号最小的序列。
 * 如果只有1个设备，或者不存在满足条件的序列，则输出1 1。
 */

public class DeviceWindow {
    public static void main(String[] args) throws Exception {
        // 使用BufferedReader读取标准输入，提高读取效率
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行输入，分割成字符串数组
        String[] p = b.readLine().split(" ");
        // 解析第一个整数为数组长度n
        int n = Integer.parseInt(p[0]);
        // 解析第二个整数为差值阈值d
        int d = Integer.parseInt(p[1]);
        // 读取第二行输入，分割成字符串数组
        p = b.readLine().split(" ");
        // 创建整型数组a2存储输入数据
        int[] a2 = new int[n];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            a2[i] = Integer.parseInt(p[i]);
        }
        // 使用TreeMap存储窗口内的元素及其出现次数，自动排序
        TreeMap<Integer, Integer> t = new TreeMap<>();
        int l = 0;// 窗口左边界指针
        int m = 0;// 全局长度
        int s = 0;// 全局起始索引
        // 遍历数组，使用滑动窗口技术
        for (int i = 0; i < n; i++) {
            int v = a2[i];
            // 将当前元素加入TreeMap，更新计数
            t.put(v, t.getOrDefault(v, 0) + 1);
            // 当窗口内最大值与最小值差大于d时，移动左边界
            while (t.lastKey() - t.firstKey() > d) {
                int u = a2[l];
                int c = t.get(u);
                // 移除或减少左边界元素的计数
                if (c == 1) {
                    t.remove(u);
                } else {
                    t.put(u, c - 1);
                }
                l++;// 左边界右移
            }
            // 计算当前窗口长度
            int k = i - l + 1;
            // 更新最大窗口长度和起始位置
            if (k > m) {
                m = k;
                s = l;
            }
        }
        // 输出结果，起始位置和结束位置（+1转换为1-based索引）
        System.out.println((s + 1) + " " + (s + m));
    }
}
