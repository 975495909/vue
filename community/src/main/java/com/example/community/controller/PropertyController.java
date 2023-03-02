package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Lease;
import com.example.community.domain.Property;
import com.example.community.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/selall")
    public Result selall() {
        List<Property> propertyList = propertyService.selAll();
        return new Result(true, 200, "查询成功！", propertyList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Property> propertyPage = propertyService.selPage(searchMap);
        return new PageResult(true, 200, "分页查询成功！", propertyPage.getRecords(), propertyPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id) {
        Property property = propertyService.selById(id);
        return new Result(true, 200, "查询" + id + "内容成功!", property);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Property property) {
        Boolean add = propertyService.add(property);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Property property) {
        Boolean update = propertyService.update(property);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = propertyService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

    @GetMapping("/selbyhouseid{id}")
    public Result selByhouseId(Integer id) {
        List<Property> propertyList = propertyService.selectByhouseId(id);
        return new Result(true, 200, "查询成功！", propertyList);
    }

    @GetMapping("/selbyownerid{id}")
    public Result selByownerId(Integer id) {
        List<Property> propertyList = propertyService.selectByownerId(id);
        return new Result(true, 200, "查询成功！", propertyList);
    }
}
