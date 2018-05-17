package com.applet.entity.geo;

public class BikeGeoInfo {

    private BikeGeoDetailInfo content;

    private GeoDistanceInfo distance;

    public BikeGeoDetailInfo getContent() {
        return content;
    }

    public void setContent(BikeGeoDetailInfo content) {
        this.content = content;
    }

    public GeoDistanceInfo getDistance() {
        return distance;
    }

    public void setDistance(GeoDistanceInfo distance) {
        this.distance = distance;
    }
}
