package com.hb56.block.model.api;

import java.io.Serializable;

/**
 * @author Been
 */
public class ApiCat implements Serializable {
    private Integer id;

    private String apiCatName;

    private Integer catPid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiCatName() {
        return apiCatName;
    }

    public void setApiCatName(String apiCatName) {
        this.apiCatName = apiCatName == null ? null : apiCatName.trim();
    }

    public Integer getCatPid() {
        return catPid;
    }

    public void setCatPid(Integer catPid) {
        this.catPid = catPid;
    }
}