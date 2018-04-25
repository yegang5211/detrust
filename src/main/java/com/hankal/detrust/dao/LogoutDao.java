package com.hankal.detrust.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface LogoutDao {
    @Update("update user_login " +
            "set login_status = #{status}, leave_time = #{leaveTime}, update_time = #{leaveTime} " +
            "where phone_number = #{mobilePhone}")
    public boolean logout(@Param("status") int status,
                          @Param("mobilePhone") String mobilePhone,
                          @Param("leaveTime") Date leaveTime);

    @Select("select 1 " +
            "from user_login a " +
            "where a.phone_number = #{mobilePhone} and a.login_status in (-1,-2,-3)" +
            " limit 1")
    public Object IsLogouted(@Param("mobilePhone") String mobilePhone);
}
