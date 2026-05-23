import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * HW.1 虚拟机调度问题
 * 虚拟机环境里有4种不同类型的任务，每种任务消耗的RAM各不相同，消耗的资源分别为A、B、C、D。
 * 假定4种任务待调度的数量无限多，小明手里有N台机器，这N台机器的RAM配置各不相同。
 * 为了在保证机器的利用率的前提下，减少CPU资源的争抢，
 * 小明希望这N台机器，机器的RAM被任务占满的同时，任务数量越少越好。
 * 输入：
 * 输入第一行为四个整数，表示四种不同类型任务的资源消耗，A B C D ，保证不重复，一定保证有消耗为1的任务，
 * 使得机器一定可以被填满。
 * 输入的第二行为机器数量N，(1 <= N <= 10^3)
 * 后续N行，每一行给出每台机器的RAM大小X，取值范围为 1 <= X <= 11000。
 * 输出:
 * 输出为N行，每行表示机器的任务组成情况，
 * 以升序排列，加号分割。若存在多种最少的任务分配组合，
 * 选择升序排列后，按顺序比较数字排序最小的组合输出。
 * 如 1+3 和 2+2 两个升序序列，按顺序比较第一个数字 1 < 2，
 * 则应该输出 1+3 这个组合。
 */
/**
 * VMtask类，实现内存块分配算法
 * 使用动态规划方法解决内存块分配问题
 */
public class VMtask {
    public static void main(String[] args) throws Exception {
        // 使用BufferedReader读取标准输入，提高读取效率
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行输入，按空格分割成字符串数组
        String[] line = b.readLine().split(" ");
        // 创建一个长度为4的整型数组，用于存储内存块大小
        int[] a = new int[4];
        // 将输入的字符串转换为整型并存储到数组a中
        for (int i = 0; i < 4; i++) {
            a[i] = Integer.parseInt(line[i]);
        }
        // 对内存块大小进行排序，便于后续处理
        Arrays.sort(a);
        // 读取需要分配的内存请求数量
        int n = Integer.parseInt(b.readLine());
        // 定义最大内存容量
        int RAM = 11000;
        // dp数组，用于存储每个内存值所需的最小块数
        int[] dp = new int[RAM + 1];
        // pre数组，用于记录每个内存值最后使用的是哪个内存块
        int[] pre = new int[RAM + 1];
        // 初始化dp数组，设置一个较大的值作为初始状态
        Arrays.fill(dp, 11000);
        // dp[0] = 0，表示0内存不需要任何块
        dp[0] = 0;
        // 动态规划求解每个内存值的最优解
        for (int x = 1; x <= RAM; x++) {
            // 遍历所有可能的内存块
            for (int t : a) {
                // 如果当前内存值x大于等于内存块t，并且使用该块能得到更优解
                if (x >= t && dp[x - t] + 1 <= dp[x]) {
                    // 更新dp[x]的值
                    dp[x] = dp[x - t] + 1;
                    // 记录使用的内存块
                    pre[x] = t;
                }
            }
        }
        // 处理每个内存请求
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(b.readLine());
            // 使用List存储分配结果
            List<Integer> res = new ArrayList<>();
            int cur = x;
            // 回溯查找使用的内存块
            while (cur > 0) {
                res.add(pre[cur]);
                cur -= pre[cur];
            }
            // 对结果进行排序
            Collections.sort(res);
            // 使用StringBuilder构建输出字符串
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < res.size(); j++) {
                if (j > 0) {
                    sb.append("+");
                }
                sb.append(res.get(j));
            }
            // 输出结果
            System.out.println(sb);
        }
    }
}