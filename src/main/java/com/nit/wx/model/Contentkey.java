package com.nit.wx.model;


import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contentkey")
public class Contentkey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Contentkeyid")
    private Integer contentKeyId;

    @Column(name = "Userid")
    private Integer userID;

    @Column(name = "Keyword")
    private String keyWord;

    @Column(name = "Contentid")
    private String contentId;

    @NotNull
    @Column(name = "Contentfu")
    private Integer contentFu;

    public Integer getContentKeyId() {
        return contentKeyId;
    }

    public void setContentKeyId(Integer contentKeyId) {
        this.contentKeyId = contentKeyId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getContentFu() {
        return contentFu;
    }

    public void setContentFu(Integer contentFu) {
        this.contentFu = contentFu;
    }

    public String getContentFuId() {
        return contentFuId;
    }

    public void setContentFuId(String contentFuId) {
        this.contentFuId = contentFuId;
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

    @Column(name = "Contentfuid")
    private String contentFuId;


    @NotNull
    @Column(name = "Isfinish")
    private  Integer isFinish;

    @NotNull
    @Column(name = "Zannumber")
    private Integer zanNumber;

    @NotNull
    @Column(name = "Iszanfinish")
    private Integer isZanFinish;

}
