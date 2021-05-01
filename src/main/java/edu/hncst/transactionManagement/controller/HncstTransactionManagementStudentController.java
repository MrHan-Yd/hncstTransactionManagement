package edu.hncst.transactionManagement.controller;


import edu.hncst.transactionManagement.dao.StudentDao;
import edu.hncst.transactionManagement.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author hncstXDD
 * @effect 响应学生操作
 */
@Controller
@RequestMapping("/hncstTransactionManagementStudent")
public class HncstTransactionManagementStudentController {


    /**
     * SutdentDao对象
     */
    StudentDao studentDao = new StudentDao();

    /**
     * 跳转至主页
     * @param model 模型
     * @param request req
     * @return 跳转的jsp文件名
     */
    @RequestMapping("/studentIndex")
    public String transactionIndex(Model model,HttpServletRequest request){
        HttpSession session =  request.getSession();

        model.addAttribute("userName", session.getAttribute("studentName"));
        return "studentIndex";
    }

    /**
     * 学生查看个人信息响应
     * @param request req
     * @return Model数据模型 返回学生的信息到页面
     * @throws IOException
     */
    @RequestMapping(value = "/personalInformation",produces = "text/html;charset=UTF-8")
    public ModelAndView personalInformation(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("studentIndex");
        HttpSession session =  request.getSession();
        String userName = (String)session.getAttribute("userName");
        List<Student> studentList = studentDao.loginQuery(userName);
        modelAndView.addObject("personalInformation",studentList);
        return modelAndView;
    }

    /**
     * 学生更新信息提交表单响应
     * @param studentGender 性别
     * @param dateOfbirth 出生日期
     * @param studentPhoneNumber 手机号
     * @param request req
     * @return model数据模型
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/personalInformationModify",produces = "text/html;charset=UTF-8")
    public ModelAndView personalInformationModify(@RequestParam("studentGender") String studentGender, @RequestParam("dateOfbirth")String dateOfbirth, @RequestParam("studentPhoneNumber") String studentPhoneNumber, HttpServletRequest request) throws IOException, ParseException {
        HttpSession session =  request.getSession();
        String studentNumber = (String)session.getAttribute("studentNumber");
        ModelAndView modelAndView = new ModelAndView("studentIndex");
        boolean result = studentDao.updateersonalInformation(studentGender,dateOfbirth,studentPhoneNumber,studentNumber);

        /**
         * 提示使用者个人信息修改是否成功
         */
        if (result) {
            modelAndView.addObject("msg","修改成功!");
        } else{
            modelAndView.addObject("msg","修改失败!");
        }
       return modelAndView;
    }


    /**
     * 请假
     */
    @RequestMapping("/leaveAdd")
    public ModelAndView leaveAdd(HttpServletRequest request,@RequestParam("file")MultipartFile file){
//        @RequestParam("startTime") String startTime,@RequestParam("endTime")String endTime,@RequestParam("reason")String reason,
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.println(reason);
        ModelAndView modelAndView = new ModelAndView("studentIndex");
        if (!file.isEmpty()){
            modelAndView.addObject("msg","请上传凭证");
        }

        /*
        得到上传文件名
         */
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        /*
        截取后缀名
         */
        String siff = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(siff);

        /*
        生成随机文件名
         */
        String fileName = System.currentTimeMillis() + siff;
        System.out.println(fileName);

        /*
        得到项目真实路径
         */
        String realPath = request.getServletContext().getRealPath("/");
        System.out.println(realPath);

        /*
        设置文件存放路径
         */
        File filePath = new File(realPath+"/upload");

        /*
        判断文件目录是否存在，不存在则新建
         */
        if (!filePath.exists()){
            //目录不存在，则新建
            filePath.mkdir();
        }

        /*
        上传文件
         */

        try {
            modelAndView.addObject("msg","上传成功");
            file.transferTo(new File(filePath,fileName));
        } catch (IOException e){
            modelAndView.addObject("msg","上传失败");
        }
        return modelAndView;
    }

}
