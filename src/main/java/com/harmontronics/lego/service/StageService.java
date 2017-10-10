package com.harmontronics.lego.service;

import com.harmontronics.lego.entity.Stage;
import com.harmontronics.lego.mapper.StageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 13:49
 */
@Service
@Transactional
public class StageService {

    @Autowired
    private StageMapper stageMapper;

    /**
     * 根据订单ID获取订单的生成详情
     * @param id 订单ID
     * @return List<Stage>
     */
    public List<Stage> findByOrderId(Integer id) {
        return stageMapper.findByOrderId(id);
    }

    public void update(Stage stage) {
        stageMapper.update(stage);
    }

    public void insert(Stage stage) {
        stageMapper.insert(stage);
    }
}
