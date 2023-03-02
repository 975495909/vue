package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.CarMapper;
import com.example.community.domain.Car;
import com.example.community.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> selAll() {
        List<Car> carList = carMapper.selectList(null);
        return carList;
    }

    @Override
    public Page<Car> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Car> wrapper = Wrappers.<Car>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Car::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Car::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "ownerName")) {
                    wrapper.like(Car::getOwnerName, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "carNum")) {
                    wrapper.like(Car::getCarNum, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "color")) {
                    wrapper.like(Car::getColor, "%" + (String) searchMap.get("selcontent") + "%");
                }
            }
            //分页
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        Page<Car> carPage = carMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return carPage;
    }

    @Override
    public Car selById(Integer id) {
        Car car=carMapper.selectById(id);
        return car;
    }

    @Override
    public Boolean add(Car car) {
        int row = carMapper.insert(car);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Car car) {
        int row = carMapper.updateById(car);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            carMapper.deleteById(id);
        }
        return true;
    }
}
