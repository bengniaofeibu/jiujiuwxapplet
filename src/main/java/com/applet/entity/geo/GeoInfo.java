package com.applet.entity.geo;

import java.util.List;

public class GeoInfo {

    private AverageDistance averageDistance;

    private List<BikeGeoInfo> results;

    public List<BikeGeoInfo> getResults() {
        return results;
    }

    public void setResults(List<BikeGeoInfo> results) {
        this.results = results;
    }

    public AverageDistance getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(AverageDistance averageDistance) {
        this.averageDistance = averageDistance;
    }
}
