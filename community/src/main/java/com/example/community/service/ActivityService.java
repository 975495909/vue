package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
//    查看所有
    public List<Activity> selAll();
//    分页查找
    public Page<Activity> selPage(Map searchMap);
    //    根据id查找
    public Activity selById(Integer id);
//    添加数据
    public Boolean add(Activity activity);
//    更新数据
    public Boolean update(Activity activity);
//    删除数据
    public Boolean del(List<Integer> ids);
}
