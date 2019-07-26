package com.hb56.client.api.service;

import com.hb56.block.model.api.Api;
import com.hb56.block.model.api.ApiCat;
import com.hb56.block.model.api.ApiCatExample;
import com.hb56.block.model.api.ApiExample;
import com.hb56.block.model.api.ApiMenu;
import com.hb56.client.api.dao.ApiCatMapper;
import com.hb56.client.api.dao.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-18-15:06
 * @company www.harbsoft.com
 */

@Service
public class ApiCatService {

    @Autowired
    ApiCatMapper apiCatMapper;
    @Autowired
    ApiMapper apiMapper;

    public List<ApiMenu> getApiMenu2() {
        List<ApiMenu> apiMenuList = new ArrayList<>();

        ApiCatExample example = new ApiCatExample();
        ApiCatExample.Criteria apiCatCriteria = example.createCriteria();
        apiCatCriteria.andCatPidEqualTo(0);
        /*遍历文件夹*/
        List<ApiCat> apiCatList = apiCatMapper.selectByExample(example);
        /*一级文件夹*/
        for (ApiCat apiCat : apiCatList) {
            ApiMenu apiMenu = new ApiMenu();
            apiMenu.setType("submenu");
            apiMenu.setTitle(apiCat.getApiCatName());
            apiMenu.setApiId(0);
            apiMenu.setCatId(apiCat.getId().toString());
            apiMenu.setName("");
            apiMenu.setParentId("0");
            /*二级文件夹*/
            ApiCatExample example2 = new ApiCatExample();
            ApiCatExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andCatPidEqualTo(apiCat.getId());
            List<ApiCat> apiCatList2 = apiCatMapper.selectByExample(example2);
            if (apiCatList2.size() > 0) {
                List<ApiMenu> apiMenusub2 = new ArrayList<>();
                for (ApiCat apiCat2 : apiCatList2) {
                    ApiMenu apiMenu2 = new ApiMenu();
                    apiMenu2.setType("submenu");
                    apiMenu2.setTitle(apiCat2.getApiCatName());
                    apiMenu2.setApiId(0);
                    apiMenu2.setCatId(apiCat2.getId().toString());
                    apiMenu2.setName("");
                    apiMenu2.setParentId(apiCat2.getCatPid().toString());
                    apiMenusub2.add(apiMenu2);
                }
                apiMenu.setChildren(apiMenusub2);
            }
            /*没有2级文件夹， 2级为文件*/
            ApiExample apiexample = new ApiExample();
            ApiExample.Criteria criteria = apiexample.createCriteria();
            criteria.andCatIdEqualTo(apiCat.getId());
            /*判断文件夹下面是否有文件*/
            List<Api> apiList2 = apiMapper.selectByExample(apiexample);
            if (apiList2.size() > 0) {
                List<ApiMenu> apiMenusub = new ArrayList<>();
                for (Api api : apiList2) {
                    ApiMenu item = new ApiMenu();
                    item.setType("menu-item");
                    item.setTitle(api.getApiName());
                    item.setApiId(api.getId());
                    item.setName(api.getUrl());
                    item.setParentId(api.getCatId().toString());
                    apiMenusub.add(item);
                }
                apiMenu.setChildren(apiMenusub);
            }
            apiMenuList.add(apiMenu);
        }

        ApiExample apiExample = new ApiExample();
        ApiExample.Criteria apiCriteria = apiExample.createCriteria();
        apiCriteria.andCatIdEqualTo(0);
        List<Api> apiList = apiMapper.selectByExample(apiExample);
        for (Api api : apiList) {
            ApiMenu item = new ApiMenu();
            item.setType("menu-item");
            item.setTitle(api.getApiName());
            item.setCatId(api.getCatId().toString());
            item.setApiId(api.getId());
            item.setName(api.getCatId().toString());
            item.setParentId("0");
            apiMenuList.add(item);
        }
        return apiMenuList;
    }

