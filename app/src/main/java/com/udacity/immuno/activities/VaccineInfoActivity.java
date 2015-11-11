package com.udacity.immuno.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.immuno.R;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;
import com.udacity.immuno.Utility;
import com.udacity.immuno.utils.CircleTransform;

public class VaccineInfoActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VaccineData vaccineData = getIntent().getParcelableExtra("vaccineInfo");
        long userId = getIntent().getLongExtra("userId", DBHelper.getPrimaryUserId());

        //progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        //progressBar.setVisibility(View.VISIBLE);

        /* No Fab button in this Activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        String strCasualName = vaccineData.getCasualName();
        if(strCasualName == null){
            strCasualName = vaccineData.getFormalName();
        }
        collapsingToolbar.setTitle(strCasualName);

        TextView vaccineDesc = (TextView) findViewById(R.id.vaccine_description);
        ImageView picture = (ImageView) findViewById(R.id.image);
        vaccineDesc.setText(vaccineData.getDescription());
        if (vaccineData.getUserId()!=2000) {
            Picasso.with(this).load(Utility.randMicrobe()).transform(new CircleTransform()).into(picture);
        }
        else {
            Picasso.with(this).load(vaccineData.getLink()).transform(new CircleTransform()).into(picture);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            progressBar.setIndeterminate(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                if (params[0]!=null && !"empty".equals(params[0])) {
                    URL url = new URL(params[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    int statusCode = urlConnection.getResponseCode();

                    // 200 represents HTTP OK
                    if (statusCode == 200) {
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
                }
                else {
                    parseResult(new Dummy().getData());
                    result=1;
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new RecycleViewAdapter(getContext(), vaccineDataList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    */

}
