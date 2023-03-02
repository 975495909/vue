package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.ChargeType;
import com.example.community.service.ChargeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/chargetype")
public class ChargeTypeController {

    @Autowired
    private ChargeTypeService chargetypeService;

    @GetMapping("/selall")
    public Result selall(){
        List<ChargeType> chargetypeList=chargetypeService.selAll();
        return new Result(true,200,"查询成功！",chargetypeList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
        Page<ChargeType> chargetypePage=chargetypeService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",chargetypePage.getRecords(),chargetypePage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        ChargeType chargetype=chargetypeService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",chargetype);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ChargeType chargetype){
        Boolean add=chargetypeService.add(chargetype);
        return new Result(true,200,"添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody ChargeType chargetype){
        Boolean update=chargetypeService.update(chargetype);
        return new Result(true,200,"更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean del=chargetypeService.del(ids);
        return new Result(true,200,"删除成功！");
    }

}