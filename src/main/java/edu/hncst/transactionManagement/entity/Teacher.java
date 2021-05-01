package edu.hncst.transactionManagement.entity;

import org.springframework.stereotype.Component;

/**
 * @author hncstXDD
 * @effect 封装老师信息
 */
@Component
public class Teacher {

    /**
     * id
     * 老师帐号
     * 老师姓名
     * 老师密码
     * 老师手机
     * 老师管理班级
     * 职务
     */
    private Integer id;
    private String teacherNumber;
    private String teacherName;
    private String teacherPassword;
    private String phoneNumber;
    private Integer administrationClassId;
    private String post;

    /**
     * 构造函数 有参 and 空参
     * @param teacherNumber 老师账号
     * @param teacherName 老师姓名
     * @param teacherPassword 老师密码
     * @param phoneNumber 老师手机
     * @param administrationClassId 管理班级
     * @param post 职务
     */
    public Teacher(Integer id, String teacherNumber, String teacherName, String teacherPassword, String phoneNumber, Integer administrationClassId, String post) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
        this.phoneNumber = phoneNumber;
        this.administrationClassId = administrationClassId;
        this.post = post;
    }

    public Teacher(String teacherNumber, String teacherName, String phoneNumber, String post) {
        this.teacherNumber = teacherNumber;
        this.teacherName = teacherName;
        this.phoneNumber = phoneNumber;
        this.post = post;
    }

    public Teacher(){};


    /**
     * get and set
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAdministrationClassId() {
        return administrationClassId;
    }

    public void setAdministrationClassId(Integer administrationClassId) {
        this.administrationClassId = administrationClassId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
