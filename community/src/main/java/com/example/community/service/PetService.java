package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Pet;

import java.util.List;
import java.util.Map;

public interface PetService {
//    查看所有
    public List<Pet> selAll();
//    分页查找
    public Page<Pet> selPage(Map searchMap);
    //    根据id查找
    public Pet selById(Integer id);
//    添加数据
    public Boolean add(Pet pet);
//    更新数据
    public Boolean update(Pet pet);
//    删除数据
    public Boolean del(List<Integer> ids);
}
