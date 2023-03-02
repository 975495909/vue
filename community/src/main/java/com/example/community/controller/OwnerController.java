package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Owner;
import com.example.community.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/selall")
    public Result selall() {
        List<Owner> ownerList = ownerService.selAll();
        return new Result(true, 200, "查询成功！", ownerList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Owner> ownerPage=ownerService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",ownerPage.getRecords(),ownerPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Owner owner=ownerService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",owner);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Owner owner) {
        Boolean add = ownerService.add(owner);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Owner owner) {
        Boolean update = ownerService.update(owner);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = ownerService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

    @GetMapping("/selbybuildingid{id}")
    public Result selbybuildingid(Integer id) {
        List<Owner> ownerList = ownerService.selectByBuildingid(id);
        return new Result(true, 200, "查询成功！", ownerList);
    }
}
