/**
 * 员工类（继承 Person），管理游乐设施工作人员信息（对应 Part1 要求）
 */
public class Employee extends Person {
    // Part1 要求：至少 2 个特有实例变量
    private String employeeId;  // 员工唯一ID
    private String position;    // 职位（如“过山车操作员”“安全员”）

    // Part1 要求：默认构造函数
    public Employee() {}

    // Part1 要求：带参构造函数（初始化父类+子类属性）
    public Employee(String id, String name, int age, String employeeId, String position) {
        super(id, name, age);  // 调用父类构造函数
        this.employeeId = employeeId;
        this.position = position;
    }

    // Part1 要求：所有特有实例变量的 Getter 和 Setter
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // 重写 toString()，便于打印员工信息
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + super.getId() + "'" +  // 父类属性
                ", name='" + super.getName() + "'" +
                ", age=" + super.getAge() +
                ", employeeId='" + employeeId + "'" +
                ", position='" + position + "'" +
                '}';
    }
}