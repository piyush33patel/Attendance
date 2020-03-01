package com.example.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CulturalEvents extends AppCompatActivity {

    private ProgressDialog progressDialog;
    ListView listView;
    JSONObject jobj;
    JsonArrayRequest jar;
    HashMap<String,String> hashMap;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String, String>>();
    //adding
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> venue = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> begin = new ArrayList<>();
    ArrayList<String> finish = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_events);

        progressDialog = new ProgressDialog(CulturalEvents.this);
        listView = (ListView)findViewById(R.id.list_view);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        jar = new JsonArrayRequest(Constants.URL_FETCH_CULTURAL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for(int i = 0 ; i<response.length() ; i++)
                {
                    try
                    {
                        hashMap = new HashMap<>();
                        jobj = response.getJSONObject(i);
                        String s1 = jobj.getString("title");
                        String s2 = jobj.getString("about");
                        String s3 = jobj.getString("date");
                        String s4 = jobj.getString("venue");
                        String s5 = jobj.getString("begin") + " to " + jobj.getString("finish");
                        hashMap.put("title", s1);
                        hashMap.put("about", s2);
                        hashMap.put("date",s3);
                        hashMap.put("venue", s4);
                        hashMap.put("time", s5);
                        arrayList.add(hashMap);
                        title.add(s1);
                        description.add(s2);
                        date.add(s3);
                        venue.add(s4);
                        begin.add(jobj.getString("begin"));
                        finish.add(jobj.getString("finish"));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                String[] from = {"title", "about", "date", "venue", "time"};
                int[] to = {R.id.event_title, R.id.event_description, R.id.event_date, R.id.event_venue, R.id.event_duration};
                CustomSimpleAdapter csa = new CustomSimpleAdapter(getApplicationContext(),arrayList, R.layout.list_items, from, to);
                listView.setAdapter(csa);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(jar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CulturalEvents.this, EditingActivity.class);
                intent.putExtra("title", title.get(i));
                intent.putExtra("date", date.get(i));
                intent.putExtra("venue", venue.get(i));
                intent.putExtra("begin", begin.get(i));
                intent.putExtra("finish", finish.get(i));
                intent.putExtra("description", description.get(i));
                startActivity(intent);
            }
        });

    }
}
