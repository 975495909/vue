package com.example.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.domain.Complaint;

import java.util.List;
import java.util.Map;

public interface ComplaintService {
//    查看所有
    public List<Complaint> selAll();
//    分页查找
    public Page<Complaint> selPage(Map searchMap);
    //    根据id查找
    public Complaint selById(Integer id);
//    添加数据
    public Boolean add(Complaint complaint);
//    更新数据
    public Boolean update(Complaint complaint);
//    删除数据
    public Boolean del(List<Integer> ids);
}
