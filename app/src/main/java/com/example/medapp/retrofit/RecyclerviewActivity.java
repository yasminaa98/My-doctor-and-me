package com.example.medapp.retrofit;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;


import com.example.medapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerviewActivity extends AppCompatActivity {
    private ListView listView;
    String firstName,lastName,region;
    ArrayList<HashMap<String, String>> doctors_list;
    private static final String JSON_URL = "https://run.mocky.io/v3/c5da42ce-f9e3-48c1-9263-bbfbdd5eefea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        doctors_list=new ArrayList<>();
        listView=findViewById(R.id.listview);
        GetData getData=new GetData();
        getData.execute();


    }
    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder current= new StringBuilder();
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    int data = isr.read();
                    while (data != -1) {
                        current.append((char) data);
                        data = isr.read();
                    }
                    return current.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

                return current.toString();

            }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("doctors");
                for (int i =0; i<jsonArray.length();i++) {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    firstName=jsonObject1.getString("firstName");
                    lastName=jsonObject1.getString("lastName");
                    region=jsonObject1.getString("region");
                    HashMap<String,String> doctors=new HashMap<>();
                    doctors.put("firstName",firstName);
                    doctors.put("lastName",lastName);
                    doctors.put("region",region);
                    doctors_list.add(doctors);

            }
        } catch (JSONException e) {
                e.printStackTrace();
            }
            ListAdapter adapter=new SimpleAdapter(RecyclerviewActivity.this,doctors_list,R.layout.rowlayout,
                    new String[]{"firstName","lastName","region"},
                new int[]{R.id.fname,R.id.lname,R.id.reg});
            listView.setAdapter(adapter);
        }
    }
}