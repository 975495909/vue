package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Lease;
import com.example.community.domain.Property;

import java.util.List;
import java.util.Map;

public interface PropertyService {
    //    查看所有
    public List<Property> selAll();

    //    分页查找
    public Page<Property> selPage(Map searchMap);

    //    根据id查找
    public Property selById(Integer id);

    //    添加数据
    public Boolean add(Property property);

    //    更新数据
    public Boolean update(Property property);

    //    删除数据
    public Boolean del(List<Integer> ids);

    // 根据 houseId 条件，查询全部记录
    public List<Property> selectByhouseId(Integer id);// 根据 houseId 条件，查询全部记录

    // 根据 ownerId 条件，查询全部记录
    public List<Property> selectByownerId(Integer id);
}
