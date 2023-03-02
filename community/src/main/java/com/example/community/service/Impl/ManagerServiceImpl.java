package com.example.community.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ManagerMapper;
import com.example.community.domain.Manager;
import com.example.community.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> selAll() {
        List<Manager> managerList = managerMapper.selectList(null);
        return managerList;
    }

    @Override
    public Manager selOneUser(Map loginForm) {
        LambdaQueryWrapper<Manager> wrapper = Wrappers.<Manager>lambdaQuery();
        if (loginForm != null) {
            if (StringUtils.isNotEmpty((String) loginForm.get("username"))) {
                wrapper.eq(Manager::getLoginName, (String) loginForm.get("username"));
            }
        }
        Manager manager= managerMapper.selectOne(wrapper);
        return manager;
    }

    @Override
    public Manager selOnePassword(Map loginForm) {
        LambdaQueryWrapper<Manager> wrapper = Wrappers.<Manager>lambdaQuery();
        if (loginForm != null) {
            if (StringUtils.isNotEmpty((String) loginForm.get("username"))&&StringUtils.isNotEmpty((String) loginForm.get("password"))) {
                wrapper.eq(Manager::getLoginName, (String) loginForm.get("username"));
                wrapper.eq(Manager::getPassword, (String) loginForm.get("password"));
            }
        }
        Manager manager= managerMapper.selectOne(wrapper);
        return manager;
    }

    @Override
    public Page<Manager> selPage(Map searchMap) {
        //        初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        LambdaQueryWrapper<Manager> wrapper = Wrappers.<Manager>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Manager::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Manager::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "name")) {
                    wrapper.like(Manager::getName, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Manager> managerPage = managerMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return managerPage;
    }

    @Override
    public Manager selById(Integer id) {
        Manager manager = managerMapper.selectById(id);
        return manager;
    }

    @Override
    public Boolean add(Manager manager) {
        int row = managerMapper.insert(manager);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Manager manager) {
        int row = managerMapper.updateById(manager);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            managerMapper.deleteById(id);
        }
        return true;
    }
}
