package com.wizz.seckill.Mapper;

import com.wizz.seckill.Model.reqInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "infomap")
public interface reqInfoMapper {
    @Insert("INSERT IGNORE  INTO `REQTBL` (STATE, PHONENUM , STUID, STUNAME) VALUES (#{state}, #{phoneNum}, #{stuId}, #{stuName})")
    void updateInfo(@Param("state")int state, @Param("phoneNum")String phoneNum,
                    @Param("stuId")String stuId, @Param("stuName")String stuName);

    @Select("SELECT COUNT(1) FROM REQTBL WHERE PHONENUM = #{phoneNum}")
    int isDuplicatePhoneNum(@Param("phoneNum") String phoneNum);

    @Select("SELECT COUNT(1) FROM REQTBL WHERE STATE = 0")
    Long getReqSum();

    @Select("SELECT * FROM REQTBL WHERE STATE = 0")
    List<reqInfo> getResult();
}
