package com.hankal.detrust.dao;

import com.hankal.detrust.domain.Device;
import com.hankal.detrust.domain.Login;
import com.hankal.detrust.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterDao {
    @Select("select 1 from user_device where phone_number = #{phoneNumber} limit 1")
    public Object getDeviceByPhone(@Param("phoneNumber") String phoneNumber);

    @Insert("insert into " +
            "user(user_id, user_name, user_nickname,create_time,update_time) " +
            "values(#{userId},#{userName},#{nickName},#{createTime},#{updateTime})")
    public int insertUser(User user);

    @Insert("insert into " +
            "user_device(device_id, user_id, device_type_code, phone_number, device_params, active, active_time) " +
            "values(#{deviceId},#{userId},#{deviceCode},#{phone},#{deviceParams},#{active},#{activeTime})")
    public int insertUserDevice(Device device);

    @Insert("insert into user_role(user_id, role_type_code) values(#{userId},#{roleTypeCode})")
    public int insertUserRole(@Param("userId") String userId, @Param("roleTypeCode") int roleTypeCode);

    @Insert("insert into " +
            "user_login(login_id,user_id,phone_number,login_token,login_status,login_password,enter_time,update_time) " +
            "values(#{loginId},#{userId},#{phone},#{loginToken},#{status},#{password},#{enterTime},#{updateTime})")
    public int insertUserLogin(Login login);
}
