<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="com.mrbean.mappers.userMapper">

    <!-- userVO와 매핑 -->
    <resultMap type="com.mrbean.user.userVO" id="memberMap">
        <id property="userId" column="u_userid"/> <!-- 기본키 매핑 -->
        <result property="userName" column="u_username"/>
        <result property="email" column="u_email"/>
        <result property="phoneNumber" column="u_phonenumber"/>
        <result property="password" column="u_passwordhash"/>
        <result property="role" column="u_roleenum"/>
        <result property="createdBy" column="u_createdby"/>
        <result property="createdAt" column="u_createat"/>
        <result property="updatedAt" column="u_updatedat"/>       
    </resultMap>

    <!-- 사용자 단일 조회 -->
    <select id="readUser" resultMap="memberMap">
        SELECT 
            u_userid, u_username, u_email, u_phonenumber, 
            u_passwordhash, u_roleenum, u_createdby, u_createat, u_updatedat
        FROM 
            users
        WHERE 
            u_userid = #{userId}
    </select>

    <!-- 사용자 목록 조회 -->
    <select id="readAllUsers" resultMap="memberMap">
        SELECT 
            u_userid, u_username, u_email, u_phonenumber, 
            u_passwordhash, u_roleenum, u_createdby, u_createat, u_updatedat
        FROM 
            users
    </select>

    <!-- 사용자 추가 -->
    <insert id="createUser" parameterType="com.mrbean.user.userVO">
        INSERT INTO users (u_userid, u_username, u_email, u_phonenumber, 
            u_passwordhash, u_roleenum, u_createdby, u_createat, u_updatedat)
        VALUES (#{userId}, #{userName}, #{email}, #{phoneNumber}, 
            #{password}, #{role}, #{createdBy}, #{createdAt}, #{updatedAt})
    </insert>

    <!-- 사용자 업데이트 -->
    <update id="updateUser" parameterType="com.mrbean.user.userVO">
        UPDATE users
        SET 
            u_username = #{userName},
            u_email = #{email},
            u_phonenumber = #{phoneNumber},
            u_passwordhash = #{password},
            u_roleenum = #{role},
            u_updatedat = #{updatedAt}
        WHERE 
            u_userid = #{userId}
    </update>

</mapper>