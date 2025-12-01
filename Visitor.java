/**
 * 访客类（继承 Person），管理主题公园访客信息（对应 Part1 要求）
 */
public class Visitor extends Person {
    // Part1 要求：至少 2 个特有实例变量
    private String visitorId;     // 访客唯一ID
    private String membershipType;// 会员类型（如“普通会员”“VIP会员”）

    // Part1 要求：默认构造函数
    public Visitor() {}

    // Part1 要求：带参构造函数（初始化父类+子类属性）
    public Visitor(String id, String name, int age, String visitorId, String membershipType) {
        super(id, name, age);  // 调用父类构造函数
        this.visitorId = visitorId;
        this.membershipType = membershipType;
    }

    // Part1 要求：所有特有实例变量的 Getter 和 Setter
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    // 重写 toString()，便于打印访客信息
    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + super.getId() + "'" +  // 父类属性
                ", name='" + super.getName() + "'" +
                ", age=" + super.getAge() +
                ", visitorId='" + visitorId + "'" +
                ", membershipType='" + membershipType + "'" +
                '}';
    }
}