package mine.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.log4testng.Logger;

import mine.test.mybatis.domain.User;
import mine.test.mybatis.mapper.IUserMapper;
import mine.test.mybatis.mapper.IUserMapper2;
import mine.test.mybatis.mapper.IUserMapper3;

public class TestMybatisMain {
    private static Logger log = Logger.getLogger(TestMybatisMain.class);
    private static SqlSessionFactory sqlSessionFactory;

    // Main主方法来启动测试
    public static void main(String[] args) throws IOException {
        TestMybatisMain testMybatis = new TestMybatisMain();

        testMybatis.testMainA();
        testMybatis.testMybatisNoSpringA();
        testMybatis.testMybatisNoSpringB();
        testMybatis.testMybatisWithoutSpringByIF();
        testMybatis.testMybatisWithSpring();

    }

    public void testMainA() {
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
    }

    // ###################
    // ### WAYS 1 ########
    // ###################
    public void testMybatisNoSpringA() throws IOException {
        // mybatis的配置文件
        String resource = "conf-mybatis.xml";

        // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        System.out.println(Test.class.getResource("/"));
        InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);

        // 构建sqlSession的工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 创建能执行映射文件中sql的sqlSession
        SqlSession session = sqlSessionFactory.openSession();
        /**
         * 映射sql的标识字符串， me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mine.test.mybatis.mapping.UserMapper.getUserById";// 映射sql的标识字符串

        // 执行查询返回一个唯一user对象的sql
        User user = (User) session.selectOne(statement, 1);
        System.out.println(user);
        session.close();
    }

    // ###################
    // ### WAYS 2 ########
    // ###################
    public void testMybatisNoSpringB() {
        Reader reader = null;
        // mybatis的配置文件
        String resource = "conf-mybatis.xml";
        try {
            // 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
            reader = Resources.getResourceAsReader(resource);
            // 构建sqlSession的工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            log.error("Error thrown while reading the configuration: {}", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Error thrown while closing the reader: {}", e);
                }
            }
        }
        // 创建能执行映射文件中sql的sqlSession
        SqlSession session = sqlSessionFactory.openSession();
        /**
         * 映射sql的标识字符串， me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，getUser是select标签的id属性值
         * ，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mine.test.mybatis.mapping.UserMapper.getUserById";// 映射sql的标识字符串

        // 执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
        session.close();
    }

    public void testMybatisWithoutSpringByIF() {
        // WAY 1: Interface + XML
        SqlSession session = sqlSessionFactory.openSession();
        IUserMapper mapper = session.getMapper(IUserMapper.class); // 如果namespace和接口全限定名不一致，报org.apache.ibatis.binding.BindingException:Type
                                                                   // interface com..IUserMapper is not known to the
                                                                   // MapperRegistry异常。
        User user = mapper.getUserById(1);
        log.info("{}: {}" + user.getName() + user.getAddress());

        // WAY 2: Interface + 注解SQL + configuration中加入XML
        IUserMapper2 mapper2 = session.getMapper(IUserMapper2.class);
        User user2 = mapper2.getUserById(1);
        log.info("{2}: {}" + user2.getName() + user2.getAddress());

        // WAY 3: Interface + 注解SQL + configuration中加入XML
        sqlSessionFactory.getConfiguration().addMapper(IUserMapper3.class);
        IUserMapper3 mapper3 = session.getMapper(IUserMapper3.class);
        User user3 = mapper3.getUserById(1);
        log.info("{3}: {}" + user3.getName() + user3.getAddress());

        session.close();
    }

    public void testMybatisWithSpring() {
        //
        // @Resource
        // IUserMapper mapper;
        // User user = mapper.getUserById(1);
        // log.info("Name: {}, address: {}", user.getName(), user.getAddress());

    }

}
