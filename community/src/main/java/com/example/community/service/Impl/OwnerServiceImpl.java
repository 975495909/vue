package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.OwnerMapper;
import com.example.community.domain.Owner;
import com.example.community.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public List<Owner> selAll() {
        List<Owner> ownerList = ownerMapper.selectList(null);
        return ownerList;
    }

    @Override
    public Page<Owner> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Owner> wrapper = Wrappers.<Owner>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Owner::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Owner::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Owner::getName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "buildingName")) {
                    wrapper.like(Owner::getBuildingName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "houseNum")) {
                    wrapper.like(Owner::getHouseNum, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Owner> ownerPage = ownerMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return ownerPage;
    }

    @Override
    public Owner selById(Integer id) {
        Owner owner = ownerMapper.selectById(id);
        return owner;
    }

    @Override
    public Boolean add(Owner owner) {
        int row = ownerMapper.insert(owner);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Owner owner) {
        int row = ownerMapper.updateById(owner);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            ownerMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Owner> selectByBuildingid(Integer id) {
        LambdaQueryWrapper<Owner> wrapper = Wrappers.<Owner>lambdaQuery();
        if (id != null) {
            wrapper.eq(Owner::getBuildingId, id);
        }
        List<Owner> ownerList = ownerMapper.selectList(wrapper);
        return ownerList;
    }
}
