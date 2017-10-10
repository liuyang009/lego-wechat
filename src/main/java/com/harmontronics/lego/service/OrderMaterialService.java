package com.harmontronics.lego.service;

import com.harmontronics.lego.entity.OrderMaterial;
import com.harmontronics.lego.mapper.OrderMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-27 16:19
 */
@Service
@Transactional
public class OrderMaterialService {

    @Autowired
    private OrderMaterialMapper mapper;

    public void insertOrderMaterial(OrderMaterial orderMeteria) {
        mapper.insert(orderMeteria);
    }
}
