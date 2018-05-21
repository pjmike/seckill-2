package com.wizz.seckill.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "actmap")
public interface actMapper {

    @Select("SELECT TICKETS FROM ACTTBL WHERE ID = #{Id}")
    Long getTickets(@Param("Id")int Id);

    @Insert("")
    Long addActivity(@Param("Id") int id);

}
