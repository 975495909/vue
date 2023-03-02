package com.example.community.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ResourcesMapper;
import com.example.community.domain.Resources;
import com.example.community.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public List<Resources> selAll() {
        List<Resources> resourcesList = resourcesMapper.selectList(null);
        return resourcesList;
    }

    @Override
    public Page<Resources> selPage(Map searchMap) {
        //        初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        LambdaQueryWrapper<Resources> wrapper = Wrappers.<Resources>lambdaQuery();
        if (searchMap != null) {
//            System.out.println(searchMap);
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Resources::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Resources::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {

                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                   wrapper.like(Resources::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Resources> resourcesPage = resourcesMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return resourcesPage;
    }

    @Override
    public Resources selById(Integer id) {
        Resources resources = resourcesMapper.selectById(id);
        return resources;
    }

    @Override
    public Boolean add(Resources resources) {
        int row = resourcesMapper.insert(resources);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Resources resources) {
        int row = resourcesMapper.updateById(resources);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            resourcesMapper.deleteById(id);
        }
        return true;
    }
}
