package com.hb56.block.model.bl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cosmos
 */
@Data
public class BillCntr {
    /**
     * 箱号
     */
    private String cntrNo;

    /**
     * 封志号
     */
    private String sealNo;

    /**
     * 封志类型
     */
    private String sealType;

    /**
     * 施加封志人
     */
    private String sealerCode;

    /**
     * 箱子箱型尺寸
     */
    private String cntrSizeType;

    /**
     * 箱子状态
     */
    private String cntrStatus;

    /**
     * 箱内货物件数
     */
    private BigDecimal cntrNumberOfPackages;

    /**
     * 箱内货重
     */
    private BigDecimal netWeight;

    /**
     * 箱皮重
     */
    private BigDecimal tareWeight;
}
