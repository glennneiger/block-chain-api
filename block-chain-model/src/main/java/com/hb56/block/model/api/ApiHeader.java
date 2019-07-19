package com.hb56.block.model.api;

import java.io.Serializable;

/**
 * @author Been
 */
public class ApiHeader implements Serializable {
    private Integer id;

    private String param;

    private String paramType;

    private String description;

    private Boolean isRequried;

    private Integer apiId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsRequried() {
        return isRequried;
    }

    public void setIsRequried(Boolean isRequried) {
        this.isRequried = isRequried;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }
}