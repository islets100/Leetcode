import java.util.*;
/*
54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，
请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
*/
class spiralOrderSolution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>(); // 存放最终螺旋顺序的列表
        
        // 1. 安全检查：如果矩阵是空的，直接返回空的列表
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }

        int rows = matrix.length, columns = matrix[0].length; // 获取行数和列数
        // 2. 建立一个“地图”记录哪些格子走过
        // 默认全是 false，走过的地方标记为 true
        boolean[][] visited = new boolean[rows][columns];
        
        int total = rows * columns; // 总共要走的步数（格子总数）
        int row = 0, column = 0;    // 机器人的起始坐标 (0, 0)

        // 3. 定义四个方向：右(0,1)、下(1,0)、左(0,-1)、上(-1,0)
        // 比如 {0, 1} 表示行不变，列+1，也就是向右走
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0; // 初始方向是 directions[0]，即向右

        // 4. 开始循环，直到走完所有格子
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]); // 记录当前格子的数值
            visited[row][column] = true;    // 标记当前格子已经走过了

            // 5. 预判下一步：尝试按照当前方向算一下下一个坐标
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];

            // 6. 判断是否需要转弯
            // 条件：下一跳越界（上下左右出框）或者 已经走过（visited 为 true）
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                // 转弯逻辑：(0+1)%4=1, (1+1)%4=2, (2+1)%4=3, (3+1)%4=0
                // 这样就在 右->下->左->上 之间循环切换
                directionIndex = (directionIndex + 1) % 4;
            }

            // 7. 真正移动：根据（可能更新后的）方向，更新当前坐标
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }

        return order;
    }
}
public class spiralOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 讀取行數和列數
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];

        // 讀取矩陣元素
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 調用解法類，獲取螺旋順序
        spiralOrderSolution solution = new spiralOrderSolution();
        List<Integer> order = solution.spiralOrder(matrix);

        // ACM 輸出：用空格分隔輸出所有元素
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(order.get(i));
        }
        System.out.println();

        scanner.close();
    }
}
