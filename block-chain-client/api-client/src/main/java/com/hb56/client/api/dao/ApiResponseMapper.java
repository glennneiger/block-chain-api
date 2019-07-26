package com.hb56.client.api.dao;

import java.util.List;

import com.hb56.block.model.api.ApiResponse;
import com.hb56.block.model.api.ApiResponseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiResponseMapper {

    int countByExample(ApiResponseExample example);

    int deleteByExample(ApiResponseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApiResponse record);

    int insertSelective(ApiResponse record);

    List<ApiResponse> selectByExample(ApiResponseExample example);

    ApiResponse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApiResponse record, @Param("example") ApiResponseExample example);

    int updateByExample(@Param("record") ApiResponse record, @Param("example") ApiResponseExample example);

    int updateByPrimaryKeySelective(ApiResponse record);

    int updateByPrimaryKey(ApiResponse record);

    int insertBatch(List<ApiResponse> apiResponseList);

    void batchDelete(List<Long> ids);

    void updateMatchs(List<ApiResponse> apiResponses);
}