package edu.hncst.transactionManagement.dao.Interface;

import edu.hncst.transactionManagement.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.Date;
import java.util.List;

/**
 * @author hncstXDD
 */
public interface IStudentDao {

    /**
     * 查询所有学生
     * @return List
     */
    @Select("select * from student")
    List<Student> findAll();

    /**
     * 根据学生帐号查询
     * @param studentNumber
     * @return list
     */
    @Select("select * from student where studentNumber=\"${studentNumber}\"")
    List<Student> idQuery(@Param("studentNumber") String studentNumber);

    /**
     * 新增学生
     * @param studentNumber 帐号
     * @param studentName  姓名
     * @param studentPassword 密码
     * @param studentGender 性别
     * @param dateOfbirth 出生日期
     * @param classId 班级Id
     * @param dormitoryNumberOrDayReading 宿舍号
     * @param studentPhoneNumber 手机号
     * @return Integer
     */
    @Insert("insert into student(studentNumber,studentName,studentPassword,studentGender,dateOfbirth,classId,dormitoryNumberOrDayReading,studentPhoneNumber) values('${studentNumber}','${studentName}',${studentPassword},'${studentGender}','${dateOfbirth}','${classId}','${dormitoryNumberOrDayReading}','${studentPhoneNumber}')")
    Integer insert(@Param("studentNumber") Integer studentNumber, @Param("studentName") String studentName, @Param("studentPassword") String studentPassword, @Param("studentGender") String studentGender, @Param("dateOfbirth") Date dateOfbirth, @Param("classId") Integer classId, @Param("dormitoryNumberOrDayReading") String dormitoryNumberOrDayReading,@Param("studentPhoneNumber") Integer studentPhoneNumber);

    /**
     * 修改学生信息
     * @param studentNumber 账号
     * @param studentName 姓名
     * @param studentPassword 密码
     * @param studentGender 性别
     * @param dateOfbirth 出生日期
     * @param classId 班级Id
     * @param dormitoryNumberOrDayReading 宿舍号
     * @param studentPhoneNumber 手机号
     * @return Integer
     */
    @Update("UPDATE student SET studentNumber = '${studentNumber}' , studentName='${studentName}' , studentPassword='${studentPassword}' , studentGender='${studentGender}' , dateOfbirth='${dateOfbirth}' , classId='${classId}' , dormitoryNumberOrDayReading='${dormitoryNumberOrDayReading}' , studentPhoneNumber='${studentPhoneNumber}' WHERE id = '${id}'")
    Integer update(@Param("studentNumber") Integer studentNumber, @Param("studentName") String studentName, @Param("studentPassword") String studentPassword, @Param("studentGender") String studentGender, @Param("dateOfbirth") Date dateOfbirth,@Param("classId") Integer classId,@Param("dormitoryNumberOrDayReading") String dormitoryNumberOrDayReading,@Param("studentPhoneNumber") Integer studentPhoneNumber);


    /**
     * 学生修改（补充）该个人信息
     * @param studentGender 性别
     * @param dateOfbirth 出生日期
     * @param studentPhoneNumber 手机号
     * @return Integer
     */
    @Update("UPDATE student SET studentGender='${studentGender}' , dateOfbirth='${dateOfbirth}' , studentPhoneNumber='${studentPhoneNumber}' WHERE studentNumber = '${studentNumber}'")
    Integer updateersonalInformation(@Param("studentGender") String studentGender,@Param("dateOfbirth") String dateOfbirth,@Param("studentPhoneNumber")String studentPhoneNumber,@Param("studentNumber") String studentNumber);

    /**
     * 删除学生
     * @param id id
     * @return Integer
     */
    @Delete("delete  from student where id='${id}'")
    Integer delete(@Param("id") Integer id);
}
