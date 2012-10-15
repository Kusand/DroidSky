package com.kusand.droidsky;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.kusand.droidsky.fetcher.AdvancedForecastFetcher;
import com.kusand.droidsky.model.AdvancedForecast;
import com.kusand.droidsky.model.HourlyPrecipitationForecast;
import com.kusand.graphview.GraphView;
import com.kusand.graphview.GraphViewSeries;

public class DroidSkySampleAdvancedForecastActivity extends Activity {

    private String latitude;
    private String longitude;
    private String locationName;

    TextView locationInfo;
    TextView currentState;
    TextView hourSummary;
    TextView daySummaryText;
    Button advancedForecast;
    AdvancedForecastFetcher forecastFetcher;
    private GraphView graphView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_forecast);
        currentState = (TextView) findViewById(R.id.current_state);
        hourSummary = (TextView) findViewById(R.id.hour_summary);
        daySummaryText = (TextView) findViewById(R.id.day_summary);
        locationInfo = (TextView) findViewById(R.id.location);
        advancedForecast = (Button) findViewById(R.id.advanced_forecast_button);
        graphView = (GraphView) findViewById(R.id.hourly_probability_graph);


        latitude = getIntent().getStringExtra("LATITUDE");
        longitude = getIntent().getStringExtra("LONGITUDE");
        locationName = getIntent().getStringExtra("LOCATION");

        forecastFetcher = new AdvancedForecastFetcher(getString(R.string.dark_sky_key), latitude, longitude);
    }

    @Override
    public void onResume() {
        super.onResume();
        setLocationText(latitude, longitude, locationName);
        new AsyncTask<Void, Void, AdvancedForecast>(){

            @Override
            protected AdvancedForecast doInBackground(Void... voids) {
                return forecastFetcher.fetchData();
            }

            @Override
            protected void onPostExecute(AdvancedForecast advancedForecast) {
                currentState.setText(String.format("If you went outside, you would find it to be %d degrees with %s conditions.", advancedForecast.getCurrentTemp(), advancedForecast.getCurrentSummary()));
                hourSummary.setText(advancedForecast.getHourSummary());
                daySummaryText.setText(advancedForecast.getDaySummary());
                GraphViewSeries hourlyForecastSeries = new GraphViewSeries("hourly forecasts", null, new GraphView.GraphViewData[0]);
                for(HourlyPrecipitationForecast forecast : advancedForecast.getHourlyPrecipitationForecasts()) {
                    hourlyForecastSeries.appendData(new GraphView.GraphViewData(forecast.getTime(), forecast.getProbability()), false);
                }
                graphView.addSeries(hourlyForecastSeries);
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
