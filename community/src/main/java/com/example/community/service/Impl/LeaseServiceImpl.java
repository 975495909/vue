package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.LeaseMapper;
import com.example.community.domain.House;
import com.example.community.domain.Lease;
import com.example.community.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseMapper leaseMapper;

    @Override
    public List<Lease> selAll() {
        List<Lease> leaseList = leaseMapper.selectList(null);
        return leaseList;
    }

    @Override
    public Page<Lease> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Lease> wrapper = Wrappers.<Lease>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Lease::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Lease::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "buildingName")) {
                    wrapper.like(Lease::getBuildingName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "ownerName")) {
                    wrapper.like(Lease::getOwnerName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "salesmanName")) {
                    wrapper.like(Lease::getSalesmanName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "houseNum")) {
                    wrapper.like(Lease::getHouseNum, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Lease> leasePage = leaseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return leasePage;
    }

    @Override
    public Lease selById(Integer id) {
        Lease lease=leaseMapper.selectById(id);
        return lease;
    }

    @Override
    public Boolean add(Lease lease) {
        int row = leaseMapper.insert(lease);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Lease lease) {
        int row = leaseMapper.updateById(lease);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            leaseMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Lease> selectByhouseId(Integer id) {
        LambdaQueryWrapper<Lease> wrapper = Wrappers.<Lease>lambdaQuery();
        if (id != null) {
            wrapper.eq(Lease::getHouseId, id);
        }
        List<Lease> leaseList = leaseMapper.selectList(wrapper);
        return leaseList;
    }

    @Override
    public List<Lease> selectByownerId(Integer id) {
        LambdaQueryWrapper<Lease> wrapper = Wrappers.<Lease>lambdaQuery();
        if (id != null) {
            wrapper.eq(Lease::getOwnerId, id);
        }
        List<Lease> leaseList = leaseMapper.selectList(wrapper);
        return leaseList;
    }
}
