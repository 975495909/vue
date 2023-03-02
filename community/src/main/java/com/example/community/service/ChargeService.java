package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Charge;

import java.util.List;
import java.util.Map;

public interface ChargeService {
    //    查看所有
    public List<Charge> selAll();
    //    分页查找
    public Page<Charge> selPage(Map searchMap);
    //    根据id查找
    public Charge selById(Integer id);
    //    添加数据
    public Boolean add(Charge charge);
    //    更新数据
    public Boolean update(Charge charge);
    //    删除数据
    public Boolean del(List<Integer> ids);
}
