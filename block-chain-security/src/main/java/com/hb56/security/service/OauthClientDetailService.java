package com.hb56.security.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb56.security.dao.OauthClientDetailMapper;
import com.hb56.security.model.OauthClientDetail;
import com.hb56.security.model.OauthClientDetailExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Been
 * @date 2019/4/26
 */
@Service
public class OauthClientDetailService {
    @Autowired
    private OauthClientDetailMapper detailMapper;

    public PageInfo<OauthClientDetail> getClientDetails(Integer pageNum, Integer pageSize, String resourceIds) {
        OauthClientDetailExample example = new OauthClientDetailExample();
        if (StringUtils.isNotBlank(resourceIds)) {
            example.createCriteria().andResourceIdsLike("%" + resourceIds.trim() + "%");
        }
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        return new PageInfo<>(detailMapper.selectByExample(example));
    }

    public OauthClientDetail getClientDetail(String clientId) {
        return detailMapper.selectById(clientId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int saveClientDetail(OauthClientDetail clientDetail) {
        return detailMapper.insertSelective(clientDetail);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int removeClientDetail(String clientID) {
        OauthClientDetailExample example = new OauthClientDetailExample();
        example.createCriteria().andClientIdEqualTo(clientID);
        return detailMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateClientDetail(OauthClientDetail clientDetail) {
        OauthClientDetailExample example = new OauthClientDetailExample();
        example.createCriteria().andClientIdEqualTo(clientDetail.getClientId());
        return detailMapper.updateByExampleSelective(clientDetail, example);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateClientSecret(String clientId, String clientSecret) {
        return detailMapper.updateClientSecret(clientId, clientSecret);
    }
}
