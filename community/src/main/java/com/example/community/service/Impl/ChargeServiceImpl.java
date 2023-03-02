package com.example.community.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ChargeMapper;
import com.example.community.domain.Charge;
import com.example.community.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public List<Charge> selAll() {
        List<Charge> chargeList = chargeMapper.selectList(null);
        return chargeList;
    }

    @Override
    public Page<Charge> selPage(Map searchMap) {
        //        初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        LambdaQueryWrapper<Charge> wrapper = Wrappers.<Charge>lambdaQuery();
        if (searchMap != null) {
//            System.out.println(searchMap);
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Charge::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Charge::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "chargeName")) {
                    wrapper.like(Charge::getChargeName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Charge::getName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "contrator")) {
                    wrapper.like(Charge::getContrator, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Charge> chargePage = chargeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return chargePage;
    }

    @Override
    public Charge selById(Integer id) {
        Charge charge = chargeMapper.selectById(id);
        return charge;
    }

    @Override
    public Boolean add(Charge charge) {
        int row = chargeMapper.insert(charge);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Charge charge) {
        int row = chargeMapper.updateById(charge);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            chargeMapper.deleteById(id);
        }
        return true;
    }
}
