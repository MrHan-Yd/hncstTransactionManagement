package edu.hncst.transactionManagement.dao;

import edu.hncst.transactionManagement.entity.Admin;
import edu.hncst.transactionManagement.entity.Member;
import edu.hncst.transactionManagement.entity.Student;
import edu.hncst.transactionManagement.entity.Teacher;
import edu.hncst.transactionManagement.utils.DatebaseTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

/**
 * @author hncstXDD
 * @effect 响应管理员操作
 */
public class AdminDao {

    /**
     * map集合 使用人员区分
     */
    private static final Map<Integer,Member> DEPARTMENTS;

    /**
     * map集合 数据库所有老师数据
     */
    private Map<Integer, Teacher> transactionMap = new HashMap<>();

    /**
     * map集合 数据库所有学生数据
     */
    private Map<Integer, Student> studentHashMap =  new HashMap<>();


    /**
     * 该类用于创建工厂
     */
    private final DatebaseTools datebaseTools = new DatebaseTools();


    @Autowired
    private Teacher teacher;

    @Autowired
    private Student Student;

    /**
     * 类加载时加载
     */
    static{
        DEPARTMENTS = new HashMap<Integer,Member>();
        DEPARTMENTS.put(1,new Member(1,"admin"));
        DEPARTMENTS.put(2,new Member(2,"transaction"));
        DEPARTMENTS.put(3,new Member(3,"student"));
    }

    /**
     * 查询member中所有值
     * @return listMember
     */
    public List<Member> getAll(){
        return new LinkedList<>(DEPARTMENTS.values());
    }

    /**
     * 通过传入id返回成员名称
     * @param id id
     * @return member
     */
    public Member getMemberById(Integer id){
        return DEPARTMENTS.get(id);
    }

    /**
     * 问题所在
     */

    /**
     * 用于管理员登录匹配账号密码所用
     */
    public List<Admin> loginQuery(String userName) throws IOException {
        datebaseTools.newInputStream();
        /*
         * 该集合用于存储sql语句查询管理员用户返回数据
         */
        List<Admin> list;

        list =  datebaseTools.iAdminDao().idQuery(userName);
        return list;
    }

    /**
     * 查询所有老师数据
     */
    public Map<Integer, Teacher>  adminQueryTransaction() throws IOException {

        List<Teacher> list;
        datebaseTools.newInputStream();
        list = datebaseTools.iTransactionDao().findAll();
        Integer i = 0;
        for (Teacher teacher : list){
            ++i;
            transactionMap.put(i,new Teacher(teacher.getId(), teacher.getTeacherNumber(), teacher.getTeacherName(), teacher.getTeacherPassword(), teacher.getPhoneNumber(), teacher.getAdministrationClassId(), teacher.getPost()));
        }
        return transactionMap;
    }

    /**
     * 查询所有学生数据
     */
    public Map<Integer, Student> adminQueryStudent() throws IOException {

        List<Student> list;

        datebaseTools.newInputStream();
        list = datebaseTools.iStudentDao().findAll();
        Integer i = 0;
        for (Student student : list){
            ++i;
            studentHashMap.put(i,new Student(student.getId(), student.getStudentNumber(), student.getStudentName(), student.getStudentPassword(), student.getStudentGender(), student.getDateOfbirth(), student.getClassId(), student.getDormitoryNumberOrDayReading(), student.getStudentPhoneNumber()));
        }
        return studentHashMap;
    }
}
