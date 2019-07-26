package com.hb56.client.api.dao;

import java.util.List;

import com.hb56.block.model.api.ApiHeader;
import com.hb56.block.model.api.ApiHeaderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Been
 */
@Repository
public interface ApiHeaderMapper {

    int countByExample(ApiHeaderExample example);

    int deleteByExample(ApiHeaderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiHeader record);

    int insertSelective(ApiHeader record);

    List<ApiHeader> selectByExample(ApiHeaderExample example);

    ApiHeader selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiHeader record, @Param("example") ApiHeaderExample example);

    int updateByExample(@Param("record") ApiHeader record, @Param("example") ApiHeaderExample example);

    int updateByPrimaryKeySelective(ApiHeader record);

    int updateByPrimaryKey(ApiHeader record);

    void insertBatch(List<ApiHeader> apiHeaders);
}