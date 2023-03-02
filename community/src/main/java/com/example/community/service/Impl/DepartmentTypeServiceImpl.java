package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.DepartmentTypeMapper;
import com.example.community.domain.DepartmentType;
import com.example.community.service.DepartmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DepartmentTypeServiceImpl implements DepartmentTypeService {

    @Autowired
    private DepartmentTypeMapper departmentTypeMapper;

    @Override
    public List<DepartmentType> selAll() {
        List<DepartmentType> departmentTypeList = departmentTypeMapper.selectList(null);
        return departmentTypeList;
    }

    @Override
    public Page<DepartmentType> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<DepartmentType> wrapper = Wrappers.<DepartmentType>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(DepartmentType::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(DepartmentType::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(DepartmentType::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<DepartmentType> departmentTypePage = departmentTypeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return departmentTypePage;
    }

    @Override
    public DepartmentType selById(Integer id) {
        DepartmentType departmentType=departmentTypeMapper.selectById(id);
        return departmentType;
    }

    @Override
    public Boolean add(DepartmentType departmentType) {
        int row = departmentTypeMapper.insert(departmentType);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(DepartmentType departmentType) {
        int row = departmentTypeMapper.updateById(departmentType);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            departmentTypeMapper.deleteById(id);
        }
        return true;
    }
}
