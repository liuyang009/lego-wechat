package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.Stage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 13:52
 */
public interface StageMapper {

    @Select("SELECT * FROM tb_stage where order_id = #{orderId} ORDER BY  stage_seq ")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER),
            @Result(column="stage_seq", property="stageSeq", jdbcType=JdbcType.INTEGER),
            @Result(column="node_name", property="nodeName", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pre_start_date", property="preStartDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pre_end_date", property="preEndDate", jdbcType=JdbcType.TIMESTAMP),
    })
    List<Stage> findByOrderId(Integer orderId);


    @Update({
            "update tb_stage",
            "set order_id = #{orderId},",
            "stage_seq = #{stageSeq},",
            "node_name = #{nodeName},",
            "start_date = #{startDate,jdbcType=TIMESTAMP},",
            "end_date = #{endDate,jdbcType=TIMESTAMP},",
            "pre_start_date = #{preStartDate,jdbcType=TIMESTAMP},",
            "pre_end_date = #{preEndDate,jdbcType=TIMESTAMP}",
            "where id = #{id}"
    })
    void update(Stage stage);

    @Insert({
            "insert into tb_stage (order_id, stage_seq, ",
            "node_name, start_date, ",
            "end_date, pre_start_date,pre_end_date)",
            "values (#{orderId}, #{stageSeq}, ",
            "#{nodeName}, #{startDate}, ",
            "#{endDate}, #{preStartDate},#{preEndDate})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    void insert(Stage stage);
}
