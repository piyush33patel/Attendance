package com.example.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainFragment extends Fragment {

    DatabaseReference mDatabase;
    Button markAttendance;
    int z;
    int present[] = new int[9];
    int total[] = new int[9];
    int absent[] = new int[9];
    int percent[] = new int[9];
    TextView tvPresent[] = new TextView[9];
    TextView tvTotal[] = new TextView[9];
    TextView tvAbsent[] = new TextView[9];
    TextView tvPercent[] = new TextView[9];
    TableRow tableRow[] = new TableRow[9];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        markAttendance = (Button) view.findViewById(R.id.markAttendance);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // find view by id
        for (int i = 0 ; i < 9 ; i ++)
        {
            String presentID = "present" + (i+1);
            String totalID = "total" + (i+1);
            String absentID = "absent" + (i+1);
            String percentID = "age" + (i+1);
            int PresID = getResources().getIdentifier(presentID, "id", getContext().getPackageName());
            tvPresent[i] = (TextView) view.findViewById(PresID);
            int AresID = getResources().getIdentifier(absentID, "id", getContext().getPackageName());
            tvAbsent[i] = (TextView) view.findViewById(AresID);
            int resTID = getResources().getIdentifier(totalID, "id", getContext().getPackageName());
            tvTotal[i] = (TextView) view.findViewById(resTID);
            int resPID = getResources().getIdentifier(percentID, "id", getContext().getPackageName());
            tvPercent[i] = (TextView) view.findViewById(resPID);

        }


        markAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutID, new MarkAttendance());
                fragmentTransaction.commit();
            }
        });


        //get data from database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer tot1 = dataSnapshot.child("student").child("8085").child("Attendance").child("total").getValue(Integer.class);
                Integer pre1 = dataSnapshot.child("student").child("8085").child("Attendance").child("present").getValue(Integer.class);
                present[0] = pre1;
                total[0] = tot1;

                Integer tot2 = dataSnapshot.child("student").child("TOC").child("Attendance").child("total").getValue(Integer.class);
                Integer pre2 = dataSnapshot.child("student").child("TOC").child("Attendance").child("present").getValue(Integer.class);
                present[1] = pre2;
                total[1] = tot2;

                Integer tot3 = dataSnapshot.child("student").child("ALGO").child("Attendance").child("total").getValue(Integer.class);
                Integer pre3 = dataSnapshot.child("student").child("ALGO").child("Attendance").child("present").getValue(Integer.class);
                present[2] = pre3;
                total[2] = tot3;

                Integer tot4 = dataSnapshot.child("student").child("DBMS").child("Attendance").child("total").getValue(Integer.class);
                Integer pre4 = dataSnapshot.child("student").child("DBMS").child("Attendance").child("present").getValue(Integer.class);
                present[3] = pre4;
                total[3] = tot4;

                Integer tot5 = dataSnapshot.child("student").child("NM").child("Attendance").child("total").getValue(Integer.class);
                Integer pre5 = dataSnapshot.child("student").child("NM").child("Attendance").child("present").getValue(Integer.class);
                present[4] = pre5;
                total[4] = tot5;

                Integer tot6 = dataSnapshot.child("student").child("8085P").child("Attendance").child("total").getValue(Integer.class);
                Integer pre6 = dataSnapshot.child("student").child("8085P").child("Attendance").child("present").getValue(Integer.class);
                present[5] = pre6;
                total[5] = tot6;

                Integer tot7 = dataSnapshot.child("student").child("ALGOP").child("Attendance").child("total").getValue(Integer.class);
                Integer pre7 = dataSnapshot.child("student").child("ALGOP").child("Attendance").child("present").getValue(Integer.class);
                present[6] = pre7;
                total[6] = tot7;

                Integer tot8 = dataSnapshot.child("student").child("DBMSP").child("Attendance").child("total").getValue(Integer.class);
                Integer pre8 = dataSnapshot.child("student").child("DBMSP").child("Attendance").child("present").getValue(Integer.class);
                present[7] = pre8;
                total[7] = tot8;

                Integer tot9 = dataSnapshot.child("student").child("NMP").child("Attendance").child("total").getValue(Integer.class);
                Integer pre9 = dataSnapshot.child("student").child("NMP").child("Attendance").child("present").getValue(Integer.class);
                present[8] = pre9;
                total[8] = tot9;

                //calculate absent and percent
                for (int i = 0 ; i < 9 ; i++)
                {
                    absent[i] = total[i] - present[i];
                    if (total[i] != 0)
                    {
                        percent[i] = (present[i] * 100 ) / total[i];
                    }
                    tvPresent[i].setText("" + present[i]);
                    tvAbsent[i].setText("" + absent[i]);
                    tvTotal[i].setText("" + total[i]);
                    tvPercent[i].setText("" + percent[i] + "%");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        for (z = 0 ; z < 9 ; z++)
        {

            String presentID = "row" + (z+1);
            int rowID = getResources().getIdentifier(presentID, "id", getContext().getPackageName());
            tableRow[z] = (TableRow) view.findViewById(rowID);

        }


    //individual click listener starts here
        tableRow[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 0);
                startActivity(intent);
            }
        });

        tableRow[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 1);
                startActivity(intent);
            }
        });

        tableRow[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 2);
                startActivity(intent);
            }
        });


        tableRow[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 3);
                startActivity(intent);
            }
        });


        tableRow[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 4);
                startActivity(intent);
            }
        });


        tableRow[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 5);
                startActivity(intent);
            }
        });


        tableRow[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 6);
                startActivity(intent);
            }
        });


        tableRow[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 7);
                startActivity(intent);
            }
        });


        tableRow[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FetchDateWise.class);
                intent.putExtra("key", 8);
                startActivity(intent);
            }
        });
        //individual click listener starts here

                return view;
    }
}
