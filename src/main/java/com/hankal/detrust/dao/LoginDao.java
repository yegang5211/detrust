package com.hankal.detrust.dao;

import com.hankal.detrust.domain.Device;
import com.hankal.detrust.domain.Login;
import com.hankal.detrust.vo.UserContext;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface LoginDao {
    @Select("select a.login_id, a.user_id, a.login_token, a.login_status, a.enter_time, a.update_time, a.leave_time " +
            "from user_login a, user_device b " +
            "where a.user_id = b.user_id and b.active = 1")
    public List<Login> getAll();

    @Select("select a.login_id, a.user_id,a.phone_number as phone, a.login_token, a.login_status as status, a.enter_time, a.update_time, a.leave_time " +
            "from user_login a, user_device b " +
            "where a.user_id = b.user_id and b.phone_number=#{mobilePhone}")
    public List<Login> getByMobilePhone(@Param("mobilePhone") String mobilePhone);

    @Select("select 1 from user_device where device_id = #{deviceId} limit 1")
    public String existDeviceId(@Param("deviceId") String deviceId);

    @Select("select 1 " +
            "from user_device a " +
            "where a.phone_number = #{mobilePhone} " +
            "and a.device_id = #{deviceId} " +
            "and a.active = 1 limit 1")
    public Object IsDeviceActived(
            @Param("mobilePhone") String mobilePhone, @Param("deviceId") String deviceId);

    @Insert("insert into user_device" +
            "(device_id,user_id,device_type_code,phone_number,device_params,active,active_time) " +
            "values(#{deviceId},#{userId},#{deviceCode},#{phone},#{deviceParams},#{active},#{activeTime})")
    public boolean insertNewDeviceId(Device device);

    @Update("update user_device " +
            "set active = #{active}, active_time=#{activeTime} " +
            "where phone_number = #{mobilePhone} and device_id = #{deviceId} ")
    public boolean activeDevice(@Param("deviceId") String deviceId,
                                @Param("mobilePhone") String mobilePhone,
                                @Param("activeTime") Date activeTime,
                                @Param("active") boolean active);

    @Update("update user_device " +
            "set active = 0, active_time=#{activeTime} " +
            "where phone_number = #{mobilePhone} and device_id != #{deviceId} ")
    public boolean unActiveOtherDevice(@Param("deviceId") String deviceId,
                                       @Param("mobilePhone") String mobilePhone,
                                       @Param("activeTime") Date activeTime);

    @Update("update user_login " +
            "set login_token = #{loginToken}, login_status = #{loginStatus}, enter_time=#{updateTime}, update_time=#{updateTime} " +
            "where phone_number = #{phone} and user_id = #{userId}")
    public boolean updateLogin(@Param("phone") String phone,
                               @Param("loginStatus") int loginStatus,
                               @Param("updateTime") Date updateTime,
                               @Param("loginToken") String loginToken,
                               @Param("userId") String userId);

    @Select("select 1 " +
            "from user_login a, user_device b " +
            "where a.user_id = b.user_id and b.phone_number = #{mobilePhone} and b.active = 1 and a.login_status = 0" +
            " limit 1")
    public Object IsLogined(@Param("mobilePhone") String mobilePhone);

}
