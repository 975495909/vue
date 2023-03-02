package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.House;
import com.example.community.domain.Lease;

import java.util.List;
import java.util.Map;

public interface LeaseService {
    //    查看所有
    public List<Lease> selAll();

    //    分页查找
    public Page<Lease> selPage(Map searchMap);

    //    根据id查找
    public Lease selById(Integer id);

    //    添加数据
    public Boolean add(Lease lease);

    //    更新数据
    public Boolean update(Lease lease);

    //    删除数据
    public Boolean del(List<Integer> ids);

    // 根据 houseId 条件，查询全部记录
    public List<Lease> selectByhouseId(Integer id);

    // 根据 ownerId 条件，查询全部记录
    public List<Lease> selectByownerId(Integer id);
}
