package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.DepartmentType;
import com.example.community.domain.Manpower;

import java.util.List;
import java.util.Map;

public interface ManpowerService {
    //    查看所有
    public List<Manpower> selAll();
    //    分页查找
    public Page<Manpower> selPage(Map searchMap);
    //    根据id查找
    public Manpower selById(Integer id);
    //    添加数据
    public Boolean add(Manpower manpower);
    //    更新数据
    public Boolean update(Manpower manpower);
    //    删除数据
    public Boolean del(List<Integer> ids);
}
