package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 16:41
 */
@Mapper
public interface OrderMapper {

    @Insert({
            "insert into tb_order (account_id, serial_number, ",
            "status, current_stage, ",
            "create_date)",
            "values (#{accountId}, #{serialNumber}, ",
            "#{status}, #{currentStage}, ",
            "#{createDate})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Order order);



    @Select("select * from tb_order where account_id = #{accountId} order by id desc")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="account_id", property="accountId"),
            @Result(column="serial_number", property="serialNumber"),
            @Result(column="status", property="status"),
            @Result(column="current_stage", property="currentStage"),
            @Result(column="create_date", property="createDate")
    })
    List<Order> findByAccountId(@Param("accountId") Integer accountId);


    @Select("select * from tb_order where id = #{id}")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="account_id", property="accountId"),
            @Result(column="serial_number", property="serialNumber"),
            @Result(column="status", property="status"),
            @Result(column="current_stage", property="currentStage"),
            @Result(column="create_date", property="createDate")
    })
    Order get(Integer id);



    @Select("select * from tb_order where serial_number = #{serialNumber}")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="account_id", property="accountId"),
            @Result(column="serial_number", property="serialNumber"),
            @Result(column="status", property="status"),
            @Result(column="current_stage", property="currentStage"),
            @Result(column="create_date", property="createDate")
    })
    Order findBySerialNumber(@Param("serialNumber") String serialNumber);

    @Update("update tb_order set status = -2 where id = #{id}")
    void updateOrderStatus(@Param("id") Integer id);

    @Update({
            "update tb_order",
            "set status = #{status},",
            "current_stage = #{currentStage}",
            "where id = #{id}"
    })
    void update(Order order);
}