    public List<ApiMenu> getApiMenu() {
        List<ApiMenu> apiMenuList = new ArrayList<>();
        ApiCatExample example = new ApiCatExample();
        ApiCatExample.Criteria apiCatCriteria = example.createCriteria();
        apiCatCriteria.andCatPidEqualTo(0);
        /*遍历文件夹*/
        List<ApiCat> apiCatList = apiCatMapper.selectByExample(example);
        /*根目录*/
        for (ApiCat apiCat : apiCatList) {
            ApiMenu apiMenu = new ApiMenu();
            apiMenu.setType("submenu");
            apiMenu.setTitle(apiCat.getApiCatName());
            apiMenu.setApiId(0);
            apiMenu.setCatId(apiCat.getId().toString());
            apiMenu.setName("");
            apiMenu.setParentId("0");

            ApiCatExample example2 = new ApiCatExample();
            ApiCatExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andCatPidEqualTo(apiCat.getId());
            List<ApiCat> apiCatList2 = apiCatMapper.selectByExample(example2);
            if (apiCatList2.size() > 0) {
                List<ApiMenu> apiMenusub2 = new ArrayList<>();
                for (ApiCat apiCat2 : apiCatList2) {
                    ApiMenu apiMenu2 = new ApiMenu();
                    apiMenu2.setType("submenu");
                    apiMenu2.setTitle(apiCat2.getApiCatName());
                    apiMenu2.setApiId(0);
                    apiMenu2.setCatId(apiCat2.getId().toString());
                    apiMenu2.setName("");
                    apiMenu2.setParentId(apiCat2.getCatPid().toString());
                    apiMenusub2.add(apiMenu2);


                    ApiExample apiexample = new ApiExample();
                    ApiExample.Criteria criteria = apiexample.createCriteria();
                    criteria.andCatIdEqualTo(apiCat.getId());
                    /*判断文件夹下面是否有文件*/
                    List<Api> apiList2 = apiMapper.selectByExample(apiexample);
                    if (apiList2.size() > 0) {
                        List<ApiMenu> apiMenusub = new ArrayList<>();
                        for (Api api : apiList2) {
                            ApiMenu item = new ApiMenu();
                            item.setType("menu-item");
                            item.setTitle(api.getApiName());
                            item.setApiId(api.getId());
                            item.setName(api.getUrl());
                            item.setParentId(api.getCatId().toString());
                            apiMenusub.add(item);
                        }
                        apiMenu2.setChildren(apiMenusub);
                    }
                    apiMenuList.add(apiMenu);
                }
                apiMenu.setChildren(apiMenusub2);
            }


        }
        ApiExample apiExample = new ApiExample();
        ApiExample.Criteria apiCriteria = apiExample.createCriteria();
        apiCriteria.andCatIdEqualTo(0);
        List<Api> apiList = apiMapper.selectByExample(apiExample);
        for (Api api : apiList) {
            ApiMenu item = new ApiMenu();
            item.setType("menu-item");
            item.setTitle(api.getApiName());
            item.setCatId(api.getCatId().toString());
            item.setApiId(api.getId());
            item.setName(api.getCatId().toString());
            item.setParentId("0");
            apiMenuList.add(item);
        }
        return apiMenuList;
    }

    public List<ApiCat> getApiCat() {
        ApiCatExample apiCatExample = new ApiCatExample();
        return apiCatMapper.selectByExample(apiCatExample);
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveApiCat(ApiCat apiCat) {
        return apiCatMapper.insertSelective(apiCat);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteApiCat(Integer id) {
        apiCatMapper.deleteByPrimaryKey(id);
        ApiExample example = new ApiExample();
        ApiExample.Criteria criteria = example.createCriteria();
        criteria.andCatIdEqualTo(id);
        apiMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateApiCatById(Integer id, String catId) {
        ApiCat apiCat = new ApiCat();
        apiCat.setCatPid(Integer.valueOf(catId));

        ApiCatExample apiCatExample = new ApiCatExample();
        ApiCatExample.Criteria criteria = apiCatExample.createCriteria();
        criteria.andIdEqualTo(id);

        return apiCatMapper.updateByExampleSelective(apiCat,apiCatExample);
    }
}

