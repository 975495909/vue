package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Repair;

import java.util.List;
import java.util.Map;

public interface RepairService {
//    查看所有
    public List<Repair> selAll();
//    分页查找
    public Page<Repair> selPage(Map searchMap);
    //    根据id查找
    public Repair selById(Integer id);
//    添加数据
    public Boolean add(Repair repair);
//    更新数据
    public Boolean update(Repair repair);
//    删除数据
    public Boolean del(List<Integer> ids);
}
