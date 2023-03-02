package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Repair;
import com.example.community.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping("/selall")
    public Result selall() {
        List<Repair> repairList = repairService.selAll();
        return new Result(true, 200, "查询成功！", repairList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Repair> repairPage=repairService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",repairPage.getRecords(),repairPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Repair repair=repairService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",repair);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Repair repair) {
        Boolean add = repairService.add(repair);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Repair repair) {
        Boolean update = repairService.update(repair);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = repairService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
