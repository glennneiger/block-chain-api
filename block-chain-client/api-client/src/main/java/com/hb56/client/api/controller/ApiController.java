package com.hb56.client.api.controller;

import com.hb56.block.model.api.Api;
import com.hb56.block.model.api.ApiCat;
import com.hb56.block.model.api.ApiMenu;
import com.hb56.block.model.api.constant.ApiCatConstant;
import com.hb56.client.api.service.ApiCatService;
import com.hb56.client.api.service.ApiService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-15-13:43
 * @company www.harbsoft.com
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;
    @Autowired
    ApiCatService apiCatService;

    /**
     * 新增Api
     * @param api
     * @return
     */
    @RequestMapping("/api")
    public RestInfo saveApi(@RequestBody Api api) {
        try {
            apiService.saveApi(api);
            return RestUtil.setSuccessData(api.getId(),"保存成功！");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("保存失败",e.getMessage());
        }
    }

    /**
     * 删除
     * @param apiMenu
     * @return
     */
    @DeleteMapping(value = "/api")
    public RestInfo deleteApiById(@RequestBody ApiMenu apiMenu) {
        String type = apiMenu.getType();
        Integer id = apiMenu.getId();
        try {
            if (ApiCatConstant.TYPE_FILE.equals(type)) {
                apiService.deleteApi(id);
            }else if (ApiCatConstant.TYPE_FILES.equals(type)) {
               apiCatService.deleteApiCat(id);
            }
            return RestUtil.setSuccessMsg("删除成功！");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("删除失败！",e.getMessage());
        }
    }

    /**
     * 修改
     * @param api
     * @return
     */
    @PutMapping(value = "/api")
    public RestInfo updateApiById(@RequestBody Api api) {
        try {
            apiService.updateApi(api);
            return RestUtil.setSuccessMsg("更新成功！");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("更新失败！",e.getMessage());
        }
    }

    /**
     *根据id 查询api
     * @param id
     * @return
     */
    @GetMapping(value = "/api/{id}")
    public RestInfo getApi(@PathVariable(name = "id") Integer id) {
            List<Api> apiList = apiService.getApi(id);
            if (apiList.isEmpty()) {
                return RestUtil.setErrorMsg("查询的API不存在！");
            }else {
                return RestUtil.setData(apiList.get(0));
            }
    }

    @GetMapping("/api")
    public RestInfo getApiCat() {
        try {
            List<Api> apiList = apiService.getApi();
            return RestUtil.setSuccessData(apiList,"获取成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("获取失败！",e.getMessage());
        }
    }

    /**
     * 新增菜单
     * @return
     */
    @PostMapping(value = "/apiMenu")
    public RestInfo saveApiMenu(@RequestBody ApiMenu apiMenu) {
        try {
            String type = apiMenu.getType();
            String title = apiMenu.getTitle();
            String name = apiMenu.getName();
            String catId = apiMenu.getCatId();
            String apiDesc = apiMenu.getApiDesc();
            String parentId = apiMenu.getParentId();
            Integer id = 0;
            if (ApiCatConstant.TYPE_FILE.equals(type)) {
                Api api = new Api();
                api.setApiName(title);
                api.setCatId(Integer.valueOf(catId));
                api.setUrl(name);
                api.setApiDesc(apiDesc);
                apiService.saveApi(api);
                id = api.getId();
            }else if (ApiCatConstant.TYPE_FILES.equals(type)){
                ApiCat apiCat = new ApiCat();
                apiCat.setApiCatName(title);
                apiCat.setCatPid(Integer.valueOf(parentId));
                apiCatService.saveApiCat(apiCat);
                id = apiCat.getId();
            }
            return RestUtil.setSuccessData(id,"保存成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("保存失败",e.getMessage());
        }
    }

    @PutMapping(value = "/apiMenu")
    public RestInfo moveToApiCat(@RequestBody ApiMenu apiMenu) {
        String type = apiMenu.getType();
        Integer id = apiMenu.getId();
        String catId = apiMenu.getLocation();
        int count = 0;
        try {
            if (ApiCatConstant.TYPE_FILE.equals(type)) {
                count = apiService.updateApiById(id,catId);
            }else if (ApiCatConstant.TYPE_FILES.equals(type)) {
                count = apiCatService.updateApiCatById(id,catId);
            }
            return RestUtil.setSuccessData(count,"更新成功！");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("更新失败！",e.getMessage());
        }
    }
}