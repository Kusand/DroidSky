package com.droidsky.fetcher;

import com.droidsky.model.InterestingStorms;

public class InterestingStormsFetcher extends DarkSkyFetcher<InterestingStorms> {

    public final String BASE_URL = "https://api.darkskyapp.com/v1/interesting/";

    public InterestingStormsFetcher(String darkSkyKey) {
        super(darkSkyKey);
    }

    @Override
    String buildUrlString() {
        return BASE_URL + darkSkyKey;
    }

    @Override
    Class<InterestingStorms> getModelClass() {
        return InterestingStorms.class;
    }
}
