/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author umprasad
 */
@Entity
@Table(name="CUSTOMER_BILL_DETAILS")
public class CustomerDetails extends  BaseEntity{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="BILL_NO")
    private Long billNo;
    
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.DATE)
    private Date billDate;
    
    @Column(name ="CUST_NAME")
    private String customerName;
    
    @Column(name ="CUST_CONTACT")
    private String customerContact;
    
    @Column(name = "AMOUNT")
    private Double amount;
    
    @OneToMany(cascade =CascadeType.REMOVE,mappedBy ="customerDetails")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BillEntry> billEntrys;

    public Long getBillNo() {
        return billNo;
    }

    public void setBillNo(Long billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<BillEntry> getBillEntrys() {
        return billEntrys;
    }

    public void setBillEntrys(List<BillEntry> billEntrys) {
        this.billEntrys = billEntrys;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDetails other = (CustomerDetails) obj;
        if (this.billNo != other.billNo && (this.billNo == null || !this.billNo.equals(other.billNo))) {
            return false;
        }
        return true;
    }
    
    
    
 }
