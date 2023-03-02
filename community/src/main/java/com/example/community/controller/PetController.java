package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Pet;
import com.example.community.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/selall")
    public Result selall() {
        List<Pet> petList = petService.selAll();
        return new Result(true, 200, "查询成功！", petList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Pet> petPage=petService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",petPage.getRecords(),petPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Pet pet=petService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",pet);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Pet pet) {
        Boolean add = petService.add(pet);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Pet pet) {
        Boolean update = petService.update(pet);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = petService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
