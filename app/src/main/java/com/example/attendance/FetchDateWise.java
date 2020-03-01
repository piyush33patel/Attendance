package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FetchDateWise extends AppCompatActivity {

    int j = 0;
    DatabaseReference mDatabase;

    ListView listView;
    ArrayList<SubjectView> arrayList = new ArrayList<>();
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_date_wise);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.listview);
        j = getIntent().getIntExtra("key", 10);

        switch (j) {
            case 0: {
                subject = "8085";
                break;
            }

            case 1: {
                subject = "TOC";
                break;
            }
            case 2: {
                subject = "ALGO";
                break;
            }
            case 3: {
                subject = "DBMS";
                break;
            }
            case 4: {
                subject = "NM";
                break;
            }
            case 5: {
                subject = "8085P";
                break;
            }
            case 6: {
                subject = "ALGOP";
                break;
            }
            case 7: {
                subject = "DBMSP";
                break;
            }
            case 8: {
                subject = "NMP";
                break;
            }

        }

try {
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            int count = 1;
            for (DataSnapshot ds : dataSnapshot.child("student").child(subject).child("DateWise").getChildren()) {
                String id = ds.getKey();
                String date = dataSnapshot.child("student").child(subject).child("DateWise").child(id).child("date").getValue(String.class);
                String attendance = dataSnapshot.child("student").child(subject).child("DateWise").child(id).child("attendance").getValue(String.class);
                String sno = "" + count;
                count++;
                SubjectView ob = new SubjectView(sno, date, attendance);
                arrayList.add(ob);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    });
}
catch (Exception e)
{
    Toast.makeText(getApplicationContext(), "Null exception", Toast.LENGTH_SHORT).show();
}

        CustomSimpleAdapter csa = new CustomSimpleAdapter(getApplicationContext(), R.layout.list_item, arrayList);
        listView.setAdapter(csa);
    }
}