package com.kusand.droidsky.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicForecast {

    private Boolean isPrecipitating;
    private Integer minutesUntilChange;
    private String currentSummary;
    private String hourSummary;
    private String daySummary;
    private Integer currentTemp;
    private Integer checkTimeout;

    @JsonProperty("isPrecipitating")
    public Boolean getPrecipitating() {
        return isPrecipitating;
    }

    @JsonProperty("isPrecipitating")
    public void setPrecipitating(Boolean precipitating) {
        isPrecipitating = precipitating;
    }

    public Integer getMinutesUntilChange() {
        return minutesUntilChange;
    }

    public void setMinutesUntilChange(Integer minutesUntilChange) {
        this.minutesUntilChange = minutesUntilChange;
    }

    public String getCurrentSummary() {
        return currentSummary;
    }

    public void setCurrentSummary(String currentSummary) {
        this.currentSummary = currentSummary;
    }

    public String getHourSummary() {
        return hourSummary;
    }

    public void setHourSummary(String hourSummary) {
        this.hourSummary = hourSummary;
    }

    public String getDaySummary() {
        return daySummary;
    }

    public void setDaySummary(String daySummary) {
        this.daySummary = daySummary;
    }

    public Integer getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Integer currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Integer getCheckTimeout() {
        return checkTimeout;
    }

    public void setCheckTimeout(Integer checkTimeout) {
        this.checkTimeout = checkTimeout;
    }
}
