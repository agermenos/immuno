package com.udacity.immuno.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.udacity.immuno.R;
import com.udacity.immuno.Utility;
import com.udacity.immuno.adapters.CustomViewHolder;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;
import com.udacity.immuno.pojos.Country;
import com.udacity.immuno.pojos.CountryList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;

public class CountryActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = CountryActivity.class.getSimpleName();

    private Activity mActivity;

    private String _countryName;
    private Country localCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActivity = this;
        ButterKnife.bind(this);
        VaccineData vaccineData = getIntent().getParcelableExtra("vaccineInfo");
        //TextView countryName= (TextView) findViewById(R.id.country_name);
        //TextView countrySubtitle = (TextView) findViewById(R.id.country_subtitle);
        //countryName.setText(vaccineData.getCasualName());

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(Utility.capitalize(vaccineData.getCasualName()));
        toolbar.setSubtitle(vaccineData.getFormalName());

        //countrySubtitle.setText(vaccineData.getFormalName());
        ImageView picture = (ImageView) findViewById(R.id.header_image);
        Picasso.with(this).load(vaccineData.getLink()).into(picture);
        _countryName = vaccineData.getCasualName();
        new AsyncHttpTask().execute(_countryName);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> implements CustomViewHolder.CustomViewItemClickListener{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL("http://immuno-1125.appspot.com/country/" + params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) { //if previous call was successful
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1) {
                List<String> vaccineList = localCountry.getAllTravelers();
                vaccineList.addAll(localCountry.getMostTravelers());
                vaccineList.addAll(localCountry.getSomeTravelers());
                ListView mainListView = (ListView) findViewById( R.id.content_country );
                ListAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_vaccine_simple_item, vaccineList);
                mainListView.setAdapter(listAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onItemClicked(VaccineData vaccineData) {
            //on selection of Item
            Intent intent;
            if (vaccineData.getUserId()!=2000) {
                intent = new Intent(getApplicationContext(), VaccineInfoActivity.class);
            }
            else {
                intent = new Intent(getApplicationContext(), CountryActivity.class);
            }
            Log.d(TAG, "Vaccine Selected: " + vaccineData.getCasualName());
            intent.putExtra("vaccineInfo", vaccineData);
            intent.putExtra("userId", DBHelper.getPrimaryUserId());
            startActivity(intent);
        }
    }

    private void parseResult(String result) {
        CountryList countryList = new CountryList();
        countryList = new Gson().fromJson(result, CountryList.class);
        for (Country country : countryList.getCountries()) {
            try {
                if (country.getCountryName().equalsIgnoreCase(_countryName)) {
                    localCountry=country;
                    break;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
