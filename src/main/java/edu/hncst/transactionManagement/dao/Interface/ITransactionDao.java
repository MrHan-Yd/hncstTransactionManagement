package edu.hncst.transactionManagement.dao.Interface;

import edu.hncst.transactionManagement.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hncstXDD
 */
public interface ITransactionDao {

    /**
     * 查询所有老师
     * @return List
     */
    @Select("select * from teacher")
    List<Teacher> findAll();

    /**
     * 根据老师帐号查询
     * @param teacherNumber
     * @return list
     */
    @Select("select * from teacher where teacherNumber=\"${teacherNumber}\"")
    List<Teacher> idQuery(@Param("teacherNumber") String teacherNumber);

    /**
     * 新增老师
     * @param teacherNumber
     * @param teacherName
     * @param teacherPassword
     * @param phoneNumber
     * @param post
     * @return Integer
     */
    @Insert("insert into teacher(teacherNumber,teacherName,teacherPassword,phoneNumber,post) values('${teacherNumber}','${teacherName}',${teacherPassword},'${phoneNumber}','${post}')")
    Integer insert(@Param("teacherNumber") Integer teacherNumber, @Param("teacherName") String teacherName, @Param("teacherPassword") String teacherPassword, @Param("phoneNumber") Integer phoneNumber, @Param("post") String post);

    /**
     * 修改老师信息
     * @param teacherNumber
     * @param teacherName
     * @param teacherPassword
     * @param phoneNumber
     * @param post
     * @return Integer
     */
    @Update("UPDATE teacher SET teacherNumber = '${teacherNumber}' , teacherName='${teacherName}' , teacherPassword='${teacherPassword}' , phoneNumber='${phoneNumber}' , post='${post}' WHERE id = '${id}'")
    Integer update(@Param("teacherNumber") Integer teacherNumber, @Param("teacherName") String teacherName, @Param("teacherPassword") String teacherPassword, @Param("phoneNumber") Integer phoneNumber, @Param("post") String post);


    /**
     * 删除老师
     * @param id
     * @return Integer
     */
    @Delete("delete  from teacher where id='${id}'")
    Integer delete(@Param("id") Integer id);
}
