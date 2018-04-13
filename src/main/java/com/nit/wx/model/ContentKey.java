package com.nit.wx.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ContentKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contentKeyId;

    private Integer userId;

    private String keyword;

    private String contentId;

    @NotNull
    private Integer contentFu;

    private String contentFuId;
    @NotNull
    private Integer isFinish;
    @NotNull
    private Integer zanNumber;
    @NotNull
    private Integer isZanFinish;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentFuId() {
        return contentFuId;
    }

    public void setContentFuId(String contentFuId) {
        this.contentFuId = contentFuId;
    }



    public Integer getContentKeyId() {
        return contentKeyId;
    }

    public void setContentKeyId(Integer contentKeyId) {
        this.contentKeyId = contentKeyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getContentFu() {
        return contentFu;
    }

    public void setContentFu(Integer contentFu) {
        this.contentFu = contentFu;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getZanNumber() {
        return zanNumber;
    }

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }

    public Integer getIsZanFinish() {
        return isZanFinish;
    }

    public void setIsZanFinish(Integer isZanFinish) {
        this.isZanFinish = isZanFinish;
    }
}