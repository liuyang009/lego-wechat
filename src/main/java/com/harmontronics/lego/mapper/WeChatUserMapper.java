package com.harmontronics.lego.mapper;

import com.harmontronics.lego.entity.WeChatUser;
import org.apache.ibatis.annotations.*;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-14 9:34
 */

@Mapper
public interface WeChatUserMapper {

    @Select("SELECT * FROM tb_account where id = #{id}")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="openid", property="openid"),
            @Result(column="nickname", property="nickname"),
            @Result(column="headimgurl", property="headimgurl"),
            @Result(column="sex", property="sex"),
            @Result(column="balance", property="balance"),
            @Result(column="create_date", property="createDate")
    })
    WeChatUser findById(@Param("id") long id);

    @Update("update tb_account set balance = #{balance} where id = #{id}")
    void update(WeChatUser weChatUser);

    @Select("SELECT * FROM tb_account where openid = #{openId}")
    @Results({
            @Result(column="id", property="id", id=true),
            @Result(column="openid", property="openid"),
            @Result(column="nickname", property="nickname"),
            @Result(column="headimgurl", property="headimgurl"),
            @Result(column="sex", property="sex"),
            @Result(column="balance", property="balance"),
            @Result(column="create_date", property="createDate")
    })
    WeChatUser findByOpenId(String openId);


    @Insert({
            "insert into tb_account (openid, nickname, ",
            "headimgurl, sex, ",
            "balance, create_date)",
            "values (#{openid}, #{nickname}, ",
            "#{headimgurl}, #{sex}, ",
            "#{balance}, #{createDate})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(WeChatUser user);
}
