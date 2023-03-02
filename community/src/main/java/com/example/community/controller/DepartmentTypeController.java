package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.DepartmentType;
import com.example.community.service.DepartmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/departmenttype")
public class DepartmentTypeController {

    @Autowired
    private DepartmentTypeService departmentTypeService;

    @GetMapping("/selall")
    public Result selall() {
        List<DepartmentType> departmentTypeList = departmentTypeService.selAll();
        return new Result(true, 200, "查询成功！", departmentTypeList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<DepartmentType> departmentTypePage=departmentTypeService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",departmentTypePage.getRecords(),departmentTypePage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        DepartmentType departmentType=departmentTypeService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",departmentType);
    }

    @PostMapping("/add")
    public Result add(@RequestBody DepartmentType departmentType) {
        Boolean add = departmentTypeService.add(departmentType);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody DepartmentType departmentType) {
        Boolean update = departmentTypeService.update(departmentType);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = departmentTypeService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
