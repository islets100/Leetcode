import java.util.*;

public class mergesort {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right)
            return; // 递归终点：只剩一个元素

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid); // 分左边
        mergeSort(nums, mid + 1, right); // 分右边

        merge(nums, left, mid, right); // 合并结果
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // 1. 准备一个临时数组存放合并结果
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // 2. 比较左右两个子数组，谁小谁进 temp
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 3. 把剩下的（如果有的话）直接搬运过去
        while (i <= mid)
            temp[k++] = nums[i++];
        while (j <= right)
            temp[k++] = nums[j++];

        // 4. 把 temp 里的有序数据拷贝回原数组
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    public static void main(String[] args) {
        mergesort sol = new mergesort();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int[] ans = sol.sortArray(nums);
            for (int i = 0; i < n; i++) {
                System.out.print(ans[i] + " ");
            }

        }
        sc.close();
    }
}
