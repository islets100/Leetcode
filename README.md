# LeetCode 题解仓库

> **持续更新中** — 题目与分类会随练习进度不断补充、调整

个人 LeetCode 刷题笔记与 Java 实现合集，侧重**思路说明**与**逐行注释**，并保留部分 **ACM 风格** 的 `main` 输入输出，便于本地运行验证。

## 目录结构

```
Leetcode/
├── dp/                 # 动态规划
├── binarySearch/       # 二分 / 快速选择
├── linkedList/         # 链表（含 LRU 双向链表）
├── *.java              # 其余专题（树、图、栈、回溯、排序等）
├── mergeSortSolution.java / sortArraySolution.java  # 排序专题
└── singletonHolderSolution.java                     # 设计模式：单例
```

## 专题归档

### `dp/` — 动态规划（15）

| 文件 | 题号 |
|------|------|
| `climbStairsSolution.java` | 70 |
| `uniquePathsSolution.java` | 62 |
| `minPathSumSolution.java` | 64 |
| `robSolution.java` | 198 |
| `maxSubArray.java` | 53 |
| `maxProductSolution.java` | 152 |
| `ProductExceptSelf.java` | 238 |
| `lengthOfLISSolution.java` | 300 |
| `coinChangeSolution.java` | 322 |
| `wordBreakSolution.java` | 139 |
| `longestPalindromeSolution.java` | 5 |
| `longestCommonSubsequenceSolution.java` | 1143 |
| `minDistanceSolution.java` | 72 |
| `canPartitionSolution.java` | 416 |
| `numSquaresSolution.java` | 279 |

### `binarySearch/` — 二分与快速选择（7）

| 文件 | 题号 |
|------|------|
| `searchSolution.java` | 33 |
| `searchRangeSolution.java` | 34 |
| `searchInsertSolution.java` | 35 |
| `TFsearchMatrixSolution.java` | 74 |
| `findMinSolution.java` | 153 |
| `searchInMatrix.java` | 240 |
| `findKthLargestSolution.java` | 215（快速选择） |

### `linkedList/` — 链表（15）

| 文件 | 题号 |
|------|------|
| `addTwoNumbers.java` | 2 |
| `removeNthFromEnd.java` | 19 |
| `mergeTwoLists.java` | 21 |
| `mergeKLists.java` | 23 |
| `swapListPairsNodes.java` | 24 |
| `reverseKGroup.java` | 25 |
| `reverseListIISolution.java` | 92 |
| `reverseList.java` | 206 |
| `isPalindrome.java` | 234 |
| `hasCycle.java` | 141 |
| `whereHasCycle.java` | 142 |
| `getIntersectionNode.java` | 160 |
| `copyRandomList.java` | 138 |
| `sortList.java` | 148 |
| `LRU.java` | 146 |

根目录下还有树、图、栈、回溯、双指针、滑动窗口等题目，尚未按专题拆分子目录。

## 代码说明

- 文件头部通常包含：**题号**、**题目描述**、**解题思路**。
- 核心逻辑配有**分步注释**；部分文件含 `main`，可从标准输入读数据并打印结果。
- Java 源文件与 `public class` 类名一致；子目录中的题解可直接在仓库根目录编译，例如：

```bash
javac dp/climbStairsSolution.java
java climbStairsSolution
```

```bash
javac linkedList/reverseList.java
java reverseList
```

## 本地忽略

`.gitignore` 已忽略：`*.jpg`、`*.class`、`笔记.md`。

---

如有新专题目录或题解迁移，会同步更新本说明。
