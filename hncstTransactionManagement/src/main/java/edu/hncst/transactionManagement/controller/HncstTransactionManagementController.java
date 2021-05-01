package edu.hncst.transactionManagement.controller;

import edu.hncst.transactionManagement.dao.AdminDao;
import edu.hncst.transactionManagement.entity.Student;
import edu.hncst.transactionManagement.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hncstXDD
 * @effect 响应管理员操作
 */
@Controller
@RequestMapping("/hncstTransactionManagement")
public class HncstTransactionManagementController {


    /**
     * adminDao对象
     * TransactionDao对象
     * StudentDao对象
     * session验证码
     */
    private final AdminDao adminDao = new AdminDao();

    private String addminUserName = null;

    /***
     * 分页一个页有几条数据
     */
    private int pageSize = 5;

    /**
     * 学生临时列表分页，前端点击对应的页数
     */
    private int StudentID;

    /**
     * 教师临时列表分页，前端点击对应的页数
     */
    private int TransactionID;

    /**
     * 存储数据库查询所有老师数据
     */
    private Map<Integer, Teacher> transactionMap = new HashMap<>();

    /**
     * 存储数据库查询所有学生数据
     */
    private Map<Integer, Student> studentMap = new HashMap<>();

    /**
     * 存储新增老师集合
     */
    private final List<Teacher> teacherList = new CopyOnWriteArrayList<>();

    /**
     * 存储新增学生集合
     */
    private final List<Student> studentList = new CopyOnWriteArrayList<>();

    /**
     * 主页响应
     * @param model 用户名数据模型
     * @return String重定向
     * @throws IOException
     */
    @RequestMapping(value = "/adminIndex")
    public String adminIndex(Model model) throws IOException {
        /**
         * 获取数据库所有老师数据存储集合
         */
        transactionMap = adminDao.adminQueryTransaction();

        /**
         * 获取数据库所有学生数据存储集合
         */
        studentMap = adminDao.adminQueryStudent();
        model.addAttribute("userName",addminUserName);
        return "adminIndex";
    }



    /**
     * 管理员新增用户响应
     */
    @RequestMapping("/adminIndexAdd")
    public ModelAndView addIndexAdd() throws IOException {
        ModelAndView modelAndView = new ModelAndView("adminIndexAdd");
        modelAndView.addObject("userName",this.addminUserName);

        modelAndView.addObject("addTransaction",deleteTransactionID());
        modelAndView.addObject("TransactionNumber",transactionSize());

        modelAndView.addObject("addStudent",deleteStudentID());
        modelAndView.addObject("StudentNumber",studentSize());

        return modelAndView;
    }


    /**
     * 管理员新增老师用户提交表单响应
     * @param teacherNumber 老师帐号
     * @param teacherName 老师姓名
     * @param phoneNumber 老师手机
     * @param post 老师职务
     */
    @RequestMapping(value = "/addTransaction",produces = "text/html;charset=UTF-8")
    public ModelAndView addTransaction(@RequestParam("teacherNumber") String teacherNumber, @RequestParam("teacherName") String teacherName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("post") String post) throws IOException {

        ModelAndView modelAndView = new ModelAndView("adminIndexAdd");
        /**
         * 遍历map和接收到教师账号进行匹配，已存在则提示，否则存入list集合
         */
        for (Teacher teacher : transactionMap.values()){
            if (teacherNumber.equals(teacher.getTeacherNumber())){
                modelAndView.addObject("msg",teacherNumber+"教师账号已存在");
            } else{
                if (teacherList.isEmpty()){
                    teacherList.add(new Teacher(teacherNumber,teacherName,phoneNumber,post));
                } else{
                    boolean rs = false;
                    for (Teacher value : teacherList){
                        rs = !teacherNumber.equals(value.getTeacherNumber());
                    }
                    if (rs){
                        teacherList.add(new Teacher(teacherNumber,teacherName,phoneNumber,post));
                    } else{
                        modelAndView.addObject("msg",teacherNumber + "新增教师列表中已存在该账号");
                    }
                }
            }

        }
        modelAndView.addObject("addTransaction",deleteTransactionID());
        modelAndView.addObject("TransactionNumber",transactionSize());

        modelAndView.addObject("addStudent",deleteStudentID());
        modelAndView.addObject("StudentNumber",studentSize());

        return modelAndView;
    }


    /**
     * 删除教师临时列表内容时响应
     */
    @RequestMapping("/deleteTransaction/{id}")
    public ModelAndView deleteTransaction(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("redirect:../adminIndexAdd");
        for(int i = 0; i< teacherList.size(); i++){
            if(teacherList.get(i).getTeacherNumber().equals(id)) {
                teacherList.remove(i);
                break;
            }
        }

        modelAndView.addObject("addTransaction",deleteTransactionID());
        modelAndView.addObject("TransactionNumber",transactionSize());

        modelAndView.addObject("addStudent",deleteStudentID());
        modelAndView.addObject("StudentNumber",studentSize());
        return modelAndView;
    }

