import java.util.Comparator;

/**
 * 访客比较器（实现 Comparator），用于历史记录排序（对应 Part4B 要求）
 * 排序规则：先按年龄升序，年龄相同则按姓名字典序升序
 */
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 先比较年龄（升序）
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }
        // 2. 年龄相同则比较姓名（字典序升序）
        return v1.getName().compareToIgnoreCase(v2.getName());
    }
}