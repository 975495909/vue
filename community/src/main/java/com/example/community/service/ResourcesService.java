package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Resources;

import java.util.List;
import java.util.Map;

public interface ResourcesService {
    //    查看所有
    public List<Resources> selAll();
    //    分页查找
    public Page<Resources> selPage(Map searchMap);
    //    根据id查找
    public Resources selById(Integer id);
    //    添加数据
    public Boolean add(Resources resources);
    //    更新数据
    public Boolean update(Resources resources);
    //    删除数据
    public Boolean del(List<Integer> ids);
}
