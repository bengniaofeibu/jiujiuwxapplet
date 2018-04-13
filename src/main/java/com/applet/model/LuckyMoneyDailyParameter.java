package com.applet.model;

public class LuckyMoneyDailyParameter {
    private String id;

    private Integer currentTotalCount;

    private Double currentTotalMoney;

    private Integer personalTotalCount;

    private Integer totalCount;

    private Double totalMoney;

    private String day;

    private Integer winningRatio;

    private Double maxMoney;

    private Double minMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCurrentTotalCount() {
        return currentTotalCount;
    }

    public void setCurrentTotalCount(Integer currentTotalCount) {
        this.currentTotalCount = currentTotalCount;
    }

    public Double getCurrentTotalMoney() {
        return currentTotalMoney;
    }

    public void setCurrentTotalMoney(Double currentTotalMoney) {
        this.currentTotalMoney = currentTotalMoney;
    }

    public Integer getPersonalTotalCount() {
        return personalTotalCount;
    }

    public void setPersonalTotalCount(Integer personalTotalCount) {
        this.personalTotalCount = personalTotalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public Integer getWinningRatio() {
        return winningRatio;
    }

    public void setWinningRatio(Integer winningRatio) {
        this.winningRatio = winningRatio;
    }

    public Double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public Double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Double minMoney) {
        this.minMoney = minMoney;
    }
}