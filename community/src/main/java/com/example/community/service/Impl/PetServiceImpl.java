package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.PetMapper;
import com.example.community.domain.Car;
import com.example.community.domain.Pet;
import com.example.community.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PetServiceImpl implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Override
    public List<Pet> selAll() {
        List<Pet> petList = petMapper.selectList(null);
        return petList;
    }

    @Override
    public Page<Pet> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Pet> wrapper = Wrappers.<Pet>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Pet::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Pet::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "ownerName")) {
                    wrapper.like(Pet::getOwnerName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Pet::getName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "color")) {
                    wrapper.like(Pet::getColor, "%" + (String) searchMap.get("selcontent") + "%");
                }
            }
            //分页
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        Page<Pet> petPage = petMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return petPage;
    }

    @Override
    public Pet selById(Integer id) {
        Pet pet=petMapper.selectById(id);
        return pet;
    }

    @Override
    public Boolean add(Pet pet) {
        int row = petMapper.insert(pet);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Pet pet) {
        int row = petMapper.updateById(pet);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            petMapper.deleteById(id);
        }
        return true;
    }
}
