package com.applet.model;

import com.applet.model.BaseModel;

import java.util.Date;

public class NyCustomStore extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private String id;

    private String storeName;

    private String customId;

    private String contact;

    private String storeAddr;

    private String storeTel;

    private Long storeProvinceid;

    private Long storeCityid;

    private Long storeRegionid;

    private Long storeStreetid;

    private String longitude;

    private String latitude;

    private String serviceTel;

    private String businessBeginTime;

    private String businessEndTime;

    private String businessWeekday;

    private String storePic1;

    private String storePic2;

    private String storeRecommend;

    private Date addTime;

    private Short delFlag;

    private Boolean openFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String thumbnail;

    private Integer customType;

    private String goodsId;

    private Integer avgCost;

    private String smallTip;

    private String title;

    private String mUrl;

    private String mContent;

    private String cbd;

    private String shareUrl;

    private String shopTime;

    private String storeId;

    private Integer distance;

    private int isCollection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getStoreAddr() {
        return storeAddr;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr == null ? null : storeAddr.trim();
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel == null ? null : storeTel.trim();
    }

    public Long getStoreProvinceid() {
        return storeProvinceid;
    }

    public void setStoreProvinceid(Long storeProvinceid) {
        this.storeProvinceid = storeProvinceid;
    }

    public Long getStoreCityid() {
        return storeCityid;
    }

    public void setStoreCityid(Long storeCityid) {
        this.storeCityid = storeCityid;
    }

    public Long getStoreRegionid() {
        return storeRegionid;
    }

    public void setStoreRegionid(Long storeRegionid) {
        this.storeRegionid = storeRegionid;
    }

    public Long getStoreStreetid() {
        return storeStreetid;
    }

    public void setStoreStreetid(Long storeStreetid) {
        this.storeStreetid = storeStreetid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel == null ? null : serviceTel.trim();
    }

    public String getBusinessBeginTime() {
        return businessBeginTime;
    }

    public void setBusinessBeginTime(String businessBeginTime) {
        this.businessBeginTime = businessBeginTime == null ? null : businessBeginTime.trim();
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime == null ? null : businessEndTime.trim();
    }

    public String getBusinessWeekday() {
        return businessWeekday;
    }

    public void setBusinessWeekday(String businessWeekday) {
        this.businessWeekday = businessWeekday == null ? null : businessWeekday.trim();
    }

    public String getStorePic1() {
        return storePic1;
    }

    public void setStorePic1(String storePic1) {
        this.storePic1 = storePic1 == null ? null : storePic1.trim();
    }

    public String getStorePic2() {
        return storePic2;
    }

    public void setStorePic2(String storePic2) {
        this.storePic2 = storePic2 == null ? null : storePic2.trim();
    }

    public String getStoreRecommend() {
        return storeRecommend;
    }

    public void setStoreRecommend(String storeRecommend) {
        this.storeRecommend = storeRecommend == null ? null : storeRecommend.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Boolean openFlag) {
        this.openFlag = openFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getCustomType() {
        return customType;
    }

    public void setCustomType(Integer customType) {
        this.customType = customType;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(Integer avgCost) {
        this.avgCost = avgCost;
    }

    public String getSmallTip() {
        return smallTip;
    }

    public void setSmallTip(String smallTip) {
        this.smallTip = smallTip == null ? null : smallTip.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl == null ? null : mUrl.trim();
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }

    public String getCbd() {
        return cbd;
    }

    public void setCbd(String cbd) {
        this.cbd = cbd == null ? null : cbd.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }
}