package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Building;
import com.example.community.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/selall")
    public Result selall() {
        List<Building> buildingList = buildingService.selAll();
        return new Result(true, 200, "查询成功！", buildingList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Building> buildingPage=buildingService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",buildingPage.getRecords(),buildingPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Building building=buildingService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",building);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Building building) {
        Boolean add = buildingService.add(building);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Building building) {
        Boolean update = buildingService.update(building);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = buildingService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
