package com.hb56.block.model.bl;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cosmos
 */
@Data
public class BillCargo implements Serializable {


    /**
     * 货物序号
     */
    private String cargoSeqNo;

    /**
     * 货物代码
     */
    private String cargoCode;

    /**
     * 件数
     */
    private BigDecimal numberOfPackages;

    /**
     * 包装类型
     */
    private String packageKindCode;

    /**
     * 毛重
     */
    private BigDecimal cargoGrossWeight;

    /**
     * 净重
     */
    private BigDecimal cargoNetWeight;

    /**
     * 体积
     */
    private BigDecimal cargoMeasurement;

    /**
     * 唛头
     */
    private String marks;


    /**
     * 货物描述
     */
    private String cargoDescription;


}
