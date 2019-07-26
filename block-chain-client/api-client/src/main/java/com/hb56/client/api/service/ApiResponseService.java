package com.hb56.client.api.service;

import com.hb56.block.model.api.ApiResponse;
import com.hb56.block.model.api.ApiResponseExample;
import com.hb56.client.api.dao.ApiResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-19-11:17
 * @company www.harbsoft.com
 */

@Service
public class ApiResponseService {

    @Autowired
    ApiResponseMapper apiResponseMapper;

    /**
     * 根据id查询Api信息概述
     * @param apiId apiI
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ApiResponse> getApiResponseByApiId(Integer apiId) {
        ApiResponseExample example = new ApiResponseExample();
        ApiResponseExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(apiId);
        return apiResponseMapper.selectByExample(example);
    }

    /**
     * 批量插入
     * @param apiResponses
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveApiResponse(List<ApiResponse> apiResponses) {
        apiResponseMapper.insertBatch(apiResponses);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids){
       apiResponseMapper.batchDelete(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteByApiId(Integer api) {
        ApiResponseExample example = new ApiResponseExample();
        ApiResponseExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(api);
        return apiResponseMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBatch(List<ApiResponse> apiResponses) {
        apiResponseMapper.updateMatchs(apiResponses);
    }
}