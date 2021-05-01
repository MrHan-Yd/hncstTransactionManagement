package edu.hncst.transactionManagement.dao;

import edu.hncst.transactionManagement.dao.Interface.IStudentDao;
import edu.hncst.transactionManagement.entity.Student;
import edu.hncst.transactionManagement.utils.DatebaseTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hncstXDD
 */
public class StudentDao {

    @Autowired
    private Student student;

    /**
     * 用来操作响应学生请求
     */
    private static final Map<Integer, Student> STUDENTS = null;


    /**
     * 创建工厂
     */
    private final DatebaseTools datebaseTools = new DatebaseTools();

    /**
     * 登录匹配查询
     * @param userName 登录名
     * @return list
     * @throws IOException
     */
    public List<Student> loginQuery(String userName) throws IOException {
        datebaseTools.newInputStream();
        /*
         * 该集合用于存储sql语句查询管理员用户返回数据
         */
        List<Student> list;

        list =  datebaseTools.iStudentDao().idQuery(userName);
        return list;
    }

    /**
     * 学生修改信息
     */
    public boolean updateersonalInformation(String studentGender, String dateOfbirth, String studentPhoneNumber, String studentNumber) throws IOException, ParseException {
        datebaseTools.newInputStream();
        Integer result;
        result = datebaseTools.iStudentDao().updateersonalInformation(studentGender,dateOfbirth,studentPhoneNumber,studentNumber);

        if (result(result)) {
            datebaseTools.success();
            return true;
        } else{
            return false;
        }
    }

    /**
     * 判断是否有影响行数
     * @param result 影响行数
     * @return boolean
     */
    public boolean result(Integer result){
        if (result > 0){
            return true;
        } else{
            return false;
        }
    }
}
