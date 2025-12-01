import java.io.*;
import java.util.*;

/**
 * 游乐设施类（实现 RideInterface），管理队列、历史记录、运行周期与IO操作（对应 Part1-7 要求）
 */
public class Ride implements RideInterface {
    // Part1 要求：至少 3 个实例变量，其中 1 个为 Employee 类型
    private String rideId;          // 游乐设施ID
    private String rideName;        // 游乐设施名称
    private Employee operator;      // 操作员（Employee类型，Part1 要求）
    private int maxRider;           // 每周期最大载客量（Part5 要求）
    private int numOfCycles;        // 已运行周期数（Part5 要求，默认0）
    
    // Part3 要求：用 Queue 存储等待访客（LinkedList 实现 Queue 接口）
    private Queue<Visitor> waitingQueue;
    // Part4A 要求：用 LinkedList 存储乘坐历史
    private LinkedList<Visitor> rideHistory;

    // Part1 要求：默认构造函数
    public Ride() {
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;  // 默认周期数为0（Part5 要求）
    }

    // Part1 要求：带参构造函数（初始化所有实例变量）
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // 所有实例变量的 Getter 和 Setter
    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) {
        // 验证：每周期至少1人（Part5 要求）
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }
    public int getNumOfCycles() { return numOfCycles; }


    // ------------------------------ Part3：队列管理方法 ------------------------------
    /**
     * 向队列添加访客（Part3 要求）
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("【失败】无法添加空访客到「" + rideName + "」队列");
            return;
        }
        waitingQueue.offer(visitor);  // Queue 的添加方法（失败返回false）
        System.out.println("【成功】访客「" + visitor.getName() + "」已加入「" + rideName + "」队列");
    }

    /**
     * 从队列移除访客（Part3 要求）
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【失败】「" + rideName + "」队列为空，无法移除访客");
            return;
        }
        Visitor removed = waitingQueue.poll();  // Queue 的移除方法（空队列返回null）
        System.out.println("【成功】访客「" + removed.getName() + "」已从「" + rideName + "」队列移除");
    }

    /**
     * 打印队列中所有访客（Part3 要求，按加入顺序）
     */
    @Override
    public void printQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("「" + rideName + "」队列当前无访客");
            return;
        }
        System.out.println("\n「" + rideName + "」队列访客列表（共" + waitingQueue.size() + "人）：");
        int index = 1;
        for (Visitor visitor : waitingQueue) {  // Queue 遍历按加入顺序（FIFO）
            System.out.println(index + ". " + visitor);
            index++;
        }
    }


    // ------------------------------ Part4A：乘坐历史管理方法 ------------------------------
    /**
     * 向历史记录添加访客（Part4A 要求）
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("【失败】无法添加空访客到「" + rideName + "」历史记录");
            return;
        }
        rideHistory.add(visitor);
        System.out.println("【成功】访客「" + visitor.getName() + "」已加入「" + rideName + "」乘坐历史");
    }

    /**
     * 检查访客是否在历史记录中（Part4A 要求）
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            return false;
        }
        // 按访客唯一ID判断（避免对象地址比较）
        for (Visitor historyVisitor : rideHistory) {
            if (historyVisitor.getVisitorId().equals(visitor.getVisitorId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回历史记录中的访客数量（Part4A 要求）
     */
    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    /**
     * 打印历史记录（Part4A 要求，必须用 Iterator）
     */
    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("「" + rideName + "」暂无乘坐历史");
            return;
        }
        System.out.println("\n「" + rideName + "」乘坐历史（共" + rideHistory.size() + "人）：");
        Iterator<Visitor> iterator = rideHistory.iterator();  // 必须用 Iterator（Part4A 要求）
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }


    // ------------------------------ Part4B：历史记录排序（需配合 VisitorComparator） ------------------------------
    /**
     * 对乘坐历史按自定义规则排序（Part4B 要求，使用 Comparator）
     */
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("【提示】「" + rideName + "」历史记录为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, comparator);  // 使用 Collections.sort + Comparator
        System.out.println("【成功】「" + rideName + "」乘坐历史已完成排序");
    }


    // ------------------------------ Part5：运行游乐设施周期 ------------------------------
    /**
     * 运行一个游乐设施周期（Part5 要求）
     */
    @Override
    public void runOneCycle() {
        // 1. 检查是否有操作员（Part5 要求）
        if (operator == null) {
            System.out.println("【失败】「" + rideName + "」未分配操作员，无法运行");
            return;
        }
        // 2. 检查队列是否为空（Part5 要求）
        if (waitingQueue.isEmpty()) {
            System.out.println("【失败】「" + rideName + "」队列为空，无法运行周期");
            return;
        }

        // 3. 计算本周期可载客数（取 maxRider 和队列长度的最小值）
        int ridersThisCycle = Math.min(maxRider, waitingQueue.size());
        System.out.println("\n【开始运行】「" + rideName + "」周期" + (numOfCycles + 1) + "，本周期载客" + ridersThisCycle + "人");

        // 4. 移除队列访客并加入历史记录
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = waitingQueue.poll();
            addVisitorToHistory(rider);  // 复用 Part4A 的添加历史方法
        }

        // 5. 周期数+1（Part5 要求）
        numOfCycles++;
        System.out.println("【运行结束】「" + rideName + "」周期" + numOfCycles + "完成，累计运行" + numOfCycles + "个周期");
    }


    // ------------------------------ Part6：导出历史记录到CSV文件 ------------------------------
    /**
     * 导出乘坐历史到CSV文件（Part6 要求）
     * @param filePath 文件路径（如 "rideHistory.csv"）
     */
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("【失败】「" + rideName + "」历史记录为空，无需导出");
            return;
        }

        // 使用 try-with-resources 自动关闭流（避免资源泄漏）
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 1. 写入CSV表头（便于理解字段）
            writer.write("personId,name,age,visitorId,membershipType");
            writer.newLine();  // 换行

            // 2. 写入每条访客数据（逗号分隔，Part6 要求）
            for (Visitor visitor : rideHistory) {
                String line = String.join(",",
                        visitor.getId(),                // Person 类属性
                        visitor.getName(),              // Person 类属性
                        String.valueOf(visitor.getAge()),// Person 类属性
                        visitor.getVisitorId(),         // Visitor 类属性
                        visitor.getMembershipType()     // Visitor 类属性
                );
                writer.write(line);
                writer.newLine();
            }

            System.out.println("【成功】「" + rideName + "」历史记录已导出到：" + new File(filePath).getAbsolutePath());
        } catch (IOException e) {
            // Part6 要求：异常处理与错误提示
            System.out.println("【导出失败】IO错误：" + e.getMessage());
        }
    }


    // ------------------------------ Part7：从CSV文件导入历史记录 ------------------------------
    /**
     * 从CSV文件导入乘坐历史（Part7 要求）
     * @param filePath 文件路径（如 "rideHistory.csv"）
     */
    public void importRideHistory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("【失败】文件不存在：" + file.getAbsolutePath());
            return;
        }

        // 使用 try-with-resources 自动关闭流
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();  // 跳过表头（第一行）

            // 1. 读取每一行数据并解析
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;  // 跳过空行

                // 拆分CSV字段（逗号分隔）
                String[] fields = line.split(",");
                // 验证字段数量（Person 3个属性 + Visitor 2个属性 = 5个字段）
                if (fields.length != 5) {
                    System.out.println("【警告】跳过格式错误的行：" + line);
                    continue;
                }

                // 2. 解析字段并创建 Visitor 对象
                String personId = fields[0];
                String name = fields[1];
                int age = Integer.parseInt(fields[2]);  // 可能抛异常，需捕获
                String visitorId = fields[3];
                String membershipType = fields[4];

                Visitor importedVisitor = new Visitor(personId, name, age, visitorId, membershipType);
                rideHistory.add(importedVisitor);  // 加入历史记录
            }

            System.out.println("【成功】从文件导入「" + rideName + "」历史记录，共" + rideHistory.size() + "人");
        } catch (NumberFormatException e) {
            System.out.println("【导入失败】年龄格式错误：" + e.getMessage());
        } catch (IOException e) {
            System.out.println("【导入失败】IO错误：" + e.getMessage());
        }
    }
}