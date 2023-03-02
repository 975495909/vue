package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.PropertyMapper;
import com.example.community.domain.Lease;
import com.example.community.domain.Property;
import com.example.community.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> selAll() {
        List<Property> propertyList = propertyMapper.selectList(null);
        return propertyList;
    }

    @Override
    public Page<Property> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Property> wrapper = Wrappers.<Property>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Property::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Property::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "buildingName")) {
                    wrapper.like(Property::getBuildingName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "ownerName")) {
                    wrapper.like(Property::getOwnerName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "houseNum")) {
                    wrapper.like(Property::getHouseNum, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "propertyNum")) {
                    wrapper.like(Property::getPropertyNum, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Property> propertyPage = propertyMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return propertyPage;
    }

    @Override
    public Property selById(Integer id) {
        Property property = propertyMapper.selectById(id);
        return property;
    }

    @Override
    public Boolean add(Property property) {
        int row = propertyMapper.insert(property);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Property property) {
        int row = propertyMapper.updateById(property);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            propertyMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Property> selectByhouseId(Integer id) {
        LambdaQueryWrapper<Property> wrapper = Wrappers.<Property>lambdaQuery();
        if (id != null) {
            wrapper.eq(Property::getHouseId, id);
        }
        List<Property> propertyList = propertyMapper.selectList(wrapper);
        return propertyList;
    }

    @Override
    public List<Property> selectByownerId(Integer id) {
        LambdaQueryWrapper<Property> wrapper = Wrappers.<Property>lambdaQuery();
        if (id != null) {
            wrapper.eq(Property::getOwnerId, id);
        }
        List<Property> propertyList = propertyMapper.selectList(wrapper);
        return propertyList;
    }
}
