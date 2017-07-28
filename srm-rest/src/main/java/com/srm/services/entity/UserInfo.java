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
@Table(name ="USER_INFO")
public class UserInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="USER_ID")
    private Long Id;
    
    @Column(name = "USER_NAME")
    private String userName;
    
    @Column(name="LOGIN_ID")
    private String loginId;
    
    @Column(name="PASSWORD")
    private String passWord;
    
    @Column(name ="ACTIVE")
    private String active;
    
    @Column(name ="BILL_ENTRY_ACCESS")
    private Boolean billEntryAccess;
    
    @Column(name = "STOCK_ENTRY_ACCESS")
    private Boolean stockEntryAccess;
    
    @Column(name = "NEW_USER_ACCESS")
    private Boolean newuserAccess;
    
    @Column(name = "VIEW_REPORT_ACCESS")
    private Boolean viewReportAccess;
    
    @Column(name = "SETTINGS_ACCESS")
    private Boolean systemAccess;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Boolean isBillEntryAccess() {
        return billEntryAccess;
    }

    public void setBillEntryAccess(Boolean billEntryAccess) {
        this.billEntryAccess = billEntryAccess;
    }

    public Boolean isStockEntryAccess() {
        return stockEntryAccess;
    }

    public void setStockEntryAccess(Boolean stockEntryAccess) {
        this.stockEntryAccess = stockEntryAccess;
    }

    public Boolean isNewuserAccess() {
        return newuserAccess;
    }

    public void setNewuserAccess(Boolean newuserAccess) {
        this.newuserAccess = newuserAccess;
    }

    public Boolean isViewReportAccess() {
        return viewReportAccess;
    }

    public void setViewReportAccess(Boolean viewReportAccess) {
        this.viewReportAccess = viewReportAccess;
    }

    public Boolean isSystemAccess() {
        return systemAccess;
    }

    public void setSystemAccess(Boolean systemAccess) {
        this.systemAccess = systemAccess;
    }
    
    
}
