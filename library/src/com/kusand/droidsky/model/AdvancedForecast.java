package com.kusand.droidsky.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvancedForecast extends BasicForecast {
    private String radarStation;
    private String timeZone;
    private List<HourlyPrecipitationForecast> hourlyPrecipitationForecasts;

    public List<HourlyPrecipitationForecast> getHourlyPrecipitationForecasts() {
        return hourlyPrecipitationForecasts;
    }

    @JsonProperty("hourPrecipitation")
    public void setHourlyPrecipitationForecasts(HourlyPrecipitationForecast[] hourlyPrecipitationForecasts) {
        this.hourlyPrecipitationForecasts = new ArrayList<HourlyPrecipitationForecast>();
        Collections.addAll(this.hourlyPrecipitationForecasts, hourlyPrecipitationForecasts);
    }

    public String getRadarStation() {
        return radarStation;
    }

    public void setRadarStation(String radarStation) {
        this.radarStation = radarStation;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
