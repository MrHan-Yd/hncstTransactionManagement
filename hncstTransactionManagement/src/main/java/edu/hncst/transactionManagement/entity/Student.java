package edu.hncst.transactionManagement.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hncstXDD
 * @effect 封装学生信息
 */
@Component
public class Student {

    /**
     * id
     * 学生帐号
     * 学生姓名
     * 学生密码
     * 学生性别
     * 出生日期
     * 班级id
     * 住宿或走读
     * 学生手机号
     */
    private Integer id;
    private String studentNumber;
    private String studentName;
    private String studentPassword;
    private String studentGender;
    private Date dateOfbirth;
    private String classId;
    private String dormitoryNumberOrDayReading;
    private String studentPhoneNumber;

    /**
     * 构造 有参 and 空参
     * @param id id
     * @param studentNumber 学生账号
     * @param studentName 姓名
     * @param studentPassword 密码
     * @param studentGender 性别
     * @param dateOfbirth 出生日期
     * @param classId 班级id
     * @param dormitoryNumberOrDayReading 住宿号
     * @param studentPhoneNumber 手机号
     */
    public Student(Integer id, String studentNumber, String studentName, String studentPassword, String studentGender, Date dateOfbirth, String classId, String dormitoryNumberOrDayReading, String studentPhoneNumber) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentGender = studentGender;
        this.dateOfbirth = dateOfbirth;
        this.classId = classId;
        this.dormitoryNumberOrDayReading = dormitoryNumberOrDayReading;
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public Student(String studentNumber,String studentName,String classId,String dormitoryNumberOrDayReading){
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.classId = classId;
        this.dormitoryNumberOrDayReading = dormitoryNumberOrDayReading;
    }

    public Student(){}


    /**
     * get and set
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Date getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(Date dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDormitoryNumberOrDayReading() {
        return dormitoryNumberOrDayReading;
    }

    public void setDormitoryNumberOrDayReading(String dormitoryNumberOrDayReading) {
        this.dormitoryNumberOrDayReading = dormitoryNumberOrDayReading;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }
}
