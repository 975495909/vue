package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Building;
import com.example.community.domain.Owner;

import java.util.List;
import java.util.Map;

public interface BuildingService {
//    查看所有
    public List<Building> selAll();
//    分页查找
    public Page<Building> selPage(Map searchMap);
    //    根据id查找
    public Building selById(Integer id);
//    添加数据
    public Boolean add(Building building);
//    更新数据
    public Boolean update(Building building);
//    删除数据
    public Boolean del(List<Integer> ids);

}
