package com.cennavi.cloudalibaba.order.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String userid;
    private String productid;
    private Integer count;
    private BigDecimal money;
    private String status;

}
