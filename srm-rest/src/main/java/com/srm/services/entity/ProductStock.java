/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author umprasad
 */
@Entity
@Table(name="PRODUCT_STOCK")
public class ProductStock extends BaseEntity{
   
    @Id
    @Column(name ="PROD_CODE")
    private String productCode;
    
    @Column(name ="PROD_QUANTITY")
    private Integer quantity;

    @Column(name ="PROD_PRICE")
    private Double price;
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    
}
