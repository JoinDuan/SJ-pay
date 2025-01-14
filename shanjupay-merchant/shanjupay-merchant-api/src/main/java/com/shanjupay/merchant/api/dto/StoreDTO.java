package com.shanjupay.merchant.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="StoreDTO", description="")
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店id")
    @JsonSerialize(using= ToStringSerializer.class)//进行json转换的时候 ，转成string，前端js处理超过位的数字时会出现精度丢失问题
    private Long id;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "门店编号")
    private Long storeNumber;

    @ApiModelProperty(value = "所属商户")
    private Long merchantId;

    @ApiModelProperty(value = "父门店")
    private Long parentId;

    @ApiModelProperty(value = "0表示禁用，1表示启用")
    private Boolean storeStatus;

    @ApiModelProperty(value = "门店地址")
    private String storeAddress;


}
