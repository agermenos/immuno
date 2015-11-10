package com.udacity.immuno.activities;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.immuno.Dummy;
import com.udacity.immuno.R;
import com.udacity.immuno.adapters.RecycleViewAdapter;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SearchFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<VaccineData> finalDataList;

    public SearchFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "SearchFragment";
    private RecyclerView mRecyclerView;
    private RecycleViewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        return rootView;
    }

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
                    URL url = new URL("http://immuno-1125.appspot.com/vaccine");
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
                adapter = new RecycleViewAdapter(getContext(), finalDataList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSearch(String searchText){
        finalDataList = new ArrayList<>();
        VaccineData header = new VaccineData();
        progressBar.setVisibility(View.VISIBLE);
        // TODO Add header
        header.setStatus(1000);
        header.setCasualName(getString(R.string.most_relevant));
        finalDataList.addAll(DBHelper.searchVaccinesByName(searchText));
        // TODO Add header
        VaccineData header2 = new VaccineData();
        header2.setStatus(1000);
        header2.setCasualName(getString(R.string.travel_results));
        new AsyncHttpTask().execute(searchText);
       // new AsyncHttpTask().execute();
    }

    private void parseResult(String result) {
        List<VaccineData> vaccineDataList = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(result);
            JSONArray vaccines = response.optJSONArray("vaccines");

            Random r = new Random();

            for (int i = 0; i < vaccines.length() || i<=20; i++) {
                try {
                    JSONObject vaccine = vaccines.optJSONObject(i);
                    VaccineData item = new VaccineData();
                    // load item
                    item.setScheduleDate(new Date());
                    item.setStatus(r.nextInt(4));
                    item.setUserId(0);
                    item.setVaccineApiId(vaccine.getString("_ID"));
                    item.setFormalName(vaccine.getString("formal_name"));
                    vaccineDataList.add(item);
                }
                catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finalDataList.addAll(vaccineDataList);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
