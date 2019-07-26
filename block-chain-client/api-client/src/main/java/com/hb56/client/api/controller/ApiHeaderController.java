package com.hb56.client.api.controller;

import com.hb56.block.model.api.ApiHeader;
import com.hb56.client.api.service.ApiHeaderService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-19-17:46
 * @company www.harbsoft.com
 */

@RestController
@RequestMapping("/apiHeader")
public class ApiHeaderController {

    @Autowired
    ApiHeaderService apiHeaderService;

    @PostMapping(value = "/apiHeader/{apiId}")
    public RestInfo saveApiHeader(@RequestBody List<ApiHeader> apiHeaders , @PathVariable Integer apiId) {
        /*1.先批量删除指定API的所有数据 删除*/
        /*2. 新增所有的数据， 有id 则用指定的， 没有就递增*/
        try {
            apiHeaderService.deleteByApiId(apiId);
            if (!apiHeaders.isEmpty()) {
                apiHeaderService.saveApiHeader(apiHeaders);
            }
            return RestUtil.setData("成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("失败！",e.getMessage());
        }
    }

    @GetMapping(value = "/apiHeader/{apiId}")
    public RestInfo getApiHeader(@PathVariable(name = "apiId") Integer apiId) {
        try {
            List<ApiHeader> apiHeaders = apiHeaderService.getApiHeader(apiId);
            return RestUtil.setData(apiHeaders);
        } catch (Exception e) {
            return RestUtil.setErrorMsg("查询失败！",e.getMessage());
        }
    }

}