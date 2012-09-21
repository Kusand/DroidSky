package com.droidsky.fetcher;

import com.droidsky.R;
import com.droidsky.model.InterestingStorms;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InterestingStormsFetcher {

    public final String BASE_URL = "https://api.darkskyapp.com/v1/interesting/";
    private final ObjectMapper objectMapper;
    private String darkSkyKey;

    public InterestingStormsFetcher(String darkSkyKey) {
        objectMapper = new ObjectMapper();
        this.darkSkyKey = darkSkyKey;
    }

    public InterestingStorms getInterestingStorms() {
        try {
            URL interestingStormsUrl = new URL(BASE_URL + darkSkyKey);

            HttpURLConnection connection = (HttpURLConnection) interestingStormsUrl.openConnection();
            return objectMapper.readValue(connection.getInputStream(), InterestingStorms.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
