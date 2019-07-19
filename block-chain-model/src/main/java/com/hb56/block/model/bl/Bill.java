package com.hb56.block.model.bl;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author cosmos
 */
@Data
public class Bill implements Serializable {

    /**
     * 序列化以后的id
     */
    private BigDecimal billId;

    /**
     * 船舶imoNo
     */
    private String imoNo;

    /**
     * 船舶英文名称
     */
    private String vesselEnName;

    /**
     * 船舶中文名称
     */
    private String vesselCnName;


    /**
     * 预计到港时间
     */
    private Date eta;

    /**
     * 预计离港时间
     */
    private Date etd;

    /**
     * 航次
     */
    private String voyageNo;

    /**
     * 国籍
     */
    private String nationalityCode;

    /**
     * 装船时间
     */
    private Date loadingDate;

    /**
     * 承运人代码
     */
    private String shippingLineCode;

    /**
     * 承运人
     */
    private String shippingLine;

    /**
     * 进出口标志
     */
    private String portId;

    /**
     * 提单号
     */
    private String billNo;

    /**
     * 装货港代码
     */
    private String loadPortCode;

    /**
     * 装货港
     */
    private String loadPort;

    /**
     * 卸货港代码
     */
    private String dischargePortCode;

    /**
     * 卸货港
     */
    private String dischargePort;


    /**
     * 收货地代码
     */
    private String placeCodeOfReceipt;

    /**
     * 收货地
     */
    private String placeOfReceipt;


    /**
     * 交货地（启运港）
     */
    private String placeOfDelivery;

    /**
     * 交货地代码（启运港）
     */
    private String placeCodeOfDelivery;


    /**
     * 运输条款
     */
    private String forwardingClause;

    /**
     * 付款方式
     */
    private String typeOfPayment;


    /**
     * 货物总件数
     */
    private BigDecimal totalNumbersOfPackages;

    /**
     * 货物总毛重（千克）
     */
    private BigDecimal totalGrossWeight;

    /**
     * 货物体积（立方米）
     */
    private BigDecimal cube;

    /**
     * 发货人代码
     */
    private String shipperCode;

    /**
     * 发货人
     */
    private String shipper;

    /**
     * 收货人代码
     */
    private String consigneeCode;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 通知方代码
     */
    private String notifyCode;

    /**
     * 通知方
     */
    private String notify;


    /**
     * 提单hash
     */
    private String hash;

    /**
     * 货物信息
     */
    private List<BillCargo> billCargoList;

    /**
     * 集装箱信息
     */
    private List<BillCntr> billCntrList;

}
