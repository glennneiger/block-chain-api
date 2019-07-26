package com.hb56.client.edo.collect.model;

/**
 * @author Been
 */
public class Article {
    // id
    private String id;
    // reqID
    private String message_id;
    // 请求报文
    private byte[] msg_request_blob;
    // 响应报文
    private byte[] msg_response_blob;
    // 内容
    private byte[] msg_request_trans_blob;

    private byte[] msg_response_trans_blob;

    private long agv1time;

    private long agv2time;

    private long agv3time;

    //服务提供方
    private String targetSystem;
    //服务接入方
    private String sourceSystem;
    // 服务名称
    private String serviceName;
    //交易标识
    private String requestId;

    //响应码
    private String errorCode;
    //监控点
    private String monitorPoint;
    //响应描述
    private String errorDesc;
    //创建时间
    private String transTime;
    //类型
    private String accessType;
    //服务描述
    private String description;
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Article() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMonitorPoint() {
        return monitorPoint;
    }

    public void setMonitorPoint(String monitorPoint) {
        this.monitorPoint = monitorPoint;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", message_id=" + message_id + ", msg_request_blob=" + msg_request_blob
                + ", msg_response_blob=" + msg_response_blob + ", msg_request_trans_blob=" + msg_request_trans_blob + "]";
    }

    public byte[] getMsg_request_blob() {
        return msg_request_blob;
    }

    public void setMsg_request_blob(byte[] msg_request_blob) {
        this.msg_request_blob = msg_request_blob;
    }

    public byte[] getMsg_response_blob() {
        return msg_response_blob;
    }

    public void setMsg_response_blob(byte[] msg_response_blob) {
        this.msg_response_blob = msg_response_blob;
    }

    public byte[] getMsg_request_trans_blob() {
        return msg_request_trans_blob;
    }

    public void setMsg_request_trans_blob(byte[] msg_request_trans_blob) {
        this.msg_request_trans_blob = msg_request_trans_blob;
    }

    public byte[] getMsg_response_trans_blob() {
        return msg_response_trans_blob;
    }

    public void setMsg_response_trans_blob(byte[] msg_response_trans_blob) {
        this.msg_response_trans_blob = msg_response_trans_blob;
    }

    public long getAgv1time() {
        return agv1time;
    }

    public void setAgv1time(long agv1time) {
        this.agv1time = agv1time;
    }

    public long getAgv2time() {
        return agv2time;
    }

    public void setAgv2time(long agv2time) {
        this.agv2time = agv2time;
    }

    public long getAgv3time() {
        return agv3time;
    }

    public void setAgv3time(long agv3time) {
        this.agv3time = agv3time;
    }
}
