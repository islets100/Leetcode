import java.util.*;
import java.io.*;

/**
 * HW.3 手机号清单交集统计
 * 某国手机号长度为8位十进制数字，范围为00000000到99999999。
 * 某商城有海量注册用户，已按商品购买情况分别记录到两个商品清单中。
 * 现在给出两个商品清单，请统计同时出现在两个清单中的用户个数。
 *
 * 输入：
 * 输入第一行为A商品清单内用户条数N，接下来N行每行一个手机号。
 * 紧接着一行表示B商品清单内用户条数M，接下来M行每行一个手机号。
 * 输入中的手机号为数字格式，前导0会省略，且同一用户可能重复出现多次。
 *
 * 输出：
 * 输出一行一个数字，表示同时出现在两个商品清单中的用户个数，
 * 同一个用户仅统计一次。
 */
public class productCompare {
    public static void main(String[] args) throws Exception {
        // 创建缓冲读取器，用于从标准输入读取数据
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一个整数n，表示第一组数据的数量
        int n = Integer.parseInt(r.readLine());
        // 创建一个BitSet，大小为100000000，用于存储第一组数据
        BitSet b = new BitSet(100000000);
        // 读取第一组n个数据，并将它们存储到BitSet b中
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(r.readLine());
            b.set(x); // 将位置x设置为true
        }
        // 读取第二个整数m，表示第二组数据的数量
        int m = Integer.parseInt(r.readLine());
        // 创建第二个BitSet，用于存储第二组数据
        BitSet c = new BitSet(100000000);
        // 初始化计数器d，用于记录共同元素的数量
        int d = 0;
        // 读取第二组m个数据
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(r.readLine());
            // 如果x不在第二组BitSet c中
            if (!c.get(x)) {
                // 检查x是否在第一组BitSet b中
                if (b.get(x))
                    d++; // 如果在，增加共同元素计数器
                // 将x添加到第二组BitSet c中
                c.set(x);
            }
        }
        // 输出共同元素的数量
        System.out.println(d);
    }
}