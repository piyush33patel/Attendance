package com.example.attendance;

public class Student
{
}
class Subject extends Student
{
    int total, present;
    Subject(int total, int present)
    {
        this.total = total;
        this.present = present;
    }
}

class DateWise
{
    String date, attendance;
    DateWise(String date, String attendance)
    {
        this.attendance = attendance;
        this.date = date;
    }
}

class SubjectView
{
    String i, date, attendance;
    SubjectView(String  i, String date, String attendance)
    {
        this.i = i;
        this.attendance = attendance;
        this.date = date;
    }
    public String getI()
    {
        return i;
    }
    public String getDate()
    {
        return date;
    }
    public String getAttendance()
    {
        return attendance;
    }

}
