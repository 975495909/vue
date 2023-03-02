package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.DepartmentType;
import com.example.community.domain.Manpower;
import com.example.community.service.ManpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/manpower")
public class ManpowerController {

    @Autowired
    private ManpowerService manpowerService;

    @GetMapping("/selall")
    public Result selall(){
        List<Manpower> manpowerList=manpowerService.selAll();
        return new Result(true,200,"查询成功！",manpowerList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
        Page<Manpower> manpowerPage=manpowerService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",manpowerPage.getRecords(),manpowerPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Manpower manpower=manpowerService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",manpower);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manpower manpower){
        Boolean add=manpowerService.add(manpower);
        return new Result(true,200,"添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Manpower manpower){
        Boolean update=manpowerService.update(manpower);
        return new Result(true,200,"更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean del=manpowerService.del(ids);
        return new Result(true,200,"删除成功！");
    }

}