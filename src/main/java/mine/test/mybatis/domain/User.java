package mine.test.mybatis.domain;

public class User {

    private int id;
    private String name;
    private int age;
    private String address;
    // Getters and setters are omitted

    // 如果有带参数的构造器，编译器不会自动生成无参构造器。当查询需要返回对象时，
    // ORM框架用反射来调用对象的无参构造函数，导致异常：
    // java.lang.NoSuchMethodException: com.john.hbatis.model.User.<init>()
    // 这时需要明确写出：
    public User() {
    }

    public User(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
