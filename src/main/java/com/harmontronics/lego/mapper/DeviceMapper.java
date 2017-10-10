package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:34
 */
@Mapper
public interface DeviceMapper {

    @Select("SELECT * FROM tb_device")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="iot_device_id", property="iotDeviceId", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="firm", property="firm", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Device> findAll();


    @Update("update tb_device set status = #{status} where id = #{id}")
    void update(Device device);

    @Select("SELECT * FROM tb_device WHERE id=#{deviceId}")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="iot_device_id", property="iotDeviceId", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="firm", property="firm", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    Device findById(@Param("deviceId") Integer deviceId);
}
