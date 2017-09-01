/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author umprasad
 */
@Entity
@Table(name ="BILL_DETAILS")
public class BillEntry extends BaseEntity{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="BILL_ID")
    private Long billId;
    
    @ManyToOne
    @JoinColumn(name ="BILL_NO",referencedColumnName="BILL_NO",insertable=true,updatable=true)
    private CustomerDetails customerDetails;
    
    @Column(name="PROD_CODE")
    private String prodCode;
    
    @Column(name = "PROD_NAME")
    private String prodName;
    
    @Column(name="PRICE")
    private Double price;
    
    @Column(name ="QTY")
    private Integer qty;
    
    @Column(name="DISCOUNT")
    private Integer discount;
    
    @Column(name ="AMOUNT")
    private Double amount;
    
   
    
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }
    
    
 }
