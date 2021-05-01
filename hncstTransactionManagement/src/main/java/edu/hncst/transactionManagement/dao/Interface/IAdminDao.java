package edu.hncst.transactionManagement.dao.Interface;

import edu.hncst.transactionManagement.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hncstXDD
 */
public interface IAdminDao {

    /**
     * 根据用户名查询
     * @param userName 用户名
     * @return list
     */
    @Select("select * from administrators where userName=\"${userName}\"")
    List<Admin> idQuery(@Param("userName") String userName);
}
