package com.example.community.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ChargeTypeMapper;
import com.example.community.domain.ChargeType;
import com.example.community.service.ChargeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ChargeTypeServiceImpl implements ChargeTypeService {

    @Autowired
    private ChargeTypeMapper chargetypeMapper;

    @Override
    public List<ChargeType> selAll() {
        List<ChargeType> chargetypeList = chargetypeMapper.selectList(null);
        return chargetypeList;
    }

    @Override
    public Page<ChargeType> selPage(Map searchMap) {
        //        初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        LambdaQueryWrapper<ChargeType> wrapper = Wrappers.<ChargeType>lambdaQuery();
        if (searchMap != null) {
//            System.out.println(searchMap);
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(ChargeType::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(ChargeType::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {

                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(ChargeType::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<ChargeType> chargetypePage = chargetypeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return chargetypePage;
    }

    @Override
    public ChargeType selById(Integer id) {
        ChargeType chargetype = chargetypeMapper.selectById(id);
        return chargetype;
    }

    @Override
    public Boolean add(ChargeType chargetype) {
        int row = chargetypeMapper.insert(chargetype);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(ChargeType chargetype) {
        int row = chargetypeMapper.updateById(chargetype);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            chargetypeMapper.deleteById(id);
        }
        return true;
    }
}
