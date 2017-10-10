package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.OrderMaterial;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-27 16:19
 */
@Mapper
public interface OrderMaterialMapper {
    @Insert({
            "insert into tb_order_material (order_id, ",
            "material_id)",
            "values (#{orderId}, ",
            "#{materialId})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    void insert(OrderMaterial orderMaterial);
}
