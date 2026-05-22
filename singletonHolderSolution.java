/**
 * 设计模式：单例模式 (Singleton Pattern)
 *
 * 题目/场景：保证一个类在 JVM 中只有一个实例，并提供全局访问点。
 * 常见用途：配置管理器、线程池、数据库连接池等需要全局唯一对象的场景。
 *
 * 解题思路：静态内部类（Holder 懒加载）
 * 1. 构造函数私有化，禁止外部 new。
 * 2. 把实例放在静态内部类 Holder 中；类加载 Holder 时才创建 INSTANCE。
 * 3. 由 JVM 类加载机制保证线程安全，且实现懒加载，无需 synchronized。
 * 4. 相比饿汉式：更省资源；相比双重检查锁：写法更简单、无复杂同步逻辑。
 */
public class singletonHolderSolution {

    // 1. 私有构造：外部无法直接 new Singleton()
    private singletonHolderSolution() {}

    // 2. 静态内部类：只有第一次调用 getInstance() 时才会加载 Holder
    private static class Holder {
        // 3. 类加载时由 JVM 保证只初始化一次，天然线程安全
        private static final singletonHolderSolution INSTANCE = new singletonHolderSolution();
    }

    /**
     * 4. 对外唯一入口：返回 Holder 中持有的单例
     */
    public static singletonHolderSolution getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 演示：两次 getInstance 应得到同一对象
     */
    public static void main(String[] args) {
        singletonHolderSolution a = singletonHolderSolution.getInstance();
        singletonHolderSolution b = singletonHolderSolution.getInstance();

        System.out.println("a == b : " + (a == b));
        System.out.println("hashCode a : " + System.identityHashCode(a));
        System.out.println("hashCode b : " + System.identityHashCode(b));
    }
}
