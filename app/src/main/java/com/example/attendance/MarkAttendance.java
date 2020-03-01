package com.example.attendance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class MarkAttendance extends Fragment {
    RadioButton sub[] = new RadioButton[5];
    RadioButton subp[] = new RadioButton[5];
    RadioButton lab[] = new RadioButton[4];
    RadioButton labp[] = new RadioButton[4];
    Button submit, change, viewAttendance;
    TextView displayDate;
    DatabaseReference mDatabase;
    String date;
    EditText et;

    int present[] = new int[9];
    int absent[] =  new int[9];
    int total[] = new int[9];
    int i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark_attendance, container, false);

        sub[0] = (RadioButton) view.findViewById(R.id.sub1);
        sub[1] = (RadioButton) view.findViewById(R.id.sub2);
        sub[2] = (RadioButton) view.findViewById(R.id.sub3);
        sub[3] = (RadioButton) view.findViewById(R.id.sub4);
        sub[4] = (RadioButton) view.findViewById(R.id.sub5);
        subp[0] = (RadioButton) view.findViewById(R.id.subp1);
        subp[1] = (RadioButton) view.findViewById(R.id.subp2);
        subp[2] = (RadioButton) view.findViewById(R.id.subp3);
        subp[3] = (RadioButton) view.findViewById(R.id.subp4);
        subp[4] = (RadioButton) view.findViewById(R.id.subp5);

        lab[0] = (RadioButton) view.findViewById(R.id.lab1);
        lab[1] = (RadioButton) view.findViewById(R.id.lab2);
        lab[2] = (RadioButton) view.findViewById(R.id.lab3);
        lab[3] = (RadioButton) view.findViewById(R.id.lab4);
        labp[0] = (RadioButton) view.findViewById(R.id.labp1);
        labp[1] = (RadioButton) view.findViewById(R.id.labp2);
        labp[2] = (RadioButton) view.findViewById(R.id.labp3);
        labp[3] = (RadioButton) view.findViewById(R.id.labp4);

        submit = (Button) view.findViewById(R.id.submit);
        change = (Button) view.findViewById(R.id.change);
        viewAttendance = (Button) view.findViewById(R.id.viewAttendance);
        displayDate = (TextView) view.findViewById(R.id.displayDate);
        et = (EditText) view.findViewById(R.id.password);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Displays current date
        Calendar calendar = Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        displayDate.setText(date);


        //Enter desired date
        Bundle bundle = getArguments();
        if(getArguments() != null) {
            date = bundle.getString("data", "NULL");
            displayDate.setText(date);
        }

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutID, new CalendarFragment());
                fragmentTransaction.commit();
            }
        });

        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutID, new MainFragment());
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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = et.getText().toString();
                if (password.equals("PASSWORD")) {
                    for (i = 0; i < 9; i++) {
                        //check attendance
                        updateAttendance(i);
                        //update data of database
                        writeNewUser(i, total[i], present[i]);
                    }
                    Toast.makeText(getContext(), "Attendance Marked", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layoutID, new MainFragment());
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }



   public void updateAttendance(int i) {
        String attend = "Absent";
        if(i < 5) {
            if (sub[i].isChecked()) {
                total[i] = total[i] + 1;
                if (subp[i].isChecked()) {
                    present[i] = present[i] + 1;
                    attend = "Present";
                }
                dateWise(i, date, attend);
            }
        }
        else {
            int j = i - 5;
            if (lab[j].isChecked()) {
                total[i] = total[i] + 1;
                if (labp[i - 5].isChecked()) {
                    present[i] = present[i] + 1;
                    attend = "Present";
                }
                dateWise(i, date, attend);
            }
        }
    }

    private void writeNewUser(int i, int total, int present) {
        Subject sub = new Subject(total, present);
        switch (i){
            case 0:{
                mDatabase.child("student").child("8085").child("Attendance").setValue(sub);
                break;
            }
            case 1:{
                mDatabase.child("student").child("TOC").child("Attendance").setValue(sub);
                break;
            }
            case 2:{
                mDatabase.child("student").child("ALGO").child("Attendance").setValue(sub);
                break;
            }
            case 3:{
                mDatabase.child("student").child("DBMS").child("Attendance").setValue(sub);
                break;
            }
            case 4:{
                mDatabase.child("student").child("NM").child("Attendance").setValue(sub);
                break;
            }
            case 5:{
                mDatabase.child("student").child("8085P").child("Attendance").setValue(sub);
                break;
            }
            case 6:{
                mDatabase.child("student").child("ALGOP").child("Attendance").setValue(sub);
                break;
            }
            case 7:{
                mDatabase.child("student").child("DBMSP").child("Attendance").setValue(sub);
                break;
            }
            case 8:{
                mDatabase.child("student").child("NMP").child("Attendance").setValue(sub);
                break;
            }
        }

    }

    public void dateWise(int i, String date, String attend)
    {
        DateWise dateWise = new DateWise(date, attend);
        switch (i){
            case 0:{
                String id = mDatabase.child("student").child("8085").child("DateWise").push().getKey();
                mDatabase.child("student").child("8085").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 1:{
                String id = mDatabase.child("student").child("TOC").child("DateWise").push().getKey();
                mDatabase.child("student").child("TOC").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 2:{
                String id = mDatabase.child("student").child("ALGO").child("DateWise").push().getKey();
                mDatabase.child("student").child("ALGO").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 3:{
                String id = mDatabase.child("student").child("DBMS").child("DateWise").push().getKey();
                mDatabase.child("student").child("DBMS").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 4:{
                String id = mDatabase.child("student").child("NM").child("DateWise").push().getKey();
                mDatabase.child("student").child("NM").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 5:{
                String id = mDatabase.child("student").child("8085P").child("DateWise").push().getKey();
                mDatabase.child("student").child("8085P").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 6:{
                String id = mDatabase.child("student").child("ALGOP").child("DateWise").push().getKey();
                mDatabase.child("student").child("ALGOP").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 7:{
                String id = mDatabase.child("student").child("DBMSP").child("DateWise").push().getKey();
                mDatabase.child("student").child("DBMSP").child("DateWise").child(id).setValue(dateWise);
                break;
            }
            case 8:{
                String id = mDatabase.child("student").child("NMP").child("DateWise").push().getKey();
                mDatabase.child("student").child("NMP").child("DateWise").child(id).setValue(dateWise);
                break;
            }
        }

    }
}
