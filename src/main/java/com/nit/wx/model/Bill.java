package com.nit.wx.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billId;

    @NotNull
    private String payTime;
    @NotNull
    private String publicId;
    @NotNull
    private String bussinessNo;
    @NotNull
    private String bussinessNox;

    private String machineNo;
    @NotNull
    private String wxOrderId;
    @NotNull
    private String shorderId;
    @NotNull
    private String userType;
    @NotNull
    private String tradeType;
    @NotNull
    private String tradeStatus;
    @NotNull
    private String bank;
    @NotNull
    private String moneyType;
    @NotNull
    private String totalMoney;
    @NotNull
    private String redBoxNum;
    @NotNull
    private String backOrderNo;
    @NotNull
    private String busbackOrderNo;
    @NotNull
    private String backMoneyNum;
    @NotNull
    private String backredBoxNum;
    @NotNull
    private String backType;

    private String backStatus;
    @NotNull
    private String productName;

    private String datebag;
    @NotNull
    private String shouxufei;
    @NotNull
    private String feilv;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId == null ? null : publicId.trim();
    }

    public String getBussinessNo() {
        return bussinessNo;
    }

    public void setBussinessNo(String bussinessNo) {
        this.bussinessNo = bussinessNo == null ? null : bussinessNo.trim();
    }

    public String getBussinessNox() {
        return bussinessNox;
    }

    public void setBussinessNox(String bussinessNox) {
        this.bussinessNox = bussinessNox == null ? null : bussinessNox.trim();
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo == null ? null : machineNo.trim();
    }

    public String getWxOrderId() {
        return wxOrderId;
    }

    public void setWxOrderId(String wxOrderId) {
        this.wxOrderId = wxOrderId == null ? null : wxOrderId.trim();
    }

    public String getShorderId() {
        return shorderId;
    }

    public void setShorderId(String shorderId) {
        this.shorderId = shorderId == null ? null : shorderId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType == null ? null : moneyType.trim();
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney == null ? null : totalMoney.trim();
    }

    public String getRedBoxNum() {
        return redBoxNum;
    }

    public void setRedBoxNum(String redBoxNum) {
        this.redBoxNum = redBoxNum == null ? null : redBoxNum.trim();
    }

    public String getBackOrderNo() {
        return backOrderNo;
    }

    public void setBackOrderNo(String backOrderNo) {
        this.backOrderNo = backOrderNo == null ? null : backOrderNo.trim();
    }

    public String getBusbackOrderNo() {
        return busbackOrderNo;
    }

    public void setBusbackOrderNo(String busbackOrderNo) {
        this.busbackOrderNo = busbackOrderNo == null ? null : busbackOrderNo.trim();
    }

    public String getBackMoneyNum() {
        return backMoneyNum;
    }

    public void setBackMoneyNum(String backMoneyNum) {
        this.backMoneyNum = backMoneyNum == null ? null : backMoneyNum.trim();
    }

    public String getBackredBoxNum() {
        return backredBoxNum;
    }

    public void setBackredBoxNum(String backredBoxNum) {
        this.backredBoxNum = backredBoxNum == null ? null : backredBoxNum.trim();
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType == null ? null : backType.trim();
    }

    public String getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(String backStatus) {
        this.backStatus = backStatus == null ? null : backStatus.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getDatebag() {
        return datebag;
    }

    public void setDatebag(String datebag) {
        this.datebag = datebag == null ? null : datebag.trim();
    }

    public String getShouxufei() {
        return shouxufei;
    }

    public void setShouxufei(String shouxufei) {
        this.shouxufei = shouxufei == null ? null : shouxufei.trim();
    }

    public String getFeilv() {
        return feilv;
    }

    public void setFeilv(String feilv) {
        this.feilv = feilv == null ? null : feilv.trim();
    }
}