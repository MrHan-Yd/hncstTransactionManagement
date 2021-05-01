package edu.hncst.transactionManagement.dao;

import edu.hncst.transactionManagement.entity.Teacher;
import edu.hncst.transactionManagement.utils.DatebaseTools;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hncstXDD
 */
public class TeacherDao {

    /**
     * 用来操作响应老师请求
     */
    private static final Map<Integer, Teacher> TRANSACTIONS = null;

    /**
     * 该类用于创建工厂
     */
    private final DatebaseTools datebaseTools = new DatebaseTools();

    private List<Teacher> list;

    public List<Teacher> loginQuery(String userName) throws IOException {
        datebaseTools.newInputStream();
        /*
         * 该集合用于存储sql语句查询管理员用户返回数据
         */
        List<Teacher> list;

        list =  datebaseTools.iTransactionDao().idQuery(userName);
        return list;
    }
}
