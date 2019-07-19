package com.hb56.security.dao;

import java.util.List;

import com.hb56.security.model.OauthClientDetail;
import com.hb56.security.model.OauthClientDetailExample;
import org.apache.ibatis.annotations.Param;

/**
 * @author Been
 */
public interface OauthClientDetailMapper {
    int countByExample(OauthClientDetailExample example);

    int deleteByExample(OauthClientDetailExample example);

    int insert(OauthClientDetail record);

    int insertSelective(OauthClientDetail record);

    List<OauthClientDetail> selectByExample(OauthClientDetailExample example);

    OauthClientDetail selectById(String clientId);

    int updateByExampleSelective(@Param("record") OauthClientDetail record, @Param("example") OauthClientDetailExample example);

    int updateByExample(@Param("record") OauthClientDetail record, @Param("example") OauthClientDetailExample example);

    int updateClientSecret(@Param("clientId") String clientId, @Param("clientSecret") String clientSecret);
}