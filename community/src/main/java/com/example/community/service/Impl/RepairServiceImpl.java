package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.RepairMapper;
import com.example.community.domain.Repair;
import com.example.community.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;

    @Override
    public List<Repair> selAll() {
        List<Repair> repairList = repairMapper.selectList(null);
        return repairList;
    }

    @Override
    public Page<Repair> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Repair> wrapper = Wrappers.<Repair>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Repair::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Repair::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
//                    wrapper.like(Repair::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Repair> repairPage = repairMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return repairPage;
    }

    @Override
    public Repair selById(Integer id) {
        Repair repair=repairMapper.selectById(id);
        return repair;
    }

    @Override
    public Boolean add(Repair repair) {
        int row = repairMapper.insert(repair);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Repair repair) {
        int row = repairMapper.updateById(repair);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            repairMapper.deleteById(id);
        }
        return true;
    }
}
