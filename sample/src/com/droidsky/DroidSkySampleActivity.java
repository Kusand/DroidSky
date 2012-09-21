package com.droidsky;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.droidsky.fetcher.InterestingStormsFetcher;
import com.droidsky.model.InterestingStorms;

public class DroidSkySampleActivity extends Activity {

    private TextView text;
    private InterestingStormsFetcher interestingStormsFetcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text = (TextView) findViewById(R.id.text);
        interestingStormsFetcher = new InterestingStormsFetcher(getString(R.string.dark_sky_key));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, InterestingStorms>() {

            @Override
            protected InterestingStorms doInBackground(Void... voids) {
                return interestingStormsFetcher.getInterestingStorms();
            }

            @Override
            protected void onPostExecute(InterestingStorms interestingStorms) {
                text.setText(interestingStorms.getStorms()[0].getCity());
            }
        }.execute();
    }
}
