package com.example.community.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ManpowerMapper;
import com.example.community.domain.DepartmentType;
import com.example.community.domain.Manpower;
import com.example.community.service.ManpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ManpowerServiceImpl implements ManpowerService {

    @Autowired
    private ManpowerMapper manpowerMapper;

    @Override
    public List<Manpower> selAll() {
        List<Manpower> manpowerList = manpowerMapper.selectList(null);
        return manpowerList;
    }

    @Override
    public Page<Manpower> selPage(Map searchMap) {
        //        初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        LambdaQueryWrapper<Manpower> wrapper = Wrappers.<Manpower>lambdaQuery();
        if (searchMap != null) {
//            System.out.println(searchMap);
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Manpower::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Manpower::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "departmentName")) {
                    wrapper.like(Manpower::getDepartmentName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Manpower::getName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "company")) {
                    wrapper.like(Manpower::getCompany, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Manpower> manpowerPage = manpowerMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return manpowerPage;
    }

    @Override
    public Manpower selById(Integer id) {
        Manpower manpower = manpowerMapper.selectById(id);
        return manpower;
    }

    @Override
    public Boolean add(Manpower manpower) {
        int row = manpowerMapper.insert(manpower);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Manpower manpower) {
        int row = manpowerMapper.updateById(manpower);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            manpowerMapper.deleteById(id);
        }
        return true;
    }
}
