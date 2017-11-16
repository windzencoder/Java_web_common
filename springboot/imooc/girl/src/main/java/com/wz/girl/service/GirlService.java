package com.wz.girl.service;

import com.wz.girl.domain.Girl;
import com.wz.girl.enums.ResultEnum;
import com.wz.girl.exception.GirlException;
import com.wz.girl.repository.GirlReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlReponsitory girlReponsitory;


    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlReponsitory.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(28);
        girlReponsitory.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl = girlReponsitory.findOne(id);
        Integer age = girl.getAge();
        if (age < 10){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 16){
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }


    /**
     * 通过id查询一个girl的信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlReponsitory.findOne(id);
    }

}
