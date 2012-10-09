package com.droidsky.fetcher;

import com.droidsky.model.AdvancedForecast;
import com.droidsky.model.BasicForecast;

public class AdvancedForecastFetcher extends DarkSkyFetcher<AdvancedForecast> {

    public final String BASE_URL = "https://api.darkskyapp.com/v1/forecast/";

    private String latitude;
    private String longitude;

    public AdvancedForecastFetcher(String darkSkyKey, String latitude, String longitude) {
        super(darkSkyKey);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    String buildUrlString() {
        return BASE_URL + darkSkyKey + "/" + latitude + "," + longitude;
    }

    @Override
    Class<AdvancedForecast> getModelClass() {
        return AdvancedForecast.class;
    }
}
