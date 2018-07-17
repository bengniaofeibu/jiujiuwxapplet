package com.applet.entity.UserInfo;

import com.applet.model.JiumiLog;

import java.util.List;

public class UserJiuMiRankListRes {

    private List<JiumiLog> jiumiLogs;

    private JiumiLog jiumiLog;

    private Integer rankListFlag=0;

    private Integer jiuSumDiff=0;

    private Integer myJiuMiRanking;


    public List<JiumiLog> getJiumiLogs() {
        return jiumiLogs;
    }

    public void setJiumiLogs(List<JiumiLog> jiumiLogs) {
        this.jiumiLogs = jiumiLogs;
    }

    public JiumiLog getJiumiLog() {
        return jiumiLog;
    }

    public void setJiumiLog(JiumiLog jiumiLog) {
        this.jiumiLog = jiumiLog;
    }

    public Integer getRankListFlag() {
        return rankListFlag;
    }

    public void setRankListFlag(Integer rankListFlag) {
        this.rankListFlag = rankListFlag;
    }

    public Integer getJiuSumDiff() {
        return jiuSumDiff;
    }

    public void setJiuSumDiff(Integer jiuSumDiff) {
        this.jiuSumDiff = jiuSumDiff;
    }

    public Integer getMyJiuMiRanking() {
        return myJiuMiRanking;
    }

    public void setMyJiuMiRanking(Integer myJiuMiRanking) {
        this.myJiuMiRanking = myJiuMiRanking;
    }
}
