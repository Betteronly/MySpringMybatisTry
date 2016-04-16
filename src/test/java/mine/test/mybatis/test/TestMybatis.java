package mine.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mine.test.mybatis.domain.User;

public class TestMybatis {

    public static void main(String[] args) throws IOException {

        String a = "11";
        final String b = "11";
        String c = a;
        String d = "1122";
        String e = a + "22";
        String f = "11" + "22";

        StringBuilder h = new StringBuilder("abcde");

        h.insert(2, 'A');
        h.append('F');
        h.append("GGG");
        h.delete(8, 10);
        h.replace(7, 7, "H");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(d == e);
        System.out.println(d == f);
        System.out.println(h);

        List<String> lst = new ArrayList<String>();
        lst.add(a);
        lst.add(b);
        lst.add(c);
        lst.add(d);
        lst.add(e);
        lst.add(f);
        System.out.println(lst);

        List<User> lU = new ArrayList<User>();
        User u = new User("9", 9, "999");
        lU.add(new User());
        lU.add(new User("1", 1, "111"));
        lU.add(new User("9", 9, "999"));
        lU.add(1, u);
        System.out.println(lU);

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println(map);

        // mybatis的配置文件
        String resource = "conf.xml";
        // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        System.out.println(Test.class.getClassLoader().getResource(""));
        System.out.println(Test.class.getResource("/"));
        InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
        // 构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        // Reader reader = Resources.getResourceAsReader(resource);
        // 构建sqlSession的工厂
        // SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串， me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mine.test.mybatis.mapping.UserMapper.getUser";// 映射sql的标识字符串
        // 执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
    }
}
