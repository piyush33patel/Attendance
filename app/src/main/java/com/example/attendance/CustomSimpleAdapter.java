package com.example.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomSimpleAdapter extends ArrayAdapter<SubjectView> {

    int mResource;
    Context mContext;
    public CustomSimpleAdapter(Context context, int resource, List<SubjectView> objects) {
        super(context, resource, objects);
        mResource = resource;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String sno = getItem(position).getI();
        String date = getItem(position).getDate();
        String attendance = getItem(position).getAttendance();
        SubjectView subjectView = new SubjectView(sno, date, attendance);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView text1 = convertView.findViewById(R.id.text);
        TextView text2 = convertView.findViewById(R.id.text1);
        TextView text3 = convertView.findViewById(R.id.text2);
        text1.setText(sno);
        text2.setText(date);
        text3.setText(attendance);
        return convertView;
        }
    }