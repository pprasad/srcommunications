/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author umprasad
 */
@Entity
public class StockReport implements  Serializable{
    
    @Id
    @Column(name ="ROW_NO")
    private Integer Id;
    
    @Column(name ="PROD_CODE")
    private  String prodCode;
    
    @Column(name="PROD_NAME")
    private  String prodName;
    
    @Column(name ="TOTAL_QTY")
    private Integer totalQty;
    
    @Column(name = "SALES_QTY")
    private Integer salesQty;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Integer getSalesQty() {
        return salesQty;
    }

    public void setSalesQty(Integer salesQty) {
        this.salesQty = salesQty;
    }
}
