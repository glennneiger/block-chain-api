package com.hb56.block.model.api;

import java.io.Serializable;

/**
 * @author Been
 */
public class Api implements Serializable {
    private Integer id;

    private Integer catId;

    private String apiName;

    private String url2;

    private String url;

    private String apiDesc;

    private String reqDesc;

    private String headerExample;

    private String reqExample;

    private String respExample;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2 == null ? null : url2.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc == null ? null : apiDesc.trim();
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc == null ? null : reqDesc.trim();
    }

    public String getHeaderExample() {
        return headerExample;
    }

    public void setHeaderExample(String headerExample) {
        this.headerExample = headerExample == null ? null : headerExample.trim();
    }

    public String getReqExample() {
        return reqExample;
    }

    public void setReqExample(String reqExample) {
        this.reqExample = reqExample == null ? null : reqExample.trim();
    }

    public String getRespExample() {
        return respExample;
    }

    public void setRespExample(String respExample) {
        this.respExample = respExample == null ? null : respExample.trim();
    }
}