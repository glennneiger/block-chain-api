package com.hb56.client.api.controller;

import com.hb56.block.model.api.ApiCat;
import com.hb56.block.model.api.ApiMenu;
import com.hb56.client.api.service.ApiCatService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-18-15:04
 * @company www.harbsoft.com
 */

@RestController
@RequestMapping("/apiCat")
public class ApiCatController {

    @Autowired
    ApiCatService apiCatService;

    /**
     * 获取菜单树
     * @return
     */
    @GetMapping("/menu")
    public RestInfo getApiMenu () {
        try {
            List<ApiMenu> apiMenus = apiCatService.getApiMenu();
            return RestUtil.setData(apiMenus);
        }catch (Exception e) {
            return RestUtil.setErrorMsg("查询失败",e.getMessage());
        }
    }

    /**
     * 获取所有的父目录
     * @return
     */
    @GetMapping("/apiCat")
    public RestInfo getApiCat() {
        try {
            List<ApiCat> apiCats = apiCatService.getApiCat();
            return RestUtil.setData(apiCats);
        }catch (Exception e) {
            return RestUtil.setErrorMsg("查询失败",e.getMessage());
        }
    }
}