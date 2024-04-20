package com.example.exe14_2024;

import static com.google.android.material.R.color.design_default_color_secondary;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exe14_2024.Helpers.Student;
import com.example.exe14_2024.Helpers.Vaccine;
import com.google.firebase.database.FirebaseDatabase;

public class StuInEd extends AppCompatActivity implements View.OnCreateContextMenuListener{
    TextView tvid, tvfn, tvln, tvv1, tvv2, tvcls;
    EditText stid, stfn, stln, clsnum, v1d, v1m, v1y, v1l, v2d, v2m, v2y, v2l;
    Spinner grspin;
    ToggleButton vacbtn;
    Button sbtn;
    Boolean takin, edit = false;
    int grdslc=0;
    String day1, day2, month1, month2, year1, year2;
    Student tempst;
    Vaccine tmpvac1, tmpvac2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuined);
        initiall();
    }

    private void initiall() {
        tvid = findViewById(R.id.tvid);
        tvfn = findViewById(R.id.tvfn);
        tvln = findViewById(R.id.tvln);
        tvcls = findViewById(R.id.tvcls);
        tvv1 = findViewById(R.id.tvv1);
        tvv2 = findViewById(R.id.tvv2);
        stid = findViewById(R.id.stid);
        stfn = findViewById(R.id.stfn);
        stln = findViewById(R.id.stln);
        clsnum = findViewById(R.id.clsnum);
        v1d = findViewById(R.id.v1d);
        v1m = findViewById(R.id.v1m);
        v1y = findViewById(R.id.v1y);
        v1l = findViewById(R.id.v1l);
        v2d = findViewById(R.id.v2d);
        v2m = findViewById(R.id.v2m);
        v2y = findViewById(R.id.v2y);
        v2l = findViewById(R.id.v2l);
        grspin = findViewById(R.id.grspin);
        vacbtn = findViewById(R.id.vacbtn);
        sbtn = findViewById(R.id.sbtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * reacts to item selection.
     * <p>
     *
     * @param	item Description	refers to the selected menu item.
     * @return	Description			returns true.
     */

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.cmcr){
            Intent si = new Intent(this,Credits.class);
            startActivity(si);
        }
        else if (id ==R.id.cmsv) {
            Intent si = new Intent(this, StuDis.class);
            startActivity(si);
        }
        else if (id ==R.id.cmsfv) {
            Intent si = new Intent(this, StuFilDis.class);
            startActivity(si);
        }
        return true;
    }

    public void stuin(View view) {
        takin = true;
        day1 = v1d.getText().toString();
        day2 = v2d.getText().toString();
        month1 = v1m.getText().toString();
        month2 = v2m.getText().toString();
        year1 = v1y.getText().toString();
        year2 = v2y.getText().toString();
        if((!stid.getText().toString().matches("\\d+"))||stid.getText().toString().isEmpty()){takin = false;tvid.setTextColor(0xffff0000);}//checks if the id contains only digits
        else{tvid.setTextColor(0xff1B1B1F);}
        if(stfn.getText().toString().isEmpty()){takin = false;tvfn.setTextColor(0xffff0000);}//checks if there is a first name
        else{tvfn.setTextColor(0xff1B1B1F);}
        if(stln.getText().toString().isEmpty()){takin = false;tvln.setTextColor(0xffff0000);}//checks if there is a last name
        else{tvln.setTextColor(0xff1B1B1F);}
        if(clsnum.getText().toString().isEmpty()){takin = false;tvcls.setTextColor(0xffff0000);}//checks if there is a class num
        else{tvcls.setTextColor(0xff1B1B1F);}
        if(grdslc<7||grdslc>12){takin = false;tvcls.setTextColor(0xffff0000);}//checks if there is a valid grade
        else{tvcls.setTextColor(0xff1B1B1F);}
        if(vacbtn.isChecked()) {
            if ((!day1.matches("\\d+")) || day1.isEmpty() || Integer.parseInt(day1) < 1 || Integer.parseInt(day1) > 31) {
                takin = false;
                v1d.setHighlightColor((0xffff0000));
            }//checks if vaccine 1 is on a valid day
            else {
                v1d.setHighlightColor(0xff1B1B1F);
            }
            if ((!month1.matches("\\d+")) || month1.isEmpty() || Integer.parseInt(month1) < 1 || Integer.parseInt(month1) > 12) {
                takin = false;
                v1m.setHighlightColor(0xffff0000);
            }//checks if vaccine 1 is on a valid month
            else {
                v1m.setHighlightColor(0xff1B1B1F);
            }
            if ((!year1.matches("\\d+")) || year1.isEmpty()) {
                takin = false;
                v1y.setHighlightColor(0xffff0000);
            }//checks if vaccine 1 is on a valid year
            else {
                v1y.setHighlightColor(0xff1B1B1F);
            }
            if (v1l.getText().toString().isEmpty()) {
                takin = false;
                v1l.setHighlightColor(0xffff0000);
            }//checks if vaccine 1 has a location
            else {
                v1l.setHighlightColor(0xff1B1B1F);
            }
            if ((!day2.matches("\\d+")) || day2.isEmpty() || Integer.parseInt(day2) < 1 || Integer.parseInt(day2) > 31) {
                takin = false;
                v2d.setHighlightColor(0xffff0000);
            }//checks if vaccine 2 is on a valid day
            else {
                v2d.setHighlightColor(0xff1B1B1F);
            }
            if ((!month2.matches("\\d+")) || month2.isEmpty() || Integer.parseInt(month2) < 1 || Integer.parseInt(month2) > 12) {
                takin = false;
                v2m.setHighlightColor(0xffff0000);
            }//checks if vaccine 2 is on a valid month
            else {
                v2m.setHighlightColor(0xff1B1B1F);
            }
            if ((!year2.matches("\\d+")) || year2.isEmpty()) {
                takin = false;
                v2y.setHighlightColor(0xffff0000);
            }//checks if vaccine 2 is on a valid year
            else {
                v2y.setHighlightColor(0xff1B1B1F);
            }
            if (v2l.getText().toString().isEmpty()) {
                takin = false;
                v2l.setHighlightColor(0xffff0000);
            }//checks if vaccine 2 has a location
            else {
                v2l.setHighlightColor(0xff1B1B1F);
            }
            if (isodmytoymd(Integer.parseInt(day2), Integer.parseInt(month2), Integer.parseInt(year2)) < isodmytoymd(Integer.parseInt(day1), Integer.parseInt(month1), Integer.parseInt(year1))) {
                takin = false;
                tvv2.setHighlightColor(0xffff0000);
                Toast.makeText(this, "Invalid  spacing since vaccine 1", Toast.LENGTH_SHORT).show();
            }//checks if vaccine 2 is on valid spacing from vaccine 1
            else {
                tvv2.setHighlightColor(0xff1B1B1F);
            }
        }

        if(takin){
            if(edit){}//edit previous
            else{}//save new
        }
        else{
            Toast.makeText(this, "reddened values invalid", Toast.LENGTH_SHORT).show();
        }
    }
    
    public int isodmytoymd(int convday, int convmonth, int convyear){
        int res;
        if(convmonth==13){convyear++;convmonth = 1;}
        res = convyear*10000+convmonth*100+convday;
        return res;
    }
}