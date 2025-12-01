/**
 * 抽象人员类（无法实例化），存储人员公共属性与行为（对应 Part1、Part2 要求）
 */
public abstract class Person {
    // Part1 要求：至少 3 个实例变量
    private String id;       // 人员唯一ID
    private String name;     // 姓名
    private int age;         // 年龄

    // Part1 要求：默认构造函数
    public Person() {}

    // Part1 要求：带参构造函数（初始化所有实例变量）
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Part1 要求：所有实例变量的 Getter 和 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // 简单数据验证：年龄需为正数
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("无效年龄：" + age + "，年龄需为正数");
        }
    }
}
    

