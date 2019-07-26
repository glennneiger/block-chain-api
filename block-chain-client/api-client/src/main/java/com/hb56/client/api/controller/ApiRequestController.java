package com.hb56.client.api.controller;

import com.hb56.block.model.api.ApiRequest;
import com.hb56.client.api.service.ApiRequestService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-18-8:59
 * @company www.harbsoft.com
 */

@RestController
@RequestMapping("/apiRequest")
public class ApiRequestController {

    @Autowired
    ApiRequestService apiRequestService;

    @PostMapping(value = "/apiRequest/{apiId}")
    public RestInfo saveRequestBody(@RequestBody List<ApiRequest> apiRequests , @PathVariable Integer apiId) {
        /*1.先批量删除指定API的所有数据 删除*/
        /*2. 新增所有的数据， 有id 则用指定的， 没有就递增*/
        try {
            apiRequestService.deleteByApiId(apiId);
            if (!apiRequests.isEmpty()) {
                apiRequestService.saveApiResponse(apiRequests);
            }
            return RestUtil.setData("保存成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("保存失败！",e.getMessage());
        }
    }

    @GetMapping(value = "/apiRequest/{apiId}")
    public RestInfo getApiRequest(@PathVariable Integer apiId) {

        try {
            List<ApiRequest> requestList = new ArrayList<>();
            List<ApiRequest> list = apiRequestService.getApiRequestByApiId(apiId);

            for (ApiRequest request : list) {
                int count = apiRequestService.getApiRequestByPid(request.getId());
                if (count == 0) {
                    request.setChilder(false);
                }else{
                    request.setChilder(true);
                }
                requestList.add(request);
            }
            return RestUtil.setSuccessData(requestList,"查询成功");
        } catch (Exception e) {
            return RestUtil.setErrorMsg("查询失败！",e.getMessage());
        }
    }

}