package edu.hncst.transactionManagement.utils;

import edu.hncst.transactionManagement.dao.Interface.IAdminDao;
import edu.hncst.transactionManagement.dao.Interface.IStudentDao;
import edu.hncst.transactionManagement.dao.Interface.ITransactionDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hncstXDD
 * @effect 创建工厂操作数据库
 */
@Component
public class DatebaseTools {

    private InputStream in;
    private static SqlSessionFactoryBuilder builder;
    private static SqlSessionFactory factory;
    private SqlSession session;

    /**
     * 通过工厂和代理对象操作数据
     * @throws IOException
     */
    public void newInputStream() throws IOException {
        in = Resources.getResourceAsStream("myBatisConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
    }

    public IAdminDao iAdminDao() throws IOException {
        return session.getMapper(IAdminDao.class);
    }

    public IStudentDao iStudentDao() throws IOException {
        return session.getMapper(IStudentDao.class);
    }

    public ITransactionDao iTransactionDao() throws IOException {
        return session.getMapper(ITransactionDao.class);
    }

    /**
     * 该方法用于修改删除提交事务
     * @throws IOException
     */
    public void success() throws IOException {
        try {
            //修改、删除需要提交事务数据库才会更新数据
            session.commit();
            //关闭SqlSession对象
            session.close();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("异常" + e);
//           数据回滚
            session.rollback();
        } finally {
            //InputStream对象
            in.close();
        }
    }


}
