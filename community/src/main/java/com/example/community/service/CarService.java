package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Car;

import java.util.List;
import java.util.Map;

public interface CarService {
//    查看所有
    public List<Car> selAll();
//    分页查找
    public Page<Car> selPage(Map searchMap);
    //    根据id查找
    public Car selById(Integer id);
//    添加数据
    public Boolean add(Car car);
//    更新数据
    public Boolean update(Car car);
//    删除数据
    public Boolean del(List<Integer> ids);
}
