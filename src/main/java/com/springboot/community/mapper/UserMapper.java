package com.springboot.community.mapper;

import com.springboot.community.dto.GithubUser;
import com.springboot.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM CITY WHERE state = #{state}")
    GithubUser findByState(@Param("state") String state);

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modifide) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModifide})")
    void insert(User user);

}