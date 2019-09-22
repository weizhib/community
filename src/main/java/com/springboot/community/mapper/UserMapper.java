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

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token")String token);
}