    /**
     * 管理员新增老师用户提交表单响应
     * @param studentNumber 学生帐号
     * @param studentName 学生姓名
     * @param classId 学生班级
     * @param dormitoryNumberOrDayReading 住宿
     */
    @RequestMapping(value = "/addStudent",produces = "text/html;charset=UTF-8")
    public ModelAndView addStudent(@RequestParam("studentNumber") String studentNumber, @RequestParam("studentName") String studentName, @RequestParam("classId") String classId, @RequestParam("dormitoryNumberOrDayReading") String dormitoryNumberOrDayReading) throws IOException {

        ModelAndView modelAndView = new ModelAndView("adminIndexAdd");
        /**
         * 遍历map和接收到学生账号进行匹配，已存在则提示，否则存入list集合
         */
        for (Student student : studentMap.values()){
            if (studentNumber.equals(student.getStudentNumber())){
                modelAndView.addObject("msg",studentNumber+"学生账号已存在");
            } else{
                if (studentList.isEmpty()){
                    studentList.add(new Student(studentNumber,studentName,classId,dormitoryNumberOrDayReading));
                } else{
                    boolean rs = false;
                    for (Student value : studentList){
                        rs = !studentNumber.equals(value.getStudentNumber());
                    }
                    if (rs) {
                        studentList.add(new Student(studentNumber, studentName, classId, dormitoryNumberOrDayReading));
                    }else{
                        modelAndView.addObject("msg",studentNumber + "新增学生列表中已存在该账号");
                    }
                }
            }
        }

        modelAndView.addObject("addTransaction",deleteTransactionID());
        modelAndView.addObject("TransactionNumber",transactionSize());

        modelAndView.addObject("addStudent",deleteStudentID());
        modelAndView.addObject("StudentNumber",studentSize());
        return modelAndView;
    }


    /**
     * 删除学生临时列表内容时响应
     */
    @RequestMapping("/deleteStudent/{id}")
    public ModelAndView deleteStudent(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("redirect:../adminIndexAdd");
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).getStudentNumber().equals(id)) {
                studentList.remove(i);
                break;
            }
        }
        modelAndView.addObject("addTransaction",deleteTransactionID());
        modelAndView.addObject("TransactionNumber",transactionSize());

        modelAndView.addObject("addStudent",deleteStudentID());
        modelAndView.addObject("StudentNumber",studentSize());
        return modelAndView;
    }

    /**
     * 学生临时列表删除数据页面计算
     * @return 当前页面的长度大于0则返回该页，如果剩余页数大于1则查询上一页的数据
     */
    public List<Student> deleteStudentID(){
        if (studentList(StudentID).size() >0){
           return studentList(StudentID);
        } else{
            if (StudentID > 1){
                return studentList(StudentID-1);
            } else{
                return studentList(StudentID);
            }
        }
    }

    /**
     * 老师临时列表删除数据页面计算
     * @return 当前页面的长度大于0则返回该页，如果剩余页数大于1则查询上一页的数据
     */
    public List<Teacher> deleteTransactionID(){
        if (transactionList(TransactionID).size() >0){
            return transactionList(TransactionID);
        } else{
            if (TransactionID > 1){
                return transactionList(TransactionID-1);
            } else{
                return transactionList(TransactionID);
            }
        }
    }

    /**
     * 计算学生数据总分页数
     * @return 页数
     */
    public int studentSize(){
        if (studentList.size() % pageSize == 0){
            return studentList.size() / pageSize;
        } else{
            return (studentList.size() / pageSize)+1;
        }
    }

    /**
     * 计算教师数据总分页数
     * @return 页数
     */
    public int transactionSize(){
        if (teacherList.size() % pageSize == 0){
            return teacherList.size() / pageSize;
        } else{
            return (teacherList.size() / pageSize)+1;
        }
    }

    /**
     * 从集合从按照一页5条拿取数据
     * @param pageNumber 页码
     * @return 老师临时列表集合
     */
    public List<Teacher> transactionList(int pageNumber){
        List<Teacher> teacherMemberArticleBeanPage = new ArrayList<>();
        int currIdx = (pageNumber > 1 ? (pageNumber -1) * pageSize : 0);
        for (int i = 0; i < pageSize && i < teacherList.size() - currIdx; i++) {
            Teacher teacherBean = teacherList.get(currIdx + i);
            teacherMemberArticleBeanPage.add(teacherBean);
        }
        return teacherMemberArticleBeanPage;
    }

    /**
     * 从集合从按照一页5条拿取数据
     * @param pageNumber 页码
     * @return 学生临时列表集合
     */
    public List<Student> studentList(int pageNumber){
        List<Student> studentMemberArticleBeanPage = new ArrayList<>();
        int currIdx = (pageNumber > 1 ? (pageNumber -1) * pageSize : 0);
        for (int i = 0; i < pageSize && i < studentList.size() - currIdx; i++) {
            Student studentBean = studentList.get(currIdx + i);
            studentMemberArticleBeanPage.add(studentBean);
        }
        return studentMemberArticleBeanPage;
    }

    /**
     * 接收从页面传入学生列表的页码赋值个ID成员变量
     * @param id 页码
     * @return 重定向到新增页面
     */
    @RequestMapping("/StudentId")
    public String studentId(@RequestParam(required = false) Integer id){
        if ( id > 0){
            StudentID = id;
        } else{
            StudentID = 1;
        }
        return "redirect:../hncstTransactionManagement/adminIndexAdd";
    }

    /**
     * 接收从页面传入教师列表的页码赋值个ID成员变量
     * @param id 页码
     * @return 重定向到新增页面
     */
    @RequestMapping("/TransactionId")
    public String transactionId(@RequestParam(required = false) Integer id){
        if ( id > 0){
            TransactionID = id;
        } else{
            TransactionID = 1;
        }
        return "redirect:../hncstTransactionManagement/adminIndexAdd";
    }


    /**
     * 退出登录
     * @param request req
     * @return 重定向到login
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("userName");
        return "redirect:/login";
    }


}
