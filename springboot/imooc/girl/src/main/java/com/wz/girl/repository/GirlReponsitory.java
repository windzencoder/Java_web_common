package com.wz.girl.repository;

import com.wz.girl.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlReponsitory extends JpaRepository<Girl,Integer>{

    //通过年龄查询
    public List<Girl> findByAge(Integer age);

}
