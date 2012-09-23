package com.droidsky.fetcher;

import com.droidsky.R;
import com.droidsky.model.InterestingStorms;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class DarkSkyFetcher<T extends Object> {

    public final String BASE_URL = "https://api.darkskyapp.com/v1/interesting/";
    private final ObjectMapper objectMapper;
    protected String darkSkyKey;

    public DarkSkyFetcher(String darkSkyKey) {
        objectMapper = new ObjectMapper();
        this.darkSkyKey = darkSkyKey;
    }

    public T fetchData() {
        try {
            URL darkSkyUrl = new URL(buildUrlString());

            HttpURLConnection connection = (HttpURLConnection) darkSkyUrl.openConnection();
            return objectMapper.readValue(connection.getInputStream(), getModelClass());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    abstract String buildUrlString();
    abstract Class<T> getModelClass();
}
