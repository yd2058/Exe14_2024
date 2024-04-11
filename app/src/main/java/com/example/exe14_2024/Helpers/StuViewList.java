package com.example.exe14_2024.Helpers;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exe14_2024.R;

public class StuViewList extends BaseAdapter {
    private Context context;
    private String names[], ids[];
    private int grades[], clss[];
    private boolean canvacs[], vac1s[], vac2s[];
    private LayoutInflater inflater;

    public StuViewList(Context context, String[] name, String[] id, boolean[] vac1, boolean[] vac2, int[] grade, int[] cls, boolean[] canvac){
        this.context = context;
        this.names = name;
        this.ids = id;
        this.vac1s = vac1;
        this.vac2s = vac2;
        this.grades = grade;
        this.clss = cls;
        this.canvacs = canvac;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount(){
        return names.length;
    }

    @Override
    public Object getItem(int pos){
        return ids[pos];
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
        cnamelis.setText(names[i]);
        cidslis.setText(ids[i]);
        cclslis.setText(clss[i]);
        cgrdlis.setText(grades[i]);
        if(!canvacs[i]){
            cv1lis.setText("Cannot Be");
            cv1lis.setTextColor(0xffff0000);
            cv1lis.setGravity(View.FOCUS_LEFT);
            cv2lis.setText("Vaccinated");
            cv2lis.setTextColor(0xffff0000);
            cv2lis.setGravity(View.FOCUS_RIGHT);
        }
        else{
            cv1lis.setText(vac1s[i]+"");
            cv1lis.setTextColor(0xff1B1B1F);
            cv1lis.setGravity(View.TEXT_ALIGNMENT_CENTER);
            cv2lis.setText(vac2s[i]+"");
            cv2lis.setTextColor(0xff1B1B1F);
            cv2lis.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }

        return view;
    }
}
