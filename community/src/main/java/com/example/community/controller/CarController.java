package com.example.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.community.common.PageResult;
import com.example.community.common.Result;
import com.example.community.domain.Car;
import com.example.community.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/selall")
    public Result selall() {
        List<Car> carList = carService.selAll();
        return new Result(true, 200, "查询成功！", carList);
    }

    @PostMapping("/selpage")
    public PageResult selpage(@RequestBody Map searchMap) {
//        System.out.println(searchMap);
        Page<Car> carPage=carService.selPage(searchMap);
        return new PageResult(true,200,"分页查询成功！",carPage.getRecords(),carPage.getTotal());
    }

    @GetMapping("/selbyid{id}")
    public Result selById(Integer id){
        Car car=carService.selById(id);
        return new Result(true,200,"查询"+id+"内容成功!",car);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Car car) {
        Boolean add = carService.add(car);
        return new Result(true, 200, "添加成功！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Car car) {
        Boolean update = carService.update(car);
        return new Result(true, 200, "更新成功！");
    }

    @PostMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean del = carService.del(ids);
        return new Result(true, 200, "删除成功！");
    }

}
