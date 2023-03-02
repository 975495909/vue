package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.House;
import com.example.community.domain.Owner;
import com.example.community.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/selall")
    public Result selall() {
        List<House> houseList = houseService.selAll();
        return new Result(true, 200, "查询成功！", houseList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<House> housePage=houseService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",housePage.getRecords(),housePage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        House house=houseService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",house);
    }

    @PostMapping("/add")
    public Result add(@RequestBody House house) {
        Boolean add = houseService.add(house);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody House house) {
        Boolean update = houseService.update(house);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = houseService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

    @GetMapping("/selbybuildingid{id}")
    public Result selbybuildingid(Integer id) {
        List<House> ownerList = houseService.selectByBuildingid(id);
        return new Result(true, 200, "查询成功！", ownerList);
    }

}
