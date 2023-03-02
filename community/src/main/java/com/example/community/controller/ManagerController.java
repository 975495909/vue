package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Manager;
import com.example.community.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/selall")
    public Result selall(){
        List<Manager> managerList=managerService.selAll();
        return new Result(true,200,"查询成功！",managerList);
    }

    @PostMapping("/selone")
    public Result selOne(@RequestBody Map loginForm){
        Manager manager=managerService.selOneUser(loginForm);
        Manager manager1=managerService.selOnePassword(loginForm);
        if (manager!=null) {
            if (manager1!=null) return new Result(true,200,"登录成功！",manager1);
            else return new Result(true,-1,"密码错误！");
        }
        else {
            return new Result(true,-1,"用户不存在");
        }
    }

    @PostMapping("/selpic")
    public Result selPic(@RequestBody Map loginForm){
        Manager manager=managerService.selOneUser(loginForm);
        return new Result(true,200,"查询头像成功！",manager);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
        Page<Manager> managerPage=managerService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",managerPage.getRecords(),managerPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Manager manager=managerService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",manager);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Manager manager){
        Boolean add=managerService.add(manager);
        return new Result(true,200,"添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Manager manager){
        Boolean update=managerService.update(manager);
        return new Result(true,200,"更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean del=managerService.del(ids);
        return new Result(true,200,"删除成功！");
    }

}