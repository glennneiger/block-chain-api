package com.hb56.block.model.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-18-15:16
 * @company www.harbsoft.com
 */

@Data
public class ApiMenu implements Serializable {

    private Integer id;
    private String type;
    private String title;
    private Integer apiId;
    private String catId;
    private String apiDesc;
    private String parentId;
    private String location;
    /**catid*/
    private String name;
    private List<ApiMenu> children;
}