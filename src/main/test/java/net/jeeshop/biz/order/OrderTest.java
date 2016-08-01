package net.jeeshop.biz.order;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/22
 * Time: 13:30
 */
public class OrderTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() throws IOException {
        Reader reader = Resources.getResourceAsReader("resources/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

        // populate in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();

        session.close();
    }

    @Test
    public void testBiz(){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{

        }finally {
            sqlSession.close();
        }
    }
}
