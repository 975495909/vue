package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Activity;
import com.example.community.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/selall")
    public Result selall() {
        List<Activity> activityList = activityService.selAll();
        return new Result(true, 200, "查询成功！", activityList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Activity> activityPage=activityService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",activityPage.getRecords(),activityPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Activity activity=activityService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",activity);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Activity activity) {
        Boolean add = activityService.add(activity);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Activity activity) {
        Boolean update = activityService.update(activity);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = activityService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
