package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.DepartmentType;
import com.example.community.domain.Manpower;

import java.util.List;
import java.util.Map;

public interface DepartmentTypeService {
//    查看所有
    public List<DepartmentType> selAll();
//    分页查找
    public Page<DepartmentType> selPage(Map searchMap);
    //    根据id查找
    public DepartmentType selById(Integer id);
//    添加数据
    public Boolean add(DepartmentType departmentType);
//    更新数据
    public Boolean update(DepartmentType departmentType);
//    删除数据
    public Boolean del(List<Integer> ids);
}
