package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.House;
import com.example.community.domain.Owner;

import java.util.List;
import java.util.Map;

public interface HouseService {
//    查看所有
    public List<House> selAll();
//    分页查找
    public Page<House> selPage(Map searchMap);
    //    根据id查找
    public House selById(Integer id);
//    添加数据
    public Boolean add(House house);
//    更新数据
    public Boolean update(House house);
//    删除数据
    public Boolean del(List<Integer> ids);
    // 根据 buildingId 条件，查询全部记录
    public List<House> selectByBuildingid(Integer id);
}
