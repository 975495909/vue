package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    //    查看所有
    public List<Manager> selAll();
    //登录
    public Manager selOneUser(Map loginForm);
    public Manager selOnePassword(Map loginForm);
    //    分页查找
    public Page<Manager> selPage(Map searchMap);
    //    根据id查找
    public Manager selById(Integer id);
    //    添加数据
    public Boolean add(Manager manager);
    //    更新数据
    public Boolean update(Manager manager);
    //    删除数据
    public Boolean del(List<Integer> ids);
}
