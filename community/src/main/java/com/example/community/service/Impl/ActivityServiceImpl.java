package com.example.community.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.dao.ActivityMapper;
import com.example.community.domain.Activity;
import com.example.community.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> selAll() {
        List<Activity> activityList = activityMapper.selectList(null);
        return activityList;
    }

    @Override
    public Page<Activity> selPage(Map searchMap) {
//        初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery();
        if (searchMap != null) {
            //时间区间
            if (StringUtils.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge(Activity::getCreateTime, searchMap.get("startTime"));
            }
            if (StringUtils.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le(Activity::getCreateTime, searchMap.get("endTime"));
            }
            //模糊搜索
            if (StringUtils.isNotEmpty((String) searchMap.get("sel")) && StringUtils.isNotEmpty((String) searchMap.get("selcontent"))) {
                if (StringUtils.equals((String) searchMap.get("sel"), "title")) {
                    wrapper.like(Activity::getTitle, "%" + (String) searchMap.get("selcontent") + "%");
                }
                if (StringUtils.equals((String) searchMap.get("sel"), "organizer")) {
                    wrapper.like(Activity::getOrganizer, "%" + (String) searchMap.get("selcontent") + "%");
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
        Page<Activity> activityPage = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return activityPage;
    }

    @Override
    public Activity selById(Integer id) {
        Activity activity=activityMapper.selectById(id);
        return activity;
    }

    @Override
    public Boolean add(Activity activity) {
        int row = activityMapper.insert(activity);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean update(Activity activity) {
        int row = activityMapper.updateById(activity);
        if (row > 0) return true;
        else return false;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            activityMapper.deleteById(id);
        }
        return true;
    }
}
