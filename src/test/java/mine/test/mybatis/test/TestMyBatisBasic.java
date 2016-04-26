package mine.test.mybatis.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import mine.test.mybatis.domain.User;
import mine.test.mybatis.mapper.IUserMapper;

/*
 * TESTNG Test
 */
public class TestMyBatisBasic {
    // private static final Logger log = LoggerFactory.getLogger(TestMyBatisBasic.class); // slf4jlog
    private static final Logger log = Logger.getLogger(TestMyBatisBasic.class); // testng.log
    private static SqlSessionFactory sqlSessionFactory;

    private static Reader reader;

    @BeforeClass
    public static void initial() {
        try {
            reader = Resources.getResourceAsReader("conf-mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            log.error("Error thrown while reading the configuration: {}", e); // slf4j log
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Error thrown while closing the reader: {}", e);// slf4j log
                }
            }
        }
    }

    @Test
    public void queryTest() {
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("mine.test.mybatis.mapping.UserMapper.getUserById", 1);
        // log.info("{}: {}, {}", user.getName(), user.getAddress(), 122); // slf4j log
        log.info("{}: {}, {}" + user.getName() + user.getAddress() + 122); //
    }

    @Test
    public void queryInInterfaceWayTest() {
        SqlSession session = sqlSessionFactory.openSession();
        // 如果namespace和接口全限定名不一致，报org.apache.ibatis.binding.BindingException:
        // Type interface com..IUserMapper is not known to the MapperRegistry异常。
        IUserMapper mapper = session.getMapper(IUserMapper.class);
        User user = mapper.getUserById(1);
        // log.info("{}: {}", user.getName(), user.getAddress());
        log.info("{}: {}" + user.getName() + user.getAddress());
    }
}
