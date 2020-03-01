package com.example.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditingActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    TextView tv;
    Button bt;
    EditText et1, et2, et3, et4, et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        progressDialog = new ProgressDialog(EditingActivity.this);
        progressDialog.setMessage("Loading...");
        tv = (TextView)findViewById(R.id.title);
        bt = (Button)findViewById(R.id.done);
        et1 = (EditText)findViewById(R.id.venue);
        et2 = (EditText)findViewById(R.id.begin);
        et3 = (EditText)findViewById(R.id.finish);
        et4 = (EditText)findViewById(R.id.date);
        et5 = (EditText)findViewById(R.id.description);

        final String title = getIntent().getStringExtra("title");
        String venue = getIntent().getStringExtra("venue");
        String begin = getIntent().getStringExtra("begin");
        String finish = getIntent().getStringExtra("finish");
        String date = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("description");
        tv.setText(title);
        et1.setText(venue, TextView.BufferType.EDITABLE);
        et2.setText(begin, TextView.BufferType.EDITABLE);
        et3.setText(finish, TextView.BufferType.EDITABLE);
        et4.setText(date, TextView.BufferType.EDITABLE);
        et5.setText(description, TextView.BufferType.EDITABLE);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String venue = et1.getText().toString().trim();
                final String begin = et2.getText().toString().trim();
                final String finish = et3.getText().toString().trim();
                final String date = et4.getText().toString().trim();
                final String description = et5.getText().toString().trim();
                progressDialog.show();
                //function
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_UPDATE_EVENTS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("about",description);
                        params.put("date",date);
                        params.put("venue",venue);
                        params.put("begin",begin);
                        params.put("finish",finish);
                        params.put("title",title);
                        return params;
                    }
                };

                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                //function
            }
        });
    }
}
