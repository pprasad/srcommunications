/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author umprasad
 */
@Entity
@Table(name ="PRODUCT_ENTRY")
public class ProductEntry extends  BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PROD_ENTRY_ID")
    private Long Id;
    
    @OneToOne
    @JoinColumn(name="PROD_CODE",referencedColumnName="PRODUCT_CODE")
    private CategoryProduct categoryProduct;
    
    @Column(name="PROD_ENTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    
    @Column(name="PROD_UNIT_PRICE")
    private Double unitPrice;
    
    @Column(name ="PROD_SALES_PRICE")
    private Double salesPrice;
    
    @Column(name="PROD_QUANTITY")
    private Integer quantity;
    
    @Column(name = "PROD_AMT")
    private Double prodAmount;
   
    @Transient
    private Integer oldQty;
   
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Double prodAmount) {
        this.prodAmount = prodAmount;
    }

    public Integer getOldQty() {
        return oldQty;
    }
    public void setOldQty(Integer oldQty) {
        this.oldQty = oldQty;
    }
    public Calendar getDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(this.entryDate);
        return calendar;
    }
 }
