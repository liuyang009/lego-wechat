package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.Material;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-26 16:28
 */

@Mapper
public interface MasMapper {

    @Select("SELECT * FROM tb_material")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.VARCHAR)
    })
    List<Material> findAll();


    @Select("SELECT * FROM tb_material where id in(select material_id from tb_order_material WHERE order_id=#{orderId})")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.VARCHAR)
    })
    List<Material> findByOrderId(@Param("orderId") Integer orderId);

    @Select("SELECT * FROM tb_material WHERE id=#{id}")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="available", property="available", jdbcType=JdbcType.VARCHAR)
    })
    Material findById(@Param("id") Integer id);


    @Update("update tb_material set available = #{available} where id = #{id}")
    void update(Material material);
}
