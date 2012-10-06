package com.droidsky;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.droidsky.fetcher.BasicForecastFetcher;
import com.droidsky.model.BasicForecast;

public class DroidSkySampleBasicForecastActivity extends Activity {

    private String latitude;
    private String longitude;
    private String locationName;

    TextView locationInfo;
    TextView currentState;
    TextView hourSummary;
    TextView daySummaryText;
    Button advancedForecast;
    BasicForecastFetcher forecastFetcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_forecast);
        currentState = (TextView) findViewById(R.id.current_state);
        hourSummary = (TextView) findViewById(R.id.hour_summary);
        daySummaryText = (TextView) findViewById(R.id.day_summary);
        locationInfo = (TextView) findViewById(R.id.location);
        advancedForecast = (Button) findViewById(R.id.advanced_forecast_button);


        latitude = getIntent().getStringExtra("LATITUDE");
        longitude = getIntent().getStringExtra("LONGITUDE");
        locationName = getIntent().getStringExtra("LOCATION");

        forecastFetcher = new BasicForecastFetcher(getString(R.string.dark_sky_key), latitude, longitude);
    }

    @Override
    public void onResume() {
        super.onResume();
        setLocationText(latitude, longitude, locationName);
        new AsyncTask<Void, Void, BasicForecast>(){

            @Override
            protected BasicForecast doInBackground(Void... voids) {
                return forecastFetcher.fetchData();
            }

            @Override
            protected void onPostExecute(BasicForecast basicForecast) {
                currentState.setText(String.format("If you went outside, you would find it to be %d degrees with %s conditions.", basicForecast.getCurrentTemp(), basicForecast.getCurrentSummary()));
                hourSummary.setText(basicForecast.getHourSummary());
                daySummaryText.setText(basicForecast.getDaySummary());
            }
        }.execute();
    }


    private void setLocationText(String latitude, String longitude, String locationName){
        if(locationName != null) {
            locationInfo.setText(String.format("Forecast at %s,%s (%s)", latitude, longitude, locationName));
        } else {
            locationInfo.setText(String.format("Forecast at %s,%s", latitude, longitude));
        }
    }
}
