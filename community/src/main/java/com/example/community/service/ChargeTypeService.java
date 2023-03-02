package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.ChargeType;

import java.util.List;
import java.util.Map;

public interface ChargeTypeService {
    //    查看所有
    public List<ChargeType> selAll();
    //    分页查找
    public Page<ChargeType> selPage(Map searchMap);
    //    根据id查找
    public ChargeType selById(Integer id);
    //    添加数据
    public Boolean add(ChargeType chargetype);
    //    更新数据
    public Boolean update(ChargeType chargetype);
    //    删除数据
    public Boolean del(List<Integer> ids);
}
