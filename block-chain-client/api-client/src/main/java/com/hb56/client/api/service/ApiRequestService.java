package com.hb56.client.api.service;

import com.hb56.block.model.api.ApiRequest;
import com.hb56.block.model.api.ApiRequestExample;
import com.hb56.client.api.dao.ApiRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-18-8:57
 * @company www.harbsoft.com
 */

@Service
public class ApiRequestService {

    @Autowired
    ApiRequestMapper apiRequestMapper;

    /**
     * 根据id查询Api信息概述
     * @param apiId apiI
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ApiRequest> getApiRequestByApiId(Integer apiId) {
        ApiRequestExample example = new ApiRequestExample();
        ApiRequestExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(apiId);
        return apiRequestMapper.selectByExample(example);
    }

    /**
     * 根据pid查询该父节点下的所有子节点
     * @param pId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer getApiRequestByPid(Long pId) {
        ApiRequestExample example = new ApiRequestExample();
        ApiRequestExample.Criteria criteria = example.createCriteria();
        criteria.andPIdEqualTo(pId);
        return apiRequestMapper.selectByExample(example).size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteByApiId(Integer api) {
        ApiRequestExample example = new ApiRequestExample();
        ApiRequestExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(api);
        return apiRequestMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveApiResponse(List<ApiRequest> apiRequests) {
        apiRequestMapper.insertBatch(apiRequests);
    }
}