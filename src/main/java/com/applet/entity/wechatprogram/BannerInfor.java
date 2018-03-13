package com.applet.entity.wechatprogram;

/**
 * BannerInfor
 *
 * @author hcx
 * @date 2018/03/08
 */
import java.io.Serializable;
import java.util.Date;

//@Table(name="t_app_display")
public class BannerInfor implements Serializable {
    private Integer id;

    private String cityName;

    /**
     * 配置类型(1.结束行程分享图片；2.开锁图片；3.骑行中提示；4.活动导航栏；5.活动二级弹窗；6.启动页；7.地图中心图标；8.扫码按钮背景图);9.充值界面图片；10.客服图标；11.我的图标；12.赳赳乐享图标;13.定位点图标;14.骑行结束广告列表
     */
//    @Column(name = "type")
    private Byte type;

    /**
     * 显示的话术
     */
//    @Column(name = "display_words")
    private String displayWords;

    /**
     * 显示的图片
     */
//    @Column(name = "display_pic")
    private String displayPic;

    /**
     * 0:不跳转  1：赳赳乐享 2：url
     */
//    @Column(name = "action_type")
    private Byte actionType;

    /**
     * 跳转url
     */
//    @Column(name = "action_url")
    private String actionUrl;

    /**
     * 如果直接跳转到活动页，需要关联活动id
     */
//    @Column(name = "activity_id")
    private String activityId;

    /**
     * 配置信息类型
     */
//    @Column(name = "description")
    private String description;

    private Date addTime;

    private Date updateTime;

    /**
     * 城市类型配置    0:一般城市   1：默认配置
     */
//    @Column(name = "state")
    private Byte state;

    /**
     * 0:全显示 1:android 2:ios
     */
//    @Column(name = "os")
    private Integer os;

    /**
     * 1:已删除   0：正常
     */
//    @Column(name = "del_flag")
    private Byte delFlag;

    /**
     * 开始时间
     */
//    @Column(name = "display_time")
    private Date displayTime;

    /**
     * 失效时间
     */
//    @Column(name = "del_time")
    private Date delTime;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    /**
     * 大于等于此版本的app才会显示
     */
//    @Column(name = "version_limit")
    private String versionLimit;

    /**
     * 是否是下载页面（0：否  1：是）
     */
//    @Column(name = "is_download")
    private Byte isDownload;

    /**
     * 0.落地页下载 1.模板下载  2.直接下载
     */
//    @Column(name = "download_type")
    private Integer downloadType;

    /**
     * ios展示的app版本
     */
//    @Column(name = "ios_versions")
    private String iosVersions;

    /**
     * 安卓展示的app版本
     */
//    @Column(name = "android_versions")
    private String androidVersions;

    /**
     * 排序
     */
//    @Column(name = "sort")
    private Integer sort;

    /**
     * 展示类型(1.静态图 2.gif 3.视频)
     */
//    @Column(name = "show_type")
    private Integer showType;

    /**
     * gif展示倒计时
     */
//    @Column(name = "gif_countdown")
    private Integer gifCountdown;

    /**
     * 是否是广告  0.不是 1.是
     */
//    @Column(name = "is_ad")
    private Byte isAd;

    /**
     * 下载方式(0:有下载页；1：下载模板；2：直接下载)
     */
//    @Column(name = "download_way")
    private Integer downloadWay;

    /**
     * 下载模板id
     */
//    @Column(name = "download_id")
    private String downloadId;

    private String districtIds;

    /**
     * ios投放版本
     */
//    @Column(name = "ios_version_ids")
    private String iosVersionIds;

    /**
     * 安卓投放版本
     */
//    @Column(name = "android_version_ids")
    private String androidVersionIds;

    /**
     * 广告渠道id
     */
//    @Column(name = "ad_channel_id")
    private String adChannelId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDisplayWords() {
        return displayWords;
    }

    public void setDisplayWords(String displayWords) {
        this.displayWords = displayWords == null ? null : displayWords.trim();
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic == null ? null : displayPic.trim();
    }

    public Byte getActionType() {
        return actionType;
    }

    public void setActionType(Byte actionType) {
        this.actionType = actionType;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public Date getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(Date displayTime) {
        this.displayTime = displayTime;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getVersionLimit() {
        return versionLimit;
    }

    public void setVersionLimit(String versionLimit) {
        this.versionLimit = versionLimit == null ? null : versionLimit.trim();
    }

    public Byte getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Byte isDownload) {
        this.isDownload = isDownload;
    }

    public Integer getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(Integer downloadType) {
        this.downloadType = downloadType;
    }

    public String getIosVersions() {
        return iosVersions;
    }

    public void setIosVersions(String iosVersions) {
        this.iosVersions = iosVersions == null ? null : iosVersions.trim();
    }

    public String getAndroidVersions() {
        return androidVersions;
    }

    public void setAndroidVersions(String androidVersions) {
        this.androidVersions = androidVersions == null ? null : androidVersions.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getGifCountdown() {
        return gifCountdown;
    }

    public void setGifCountdown(Integer gifCountdown) {
        this.gifCountdown = gifCountdown;
    }

    public Byte getIsAd() {
        return isAd;
    }

    public void setIsAd(Byte isAd) {
        this.isAd = isAd;
    }

    public Integer getDownloadWay() {
        return downloadWay;
    }

    public void setDownloadWay(Integer downloadWay) {
        this.downloadWay = downloadWay;
    }

    public String getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId == null ? null : downloadId.trim();
    }

    public String getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(String districtIds) {
        this.districtIds = districtIds == null ? null : districtIds.trim();
    }

    public String getIosVersionIds() {
        return iosVersionIds;
    }

    public void setIosVersionIds(String iosVersionIds) {
        this.iosVersionIds = iosVersionIds == null ? null : iosVersionIds.trim();
    }

    public String getAndroidVersionIds() {
        return androidVersionIds;
    }

    public void setAndroidVersionIds(String androidVersionIds) {
        this.androidVersionIds = androidVersionIds == null ? null : androidVersionIds.trim();
    }

    public String getAdChannelId() {
        return adChannelId;
    }

    public void setAdChannelId(String adChannelId) {
        this.adChannelId = adChannelId == null ? null : adChannelId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityName=").append(cityName);
        sb.append(", type=").append(type);
        sb.append(", displayWords=").append(displayWords);
        sb.append(", displayPic=").append(displayPic);
        sb.append(", actionType=").append(actionType);
        sb.append(", actionUrl=").append(actionUrl);
        sb.append(", activityId=").append(activityId);
        sb.append(", description=").append(description);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", state=").append(state);
        sb.append(", os=").append(os);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", displayTime=").append(displayTime);
        sb.append(", delTime=").append(delTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", versionLimit=").append(versionLimit);
        sb.append(", isDownload=").append(isDownload);
        sb.append(", downloadType=").append(downloadType);
        sb.append(", iosVersions=").append(iosVersions);
        sb.append(", androidVersions=").append(androidVersions);
        sb.append(", sort=").append(sort);
        sb.append(", showType=").append(showType);
        sb.append(", gifCountdown=").append(gifCountdown);
        sb.append(", isAd=").append(isAd);
        sb.append(", downloadWay=").append(downloadWay);
        sb.append(", downloadId=").append(downloadId);
        sb.append(", districtIds=").append(districtIds);
        sb.append(", iosVersionIds=").append(iosVersionIds);
        sb.append(", androidVersionIds=").append(androidVersionIds);
        sb.append(", adChannelId=").append(adChannelId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
