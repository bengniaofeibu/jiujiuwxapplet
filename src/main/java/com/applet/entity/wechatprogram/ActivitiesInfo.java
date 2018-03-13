package com.applet.entity.wechatprogram;
/**
 * ActivitiesInfo
 *
 * @author hcx
 * @date 2018/03/09
 */
public class ActivitiesInfo {

    private String id;
    private String imgPath;
    private String activityPath;
    private String isSelf;
    private String shareUrl;
    private String sharePlatform;
    private String videoPath;
    private String shareTitle;
    private String shareContent;
    private String sharePic;
    private String welfareTitle;
    private String welfareSecTitle;
    private String type;
    private String imgPath2;
    private String beginTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getActivityPath() {
        return activityPath;
    }

    public void setActivityPath(String activityPath) {
        this.activityPath = activityPath;
    }

    public String getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getSharePlatform() {
        return sharePlatform;
    }

    public void setSharePlatform(String sharePlatform) {
        this.sharePlatform = sharePlatform;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getWelfareTitle() {
        return welfareTitle;
    }

    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle;
    }

    public String getWelfareSecTitle() {
        return welfareSecTitle;
    }

    public void setWelfareSecTitle(String welfareSecTitle) {
        this.welfareSecTitle = welfareSecTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgPath2() {
        return imgPath2;
    }

    public void setImgPath2(String imgPath2) {
        this.imgPath2 = imgPath2;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ActivitiesInfo{" +
                "id='" + id + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", activityPath='" + activityPath + '\'' +
                ", isSelf='" + isSelf + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", sharePlatform='" + sharePlatform + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", shareTitle='" + shareTitle + '\'' +
                ", shareContent='" + shareContent + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", welfareTitle='" + welfareTitle + '\'' +
                ", welfareSecTitle='" + welfareSecTitle + '\'' +
                ", type='" + type + '\'' +
                ", imgPath2='" + imgPath2 + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
