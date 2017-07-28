/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author umprasad
 */
@Entity
@Table(name = "HEADER_SETTING")
public class HeaderSettings extends  BaseEntity{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="HEADER_ID")
    private Long id;
    
    @Column(name ="COMPANY_NAME")
    private String companyName;
    
    @Column(name ="ADDRESS")
    private String address;
    
    @Column(name = "ADDRESS_SUB")
    private String addressSub;
    
    @Column(name = "CONTACT_NO")
    private String contactNo;
    
    @Column(name = "REG_NO")
    private String registrationNo;
    
    @Column(name ="DEFAULT_USE")
    private String defaultUse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressSub() {
        return addressSub;
    }

    public void setAddressSub(String addressSub) {
        this.addressSub = addressSub;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getDefaultUse() {
        return defaultUse;
    }

    public void setDefaultUse(String defaultUse) {
        this.defaultUse = defaultUse;
    }
    
    
}
