package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.House;
import com.example.community.domain.Lease;
import com.example.community.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/lease")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @GetMapping("/selall")
    public Result selall() {
        List<Lease> leaseList = leaseService.selAll();
        return new Result(true, 200, "查询成功！", leaseList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Lease> leasePage = leaseService.selPage(searchMap);
        return new PageResult(true, 200, "分页查询成功！", leasePage.getRecords(), leasePage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id) {
        Lease lease = leaseService.selById(id);
        return new Result(true, 200, "查询" + id + "内容成功!", lease);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Lease lease) {
        Boolean add = leaseService.add(lease);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Lease lease) {
        Boolean update = leaseService.update(lease);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = leaseService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

    @GetMapping("/selbyhouseid{id}")
    public Result selByhouseId(Integer id) {
        List<Lease> leaseList = leaseService.selectByhouseId(id);
        return new Result(true, 200, "查询成功！", leaseList);
    }

    @GetMapping("/selbyownerid{id}")
    public Result selByownerId(Integer id) {
        List<Lease> leaseList = leaseService.selectByownerId(id);
        return new Result(true, 200, "查询成功！", leaseList);
    }

}
