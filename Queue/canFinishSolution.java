import java.util.*;

/**207.课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class canFinishSolution {

    /**
     * 核心算法：判断是否能完成所有课程（检测有向图中是否存在环）
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 入度数组：记录每门课程还需要修几门先修课
        int[] inDegree = new int[numCourses];
        
        // 2. 邻接表：记录每一门课修完后，哪些课的先修要求会变少
        List<List<Integer>> adjList = new ArrayList<>();
        // 初始化列表，防止 null 指针
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // 3. 构建图的结构：根据先修关系填充数据
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];    // 当前要修的课
            int preCourse = prerequisite[1]; // 先修课
            // 当前课程需要多一门先修课，所以入度自增
            inDegree[course]++;
            // 修完先修课后，能解锁当前课程，记录这种指向关系
            adjList.get(preCourse).add(course);
        }

        // 4. 队列：存放所有当前可以立即修读的课程（即入度为 0）
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 如果某门课入度为 0，说明它不需要任何先修课，入队
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 5. 计数器：统计我们最终一共成功修完了多少门课
        int count = 0;
        
        // 6. 开始拓扑排序（解套过程）
        while (!queue.isEmpty()) {
            // 从队列中弹出一门已经准备好的课
            int selectedCourse = queue.poll();
            // 记数加 1
            count++;
            
            // 获取这门课的所有后续课程
            List<Integer> nextCourses = adjList.get(selectedCourse);
            for (int nextCourse : nextCourses) {
                // 因为先修课选完了，所有后续课程的入度都要减 1
                inDegree[nextCourse]--;
                // 如果减到 0，说明它的所有先修课都搞定了，可以进队列准备修读了
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 7. 判断结果：如果我们修完的课程总数等于要求的总课程数，说明没有环
        return count == numCourses;
    }

    /**
     * 主函数：处理 ACM 输入输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        canFinishSolution solution = new canFinishSolution();

        // 循环读取输入数据
        while (sc.hasNextInt()) {
            // 第一行：总课程数
            int numCourses = sc.nextInt();
            // 第二行：先修关系的组数
            int pCount = sc.nextInt();
            
            int[][] prerequisites = new int[pCount][2];
            for (int i = 0; i < pCount; i++) {
                // 读取每组关系：[目标课程, 先修课程]
                prerequisites[i][0] = sc.nextInt();
                prerequisites[i][1] = sc.nextInt();
            }

            // 输出计算结果
            System.out.println(solution.canFinish(numCourses, prerequisites));
        }
        sc.close();
    }
}