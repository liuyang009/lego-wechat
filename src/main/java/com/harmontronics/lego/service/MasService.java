package com.harmontronics.lego.service;

import com.harmontronics.lego.entity.Material;
import com.harmontronics.lego.mapper.MasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-26 16:00
 */

@Service
@Transactional
public class MasService {

    @Autowired
    private MasMapper masMapper;

    public List<Material> findAll() {
        return masMapper.findAll();
    }

    public List<Material> findByOrderId(Integer id) {
        return masMapper.findByOrderId(id);
    }

    public Material findById(int i) {
        return masMapper.findById(i);
    }

    public void updateAvailable(Material material) {
        masMapper.update(material);
    }
}
