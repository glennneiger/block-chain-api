package com.hb56.client.api.dao;

import java.util.List;

import com.hb56.block.model.api.ApiRequest;
import com.hb56.block.model.api.ApiRequestExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Been
 */
@Repository
public interface ApiRequestMapper {

    int countByExample(ApiRequestExample example);

    int deleteByExample(ApiRequestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApiRequest record);

    int insertSelective(ApiRequest record);

    List<ApiRequest> selectByExample(ApiRequestExample example);

    ApiRequest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApiRequest record, @Param("example") ApiRequestExample example);

    int updateByExample(@Param("record") ApiRequest record, @Param("example") ApiRequestExample example);

    int updateByPrimaryKeySelective(ApiRequest record);

    int updateByPrimaryKey(ApiRequest record);

    void insertBatch(List<ApiRequest> apiRequests);
}