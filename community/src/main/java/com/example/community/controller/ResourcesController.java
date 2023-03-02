package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Resources;
import com.example.community.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    @GetMapping("/selall")
    public Result selall(){
        List<Resources> resourcesList=resourcesService.selAll();
        return new Result(true,200,"查询成功！",resourcesList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
        Page<Resources> resourcesPage=resourcesService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",resourcesPage.getRecords(),resourcesPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Resources resources=resourcesService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",resources);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Resources resources){
        Boolean add=resourcesService.add(resources);
        return new Result(true,200,"添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Resources resources){
        Boolean update=resourcesService.update(resources);
        return new Result(true,200,"更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean del=resourcesService.del(ids);
        return new Result(true,200,"删除成功！");
    }

}