package com.hb56.client.api.controller;

import com.hb56.block.model.api.ApiResponse;
import com.hb56.client.api.service.ApiResponseService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 批量操作
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-19-11:15
 * @company www.harbsoft.com
 */

@RestController
@RequestMapping("/apiResponse")
public class ApiResponseController  {

    @Autowired
    ApiResponseService apiResponseService;

    /**
     * 增 删 改
     * 删除的没有，
     * 新增的没有id
     * 修改的有id
     * @param apiResponses
     * @return
     */
    @PostMapping(value = "/apiResponse/{apiId}")
    public RestInfo saveApiResponse(@RequestBody List<ApiResponse> apiResponses , @PathVariable Integer apiId) {
        /*1.先批量删除指定API的所有数据 删除*/
        /*2. 新增所有的数据， 有id 则用指定的， 没有就递增*/
        try {
            apiResponseService.deleteByApiId(apiId);
            if (!apiResponses.isEmpty()) {
                apiResponseService.saveApiResponse(apiResponses);
            }
            return RestUtil.setData("成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("失败！",e.getMessage());
        }
    }


    @GetMapping(value = "/apiResponse/{apiId}")
    public RestInfo getApiRequest(@PathVariable Integer apiId) {
        try {
            List<ApiResponse> apiResponses = apiResponseService.getApiResponseByApiId(apiId);
            return RestUtil.setData(apiResponses);
        } catch (Exception e) {
            return RestUtil.setErrorMsg("查询失败！",e.getMessage());
        }
    }

}