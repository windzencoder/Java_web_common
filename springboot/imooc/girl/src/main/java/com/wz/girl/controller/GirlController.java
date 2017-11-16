package com.wz.girl.controller;


import com.wz.girl.domain.Girl;
import com.wz.girl.domain.Result;
import com.wz.girl.repository.GirlReponsitory;
import com.wz.girl.service.GirlService;
import com.wz.girl.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlReponsitory girlReponsitory;

    @Autowired
    private GirlService girlService;

    /**
     * 测试事务
     */
    @RequestMapping(value = "/girls/two")
    public void insertTwo(){
        girlService.insertTwo();
    }

    /**
     * 查询列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlReponsitory.findAll();
    }

    /**
     * 添加Girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girlReponsitory.save(girl);
        return ResultUtil.success(girlReponsitory.save(girl));
    }

    /**
     * 查询一个Girl
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlReponsitory.findOne(id);
    }

    /**
     * 更新一个Girl
     * @param id
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam(value = "cupSize", required = false) String cupSize,
                           @RequestParam(value = "age", required = false) Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlReponsitory.save(girl);
    }

    /**
     * 删除一个Girl
     * @param id
     * @return
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlReponsitory.delete(id);
    }

    //通过年龄查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age){
        return girlReponsitory.findByAge(age);
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws  Exception{
        girlService.getAge(id);
    }

}
