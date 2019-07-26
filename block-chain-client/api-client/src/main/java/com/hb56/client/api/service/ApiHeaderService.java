package com.hb56.client.api.service;

import com.hb56.block.model.api.ApiHeader;
import com.hb56.block.model.api.ApiHeaderExample;
import com.hb56.client.api.dao.ApiHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-19-17:47
 * @company www.harbsoft.com
 */

@Service
public class ApiHeaderService {

    @Autowired
    ApiHeaderMapper apiHeaderMapper;

    /**
     * 根据id查询Api信息概述
     * @param apiId apiI
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ApiHeader> getApiHeader(Integer apiId) {
        ApiHeaderExample example = new ApiHeaderExample();
        ApiHeaderExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(apiId);
        return apiHeaderMapper.selectByExample(example);
    }

    public int deleteByApiId(Integer apiId) {
        ApiHeaderExample example = new ApiHeaderExample();
        ApiHeaderExample.Criteria criteria = example.createCriteria();
        criteria.andApiIdEqualTo(apiId);
        return apiHeaderMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveApiHeader(List<ApiHeader> apiHeaders) {
        apiHeaderMapper.insertBatch(apiHeaders);
    }
}