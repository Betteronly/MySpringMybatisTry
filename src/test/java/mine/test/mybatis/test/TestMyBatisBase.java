package mine.test.mybatis.test;

// public class TestMyBatisBase {
//
// private static final Logger log = LoggerFactory.getLogger(TestMyBatisBase.class);
// private static SqlSessionFactory sqlSessionFactory;
//
// private static Reader reader;
//
// @BeforeClass
// public static void initial() {
// try {
// reader = Resources.getResourceAsReader("Configuration.xml");
// sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
// } catch (IOException e) {
// log.error("Error thrown while reading the configuration: {}", e);
// } finally {
// if (reader != null) {
// try {
// reader.close();
// } catch (IOException e) {
// log.error("Error thrown while closing the reader: {}", e);
// }
// }
// }
// }
//
// @Test
// public void queryTest() {
// SqlSession session = sqlSessionFactory.openSession();
// User user = (User) session.selectOne("mine.test.UserMapper.getUserById", 1);
// log.info("{}: {}", user.getName(), user.getAddress());
// }
//
// }
