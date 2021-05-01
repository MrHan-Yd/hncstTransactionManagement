package edu.hncst.transactionManagement.controller;

import edu.hncst.transactionManagement.dao.AdminDao;
import edu.hncst.transactionManagement.dao.StudentDao;
import edu.hncst.transactionManagement.dao.TeacherDao;
import edu.hncst.transactionManagement.entity.Admin;
import edu.hncst.transactionManagement.entity.Student;
import edu.hncst.transactionManagement.entity.Teacher;
import edu.hncst.transactionManagement.utils.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hncstXDD
 * @effect 响应登录
 */
@Controller
public class LoginController {

    private final AdminDao adminDao = new AdminDao();
    private final TeacherDao teacherDao = new TeacherDao();
    private final StudentDao studentDao = new StudentDao();

    private String checkCode = null;


    String memberAdmin = adminDao.getAll().get(0).getMemberName();
    String memberAdminTransaction = adminDao.getAll().get(1).getMemberName();
    String memberAdminStudent = adminDao.getAll().get(2).getMemberName();

    /**
     * 登录
     * @return login
     */
    @RequestMapping("/login")
    public String login(HttpSession session){
        if (session.getAttribute("userName") != null){
            return "redirect:/hncstTransactionManagement/adminIndex";
        }
        return "login";
    }

    /**
     * 匹配账号密码
     */
    @RequestMapping(value = "/logon",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, String> logon(String userName, String password, String checkCode, String choice, HttpServletRequest request) throws IOException {

        /**
         * 存储返回的json数据
         */
        Map<String,String> map = new HashMap<>();


        /**
         * 获取验证码
         * @effect 和表单验证码比较
         */
        HttpSession session = request.getSession();
        this.checkCode = (String) session.getAttribute("randomcode_key");

        /**
         * 验证码正确
         */
        if (checkCode.equalsIgnoreCase(this.checkCode)){

            /**
             * 登录为管理员
             */
            if (choice.equals(memberAdmin)){
                List<Admin> admins = adminDao.loginQuery(userName);
                if (!admins.isEmpty()){
                    for (Admin admin : admins){
                        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())){
                            map.put("login","true");
                            session.setAttribute("userName",userName);
                        } else{
                            map.put("login","false");
                            map.put("msg","用户名或密码错误");
                        }
                    }
                } else{
                    map.put("login","false");
                    map.put("msg",userName+"管理员不存在!");
                }

                /**
                 * 登录为教师
                 */
            } else if (choice.equals(memberAdminTransaction)){
                List<Teacher> teachers = teacherDao.loginQuery(userName);
                if (!teachers.isEmpty()){
                    for (Teacher teacher : teachers) {
                        if (userName.equals(teacher.getTeacherNumber()) && password.equals(teacher.getTeacherPassword())) {
                            map.put("login", "true");
                            session.setAttribute("userName", userName);
                            session.setAttribute("transactionName", teacher.getTeacherName());
                            session.setAttribute("transactionPost", teacher.getPost());
                        } else {
                            map.put("login", "false");
                            map.put("msg", "用户名或密码错误!");
                        }
                    }
                } else{
                    map.put("login","false");
                    map.put("msg","教师账号"+userName+"不存在!");
                }

                /**
                 * 登录为学生
                 */
            } else if (choice.equals(memberAdminStudent)){
                List<Student> students = studentDao.loginQuery(userName);
                if (!students.isEmpty()){
                    for (Student student : students){
                        if (userName.equals(student.getStudentNumber()) && password.equals(student.getStudentPassword())){
                            map.put("login","true");
                            session.setAttribute("userName",userName);
                            session.setAttribute("studentName",student.getStudentName());
                            session.setAttribute("studentNumber",student.getStudentNumber());
                        } else{
                            map.put("login","false");
                            map.put("msg","用户名或密码错误!");
                        }
                        break;
                    }
                } else{
                    map.put("login","false");
                    map.put("msg","学生账号"+userName+"不存在");
                }
            } else{
                map.put("login","false");
                map.put("msg","该账号不存在");
            }


        } else{
            map.put("login","false");
            map.put("msg","验证码不正确");
        }
        return map;

    }


    /**
     * 获取生成验证码显示到 UI 界面
     * @param request req
     * @param response rep
     */
    @RequestMapping(value="/checkCode")
    public void checkCode(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            //输出图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
