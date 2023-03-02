package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Complaint;
import com.example.community.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/selall")
    public Result selall() {
        List<Complaint> complaintList = complaintService.selAll();
        return new Result(true, 200, "查询成功！", complaintList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Complaint> complaintPage=complaintService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",complaintPage.getRecords(),complaintPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Complaint complaint=complaintService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",complaint);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Complaint complaint) {
        Boolean add = complaintService.add(complaint);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Complaint complaint) {
        Boolean update = complaintService.update(complaint);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = complaintService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
