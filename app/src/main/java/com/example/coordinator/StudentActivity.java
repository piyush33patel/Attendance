package com.example.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    JSONObject jobj;
    JsonArrayRequest jar;

    ListView listView;
    HashMap<String,String> hashMap;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String, String>>();

    TextView allTV, culturalTV, technicalTV, sportsTV;
    EditText et;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        progressDialog = new ProgressDialog(StudentActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        allTV = (TextView)findViewById(R.id.all);
        culturalTV = (TextView)findViewById(R.id.cultural);
        technicalTV = (TextView)findViewById(R.id.technical);
        sportsTV = (TextView)findViewById(R.id.sports);
        et = (EditText)findViewById(R.id.edit_text);
        bt = (Button)findViewById(R.id.button);


        StringRequest stringRequest = new StringRequest(Constants.URL_REPORT_PARTICIPATED_COUNT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            jobj = new JSONObject(response);
                            String all = jobj.getString("total_participated_all");
                            String cultural = jobj.getString("total_participated_cultural");
                            String technical = jobj.getString("total_participated_technical");
                            String sports = jobj.getString("total_participated_sports");
                            allTV.setText(""+all);
                            culturalTV.setText(""+cultural);
                            technicalTV.setText(""+technical);
                            sportsTV.setText(""+sports);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestHandler.getInstance(StudentActivity.this).addToRequestQueue(stringRequest);

        allTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, Query.class);
                intent.putExtra("unique","Report_TotalParticipatedStudent.php");
                startActivity(intent);
            }
        });

        culturalTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, Query.class);
                intent.putExtra("unique","Report_TotalParticipatedCultural.php");
                startActivity(intent);
            }
        });

        technicalTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, Query.class);
                intent.putExtra("unique","Report_TotalParticipatedTechnical.php");
                startActivity(intent);
            }
        });

        sportsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, Query.class);
                intent.putExtra("unique","Report_TotalParticipatedSports.php");
                startActivity(intent);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et.getText().toString().trim();
                Intent intent = new Intent(StudentActivity.this, Query.class);
                intent.putExtra("uniques",title);
                startActivity(intent);
            }
        });


    }
}
