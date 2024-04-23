package com.example.exe14_2024.Helpers;


import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exe14_2024.R;

import java.util.ArrayList;

public class StuViewList extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;
    private LayoutInflater inflater;

    public StuViewList(Context context, ArrayList<Student> studentss){
        this.context = context;
        this.students = studentss;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount(){
        return students.toArray().length;
    }

    @Override
    public Object getItem(int pos){
        return students.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return pos;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent){
        view = inflater.inflate(R.layout.cstulis, parent, false);
        TextView cnamelis = view.findViewById(R.id.cnamelis);
        TextView cidslis = view.findViewById(R.id.cidlis);
        TextView cclslis = view.findViewById(R.id.cclslis);
        TextView cgrdlis = view.findViewById(R.id.cgrdlis);
        TextView cv1lis = view.findViewById(R.id.cv1lis);
        TextView cv2lis = view.findViewById(R.id.cv2lis);
        LinearLayout stulisll = view.findViewById(R.id.stulisll);
        cnamelis.setText(students.get(i).getLastName()+" "+students.get(i).getPrivateName());
        cidslis.setText(students.get(i).getId());
        cclslis.setText(students.get(i).getClss());
        cgrdlis.setText(students.get(i).getGrade());
        switch(students.get(i).getVacs()){
            case 0: cv1lis.setText("Cannot Be");cv1lis.setBackgroundColor(0xffff0000);cv1lis.setGravity(View.FOCUS_LEFT);cv2lis.setText("Vaccinated");cv2lis.setBackgroundColor(0xffff0000);cv2lis.setGravity(View.FOCUS_RIGHT);break;
            case 1: cv1lis.setText("Missing");cv1lis.setBackgroundColor(0xffff0000);cv1lis.setGravity(View.FOCUS_LEFT);cv2lis.setText("Missing");cv2lis.setBackgroundColor(0xffff0000);cv2lis.setGravity(View.FOCUS_RIGHT);break;
            case 2: cv1lis.setText("Done");cv1lis.setBackgroundColor(0xffff0000);cv1lis.setGravity(View.FOCUS_LEFT);cv2lis.setText("Missing");cv2lis.setBackgroundColor(0xff50C878);cv2lis.setGravity(View.FOCUS_RIGHT);break;
            case 3: cv1lis.setText("Done");cv1lis.setBackgroundColor(0xff50C878);cv1lis.setGravity(View.FOCUS_LEFT);cv2lis.setText("Done");cv2lis.setBackgroundColor(0xff50C878);cv2lis.setGravity(View.FOCUS_RIGHT);break;
        }
        if(i%2==1){stulisll.setBackgroundColor(0x4DBEBEBE);}else{stulisll.setBackgroundColor(0x00BEBEBE);}
        return view;
    }
}
