package com.hb56.client.api.service;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.hb56.block.model.api.Api;
import com.hb56.block.model.api.ApiExample;
import com.hb56.client.api.dao.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-15-11:26
 * @company www.harbsoft.com
 */

@Service
public class ApiService {

    @Autowired
    private ApiMapper apiMapper;

    /**
     * 保存
     * @param api
     */
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor = Exception.class)
    public int saveApi(Api api) {
        return apiMapper.insertSelective(api);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor = Exception.class)
    public int deleteApi(Integer id) {
        ApiExample example = new ApiExample();
        ApiExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return apiMapper.deleteByExample(example);
    }

    /**
     * 更新
     * @param api
     * @return
     */
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor = Exception.class)
    public int updateApi(Api api) {
        return apiMapper.updateByPrimaryKey(api);
    }

    /**
     * 根据id查询Api
     * @param id apiI
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Api> getApi(Integer id) {
        ApiExample example = new ApiExample();
        ApiExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return apiMapper.selectByExample(example);
    }

    /**
     * 查询所有的api菜单列表
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Api> getApi() {
        ApiExample example = new ApiExample();
        return apiMapper.selectByExample(example);
    }

    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor = Exception.class)
    public int updateApiById(Integer id, String catId) {
        ApiExample apiExample = new ApiExample();
        ApiExample.Criteria criteria = apiExample.createCriteria();
        criteria.andIdEqualTo(id);
        Api api = new Api();
        api.setCatId(Integer.valueOf(catId));
        return apiMapper.updateByExampleSelective(api,apiExample);
    }
}