package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Owner;

import java.util.List;
import java.util.Map;

public interface OwnerService {
//    查看所有
    public List<Owner> selAll();
//    分页查找
    public Page<Owner> selPage(Map searchMap);
    //    根据id查找
    public Owner selById(Integer id);
//    添加数据
    public Boolean add(Owner owner);
//    更新数据
    public Boolean update(Owner owner);
//    删除数据
    public Boolean del(List<Integer> ids);
    // 根据 buildingId 条件，查询全部记录
    public List<Owner> selectByBuildingid(Integer id);
}
