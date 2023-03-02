package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Charge;
import com.example.community.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @GetMapping("/selall")
    public Result selall(){
        List<Charge> chargeList=chargeService.selAll();
        return new Result(true,200,"查询成功！",chargeList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
        Page<Charge> chargePage=chargeService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",chargePage.getRecords(),chargePage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Charge charge=chargeService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",charge);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Charge charge){
        Boolean add=chargeService.add(charge);
        return new Result(true,200,"添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Charge charge){
        Boolean update=chargeService.update(charge);
        return new Result(true,200,"更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean del=chargeService.del(ids);
        return new Result(true,200,"删除成功！");
    }

}