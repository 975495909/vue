package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ComplaintMapper;
import com.example.community.domain.Complaint;
import com.example.community.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public List<Complaint> selAll() {
        List<Complaint> complaintList = complaintMapper.selectList(null);
        return complaintList;
    }

    @Override
    public Page<Complaint> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Complaint> wrapper = Wrappers.<Complaint>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Complaint::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Complaint::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
//                    wrapper.like(Complaint::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Complaint> complaintPage = complaintMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return complaintPage;
    }

    @Override
    public Complaint selById(Integer id) {
        Complaint complaint=complaintMapper.selectById(id);
        return complaint;
    }

    @Override
    public Boolean add(Complaint complaint) {
        int row = complaintMapper.insert(complaint);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Complaint complaint) {
        int row = complaintMapper.updateById(complaint);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            complaintMapper.deleteById(id);
        }
        return true;
    }
}
