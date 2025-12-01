/**
 * 主类，演示 PRVMS 所有模块功能（对应 Part1-7 要求）
 * 项目名需改为：你的 MySCU 用户名-A2（如 "lfolkman-A2"）
 */
public class AssignmentTwo {
    // 主方法：执行所有演示
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        System.out.println("==================== Part3：队列管理演示 ====================");
        demo.partThree();

        System.out.println("\n\n==================== Part4A：乘坐历史演示 ====================");
        demo.partFourA();

        System.out.println("\n\n==================== Part4B：历史排序演示 ====================");
        demo.partFourB();

        System.out.println("\n\n==================== Part5：运行周期演示 ====================");
        demo.partFive();

        System.out.println("\n\n==================== Part6：导出历史演示 ====================");
        demo.partSix();

        System.out.println("\n\n==================== Part7：导入历史演示 ====================");
        demo.partSeven();
    }

    /**
     * Part3 演示：队列添加、移除、打印
     */
    public void partThree() {
        // 1. 创建操作员
        Employee operator = new Employee("E001", "张三", 30, "EMP001", "过山车操作员");
        // 2. 创建游乐设施（过山车）
        Ride rollerCoaster = new Ride("R001", "极速过山车", operator, 4);

        // 3. 添加 5 个访客到队列（Part3 要求：至少5个）
        rollerCoaster.addVisitorToQueue(new Visitor("P001", "李四", 20, "VIS001", "普通会员"));
        rollerCoaster.addVisitorToQueue(new Visitor("P002", "王五", 25, "VIS002", "VIP会员"));
        rollerCoaster.addVisitorToQueue(new Visitor("P003", "赵六", 18, "VIS003", "普通会员"));
        rollerCoaster.addVisitorToQueue(new Visitor("P004", "孙七", 30, "VIS004", "普通会员"));
        rollerCoaster.addVisitorToQueue(new Visitor("P005", "周八", 22, "VIS005", "VIP会员"));

        // 4. 移除 1 个访客（Part3 要求）
        rollerCoaster.removeVisitorFromQueue();

        // 5. 打印队列（Part3 要求）
        rollerCoaster.printQueue();
    }

    /**
     * Part4A 演示：历史记录添加、检查、计数、打印
     */
    public void partFourA() {
        // 1. 创建游乐设施（旋转木马）
        Ride carousel = new Ride("R002", "旋转木马", null, 2);

        // 2. 添加 5 个访客到历史（Part4A 要求：至少5个）
        Visitor v1 = new Visitor("P006", "吴九", 10, "VIS006", "儿童会员");
        Visitor v2 = new Visitor("P007", "郑十", 12, "VIS007", "儿童会员");
        Visitor v3 = new Visitor("P008", "钱十一", 8, "VIS008", "儿童会员");
        Visitor v4 = new Visitor("P009", "冯十二", 9, "VIS009", "儿童会员");
        Visitor v5 = new Visitor("P010", "陈十三", 11, "VIS010", "儿童会员");

        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // 3. 检查访客是否在历史中（Part4A 要求）
        Visitor checkV = new Visitor("P008", "钱十一", 8, "VIS008", "儿童会员");
        boolean isInHistory = carousel.checkVisitorFromHistory(checkV);
        System.out.println("\n访客「" + checkV.getName() + "」是否在历史中：" + (isInHistory ? "是" : "否"));

        // 4. 打印历史记录数量（Part4A 要求）
        System.out.println("历史记录访客总数：" + carousel.numberOfVisitors());

        // 5. 打印历史记录（Part4A 要求，用 Iterator）
        carousel.printRideHistory();
    }

    /**
     * Part4B 演示：历史记录排序（前后对比）
     */
    public void partFourB() {
        // 1. 创建游乐设施（海盗船）
        Ride pirateShip = new Ride("R003", "海盗船", null, 3);

        // 2. 添加 5 个访客到历史（Part4B 要求：至少5个）
        pirateShip.addVisitorToHistory(new Visitor("P011", "李十四", 28, "VIS011", "普通会员"));
        pirateShip.addVisitorToHistory(new Visitor("P012", "王十五", 22, "VIS012", "VIP会员"));
        pirateShip.addVisitorToHistory(new Visitor("P013", "张十六", 28, "VIS013", "普通会员"));
        pirateShip.addVisitorToHistory(new Visitor("P014", "刘十七", 25, "VIS014", "普通会员"));
        pirateShip.addVisitorToHistory(new Visitor("P015", "黄十八", 22, "VIS015", "VIP会员"));

        // 3. 打印排序前的历史
        System.out.println("\n【排序前】海盗船乘坐历史：");
        pirateShip.printRideHistory();

        // 4. 排序（使用 VisitorComparator，Part4B 要求）
        pirateShip.sortRideHistory(new VisitorComparator());

        // 5. 打印排序后的历史
        System.out.println("\n【排序后】海盗船乘坐历史（按年龄→姓名）：");
        pirateShip.printRideHistory();
    }

    /**
     * Part5 演示：运行游乐设施周期（队列→历史）
     */
    public void partFive() {
        // 1. 创建操作员
        Employee operator = new Employee("E002", "林十九", 28, "EMP002", "海盗船操作员");
        // 2. 创建游乐设施（大摆锤，每周期最大3人）
        Ride swing = new Ride("R004", "大摆锤", operator, 3);

        // 3. 添加 10 个访客到队列（Part5 要求：至少10个）
        for (int i = 0; i < 10; i++) {
            swing.addVisitorToQueue(new Visitor(
                    "P" + String.format("%03d", 16 + i),
                    "访客" + (16 + i),
                    18 + (i % 12),  // 年龄18-30岁
                    "VIS" + String.format("%03d", 16 + i),
                    i % 2 == 0 ? "普通会员" : "VIP会员"
            ));
        }

        // 4. 打印运行前的队列
        System.out.println("\n【运行前】大摆锤队列：");
        swing.printQueue();

        // 5. 运行一个周期（Part5 要求）
        swing.runOneCycle();

        // 6. 打印运行后的队列和历史
        System.out.println("\n【运行后】大摆锤队列：");
        swing.printQueue();
        System.out.println("\n【运行后】大摆锤乘坐历史：");
        swing.printRideHistory();
    }

    /**
     * Part6 演示：导出历史记录到CSV
     */
    public void partSix() {
        // 1. 创建游乐设施并添加历史数据
        Ride ferrisWheel = new Ride("R005", "摩天轮", null, 6);
        for (int i = 0; i < 5; i++) {  // Part6 要求：至少5个
            ferrisWheel.addVisitorToHistory(new Visitor(
                    "P" + String.format("%03d", 26 + i),
                    "摩天轮访客" + (26 + i),
                    20 + i,
                    "VIS" + String.format("%03d", 26 + i),
                    "普通会员"
            ));
        }

        // 2. 导出到CSV文件（路径可自定义）
        ferrisWheel.exportRideHistory("ferrisWheelHistory.csv");
    }

    /**
     * Part7 演示：从CSV导入历史记录并验证
     */
    public void partSeven() {
        // 1. 创建新的游乐设施（用于导入）
        Ride importedRide = new Ride("R006", "导入测试设施", null, 5);

        // 2. 从Part6的CSV文件导入（Part7 要求）
        importedRide.importRideHistory("ferrisWheelHistory.csv");

        // 3. 验证导入结果（打印数量和列表，Part7 要求）
        System.out.println("\n【导入验证】导入的访客总数：" + importedRide.numberOfVisitors());
        System.out.println("【导入验证】导入的访客列表：");
        importedRide.printRideHistory();
    }
}