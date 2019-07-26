package com.hb56.client.api.dao;

import java.util.List;

import com.hb56.block.model.api.ApiCat;
import com.hb56.block.model.api.ApiCatExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Been
 */
@Repository
public interface ApiCatMapper {

    int countByExample(ApiCatExample example);

    int deleteByExample(ApiCatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiCat record);

    int insertSelective(ApiCat record);

    List<ApiCat> selectByExample(ApiCatExample example);

    ApiCat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiCat record, @Param("example") ApiCatExample example);

    int updateByExample(@Param("record") ApiCat record, @Param("example") ApiCatExample example);

    int updateByPrimaryKeySelective(ApiCat record);

    int updateByPrimaryKey(ApiCat record);
}