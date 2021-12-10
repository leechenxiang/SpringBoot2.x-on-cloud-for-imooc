package com.imooc.controller;

import com.imooc.dao.StuDao;
import com.imooc.entity.StudentMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequestMapping("mongo")
@RestController
public class MongoController {

    @Autowired
    private StuDao stuDao;

    @GetMapping("create")
    public String create(){
        StudentMO stu = new StudentMO("1001", "Jack", "男", 18);
        stuDao.save(stu);
        return "OK~";
    }

    @GetMapping("get")
    public Object get(String id){
        Optional<StudentMO> op = stuDao.findById(id);
        return op.get();
    }

    @GetMapping("update")
    public String update(){
        StudentMO stu = new StudentMO("1001", "LiLei", "女", 22);
        stuDao.save(stu);
        return "OK";
    }

    @GetMapping("delete")
    public String delete(String id){
        stuDao.deleteById(id);
        return "OK";
    }
}
