package com.hankal.detrust.dao;

import com.hankal.detrust.domain.UserInfo;
import com.hankal.detrust.vo.UserContext;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select " +
            "a.user_id, a.user_nickname,a.create_time,a.update_time," +
            "b.phone_number,b.login_status,b.enter_time,b.leave_time," +
            "c.device_id,c.device_type_code,c.active,c.active_time " +
            "from user a, user_login b, user_device c " +
            "where a.user_id = b.user_id and a.user_id = c.user_id " +
            "and c.phone_number = #{mobilePhone}")
    public UserInfo findByUserName(@Param("mobilePhone") String mobilePhone);

    @Select("select " +
            "a.user_id, a.user_nickname,a.create_time,a.update_time," +
            "b.phone_number,b.login_status,b.enter_time,b.leave_time," +
            "c.device_id,c.device_type_code,c.active,c.active_time " +
            "from user a, user_login b, user_device c " +
            "where a.user_id = b.user_id and a.user_id = c.user_id")
    public List<UserInfo> getAll();

    @Select("select 1 from user a, user_device b, user_login c " +
            "where " +
            "a.user_id = b.user_id and a.user_id = c.user_id " +
            "and b.active = 1 and b.phone_number = c.phone_number " +
            "and b.phone_number = #{phoneNumber} and b.device_id = #{deviceId} and a.user_id = #{userId}")
    public Object exitOnlineUser(@Param("deviceId") String deviceId,
                                 @Param("phoneNumber") String phoneNumber,
                                 @Param("userId") String userId);

    @Select("select " +
            "a.user_id as userId, b.login_id as loginId, " +
            "c.device_id as deviceId, c.phone_number as mobilePhone, " +
            "b.login_token as loginToken " +
            "from user a, user_login b, user_device c " +
            "where a.user_id = b.user_id and a.user_id = c.user_id " +
            "and b.login_password = #{password} and c.phone_number = #{mobilePhone} limit 1")
    public UserContext getUserContext(
            @Param("mobilePhone") String mobilePhone, @Param("password") String password);

}
