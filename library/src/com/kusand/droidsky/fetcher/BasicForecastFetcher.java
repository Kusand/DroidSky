package com.kusand.droidsky.fetcher;

import com.kusand.droidsky.model.BasicForecast;

public class BasicForecastFetcher extends DarkSkyFetcher<BasicForecast> {

    public final String BASE_URL = "https://api.darkskyapp.com/v1/brief_forecast/";

    private String latitude;
    private String longitude;

    public BasicForecastFetcher(String darkSkyKey, String latitude, String longitude) {
        super(darkSkyKey);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    String buildUrlString() {
        return BASE_URL + darkSkyKey + "/" + latitude + "," + longitude;
    }

    @Override
    Class<BasicForecast> getModelClass() {
        return BasicForecast.class;
    }
}
