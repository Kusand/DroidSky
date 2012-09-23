package com.droidsky;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.droidsky.fetcher.InterestingStormsFetcher;
import com.droidsky.model.InterestingStorms;
import com.droidsky.model.Storm;

public class DroidSkySampleActivity extends Activity {

    private ListView stormList;
    private InterestingStormsFetcher interestingStormsFetcher;
    private Storm[] storms = new Storm[1];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interesting_storms);
        stormList = (ListView) findViewById(R.id.interesting_storm_list);
        interestingStormsFetcher = new InterestingStormsFetcher(getString(R.string.dark_sky_key));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, InterestingStorms>() {

            @Override
            protected InterestingStorms doInBackground(Void... voids) {
                return interestingStormsFetcher.fetchData();
            }

            @Override
            protected void onPostExecute(InterestingStorms interestingStorms) {
                storms = interestingStorms.getStorms();
                stormList.setAdapter(new StormAdapter(DroidSkySampleActivity.this, 0, storms));
            }
        }.execute();
    }

    private class StormAdapter extends ArrayAdapter<Storm> {


        public StormAdapter(Context context, int textViewResourceId, Storm[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View newView = getLayoutInflater().inflate(R.layout.interesting_storm_item, viewGroup, false);

            TextView cityText = (TextView) newView.findViewById(R.id.storm_city);
            cityText.setText(getItem(i).getCity());
            return newView;
        }

    }
}
