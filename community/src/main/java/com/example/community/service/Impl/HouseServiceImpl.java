package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.HouseMapper;
import com.example.community.domain.House;
import com.example.community.domain.Owner;
import com.example.community.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public List<House> selAll() {
        List<House> houseList = houseMapper.selectList(null);
        return houseList;
    }

    @Override
    public Page<House> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<House> wrapper = Wrappers.<House>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(House::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(House::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "buildingName")) {
                    wrapper.like(House::getBuildingName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "ownerName")) {
                    wrapper.like(House::getOwnerName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<House> housePage = houseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return housePage;
    }

    @Override
    public House selById(Integer id) {
        House house=houseMapper.selectById(id);
        return house;
    }

    @Override
    public Boolean add(House house) {
        int row = houseMapper.insert(house);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(House house) {
        int row = houseMapper.updateById(house);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            houseMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<House> selectByBuildingid(Integer id) {
        LambdaQueryWrapper<House> wrapper = Wrappers.<House>lambdaQuery();
        if (id != null) {
            wrapper.eq(House::getBuildingId, id);
        }
        List<House> houseList = houseMapper.selectList(wrapper);
        return houseList;
    }
}
