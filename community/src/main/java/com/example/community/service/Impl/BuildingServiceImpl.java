package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.BuildingMapper;
import com.example.community.domain.Building;
import com.example.community.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public List<Building> selAll() {
        List<Building> buildingList = buildingMapper.selectList(null);
        return buildingList;
    }

    @Override
    public Page<Building> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Building> wrapper = Wrappers.<Building>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Building::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Building::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Building::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Building> buildingPage = buildingMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return buildingPage;
    }

    @Override
    public Building selById(Integer id) {
        Building building=buildingMapper.selectById(id);
        return building;
    }

    @Override
    public Boolean add(Building building) {
        int row = buildingMapper.insert(building);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Building building) {
        int row = buildingMapper.updateById(building);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            buildingMapper.deleteById(id);
        }
        return true;
    }
}
