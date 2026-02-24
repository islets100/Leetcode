class TrapRainWateSolution {
    public int trapDP(int[] height) {
        int n = height.length;
        if (n == 0) return 0; // 安全检查：如果数组为空，直接返回 0
        
        int ans = 0; // 用来累加所有位置的总雨水量

        // 1. 计算每个位置“左侧最高柱子”的数组
        // preMax[i] 表示第 i 个位置及其左边所有柱子中最厚实、最高的那根
        int[] preMax = new int [n];
        preMax[0] = height[0]; // 第 0 个位置左边最高的就是它自己
        for(int i = 1; i < n; i++){
            // 当前最高 = 上一个最高值 和 当前高度 里的较大者
            preMax[i] = Math.max(preMax[i-1], height[i]);
        }

        // 2. 计算每个位置“右侧最高柱子”的数组
        // sufMax[i] 表示第 i 个位置及其右边所有柱子中最高的那根
        int[] sufMax = new int[n];
        sufMax[n-1] = height[n-1]; // 最后一个位置右边最高的就是它自己
        for(int j = n-2; j >= 0; j--){
            // 从右往左遍历：当前最高 = 右边那个最高值 和 当前高度 里的较大者
            sufMax[j] = Math.max(sufMax[j+1], height[j]);
        }

        // 3. 计算每个位置的存水量并累加
        for(int i = 0; i < n; i++){
            // 核心公式：
            // 该位置水面高度 = min(左边最高, 右边最高)
            // 该位置存水量 = 水面高度 - 该位置柱子本身的高度
            ans += Math.min(preMax[i], sufMax[i]) - height[i];
        }

        // 4. 返回总和
        return ans;
    }
    //-----------双指针法---------------------
    public int trapTP(int[] height) {
            int ans = 0;        // 累加总雨水量
        int preMax = 0;     // 记录左指针走过的路径中，最高的那根柱子
        int sufMax = 0;     // 记录右指针走过的路径中，最高的那根柱子
        int left = 0;       // 左指针，从头开始
        int right = height.length - 1; // 右指针，从末尾开始

        // 1. 当左右指针没有相遇时，继续循环
        while(left < right){
            
            // 2. 更新当前的左侧最大值和右侧最大值
            // preMax 始终是 height[0...left] 中的最大值
            preMax = Math.max(preMax, height[left]);
            // sufMax 始终是 height[right...n-1] 中的最大值
            sufMax = Math.max(sufMax, height[right]);

            // 3. 核心逻辑：谁矮就处理谁
            // 如果左边的最高墙比右边的最高墙矮
            if (preMax < sufMax){
                // 对于 left 指向的这个位置，它能接的水就是 (当前的左最高墙 - 它自己的高度)
                // 为什么不关心右边？因为我们已知右边有一堵 sufMax 墙，而且 sufMax > preMax
                ans += preMax - height[left];
                
                // 处理完左边，左指针右移
                left++;
            } else {
                // 如果右边的最高墙矮于或等于左边的最高墙
                // 对于 right 指向的这个位置，它能接的水就是 (当前的右最高墙 - 它自己的高度)
                ans += sufMax - height[right];
                
                // 处理完右边，右指针左移
                right--;
            }
        }
        
        // 4. 返回总水量
        return ans;
    
        }
    
}
public class TrapRainWater {
    public static void main(String[] args) {
        TrapRainWateSolution solution = new TrapRainWateSolution();

        int[] h1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("測試1：{0,1,0,2,1,0,1,3,2,1,2,1} -> " + solution.trapDP(h1));

        int[] h2 = {4,2,0,3,2,5};
        System.out.println("測試2：{4,2,0,3,2,5} -> " + solution.trapTP(h2));

        int[] h3 = {1,2,3,4,5};
        System.out.println("測試3：{1,2,3,4,5} -> " + solution.trapDP(h3));

        int[] h4 = {5,4,3,2,1};
        System.out.println("測試4：{5,4,3,2,1} -> " + solution.trapTP(h4));
    }
}
