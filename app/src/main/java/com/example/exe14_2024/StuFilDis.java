package com.example.exe14_2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exe14_2024.Helpers.StuViewList;
import com.example.exe14_2024.Helpers.Student;

import java.util.ArrayList;

public class StuFilDis extends AppCompatActivity implements View.OnCreateContextMenuListener{
    ListView stulisfil;
    StuViewList stuadpfil;

    private ArrayList<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_fil_dis);

        stulisfil = findViewById(R.id.stulisfil);

    }

    @Override
    protected void onResume() {
        super.onResume();
        readstu();
        stuadpfil = new StuViewList(this, students);
        stulisfil.setAdapter(stuadpfil);
    }

    private void readstu() {
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
        else if (id ==R.id.cmsi) {
            Intent si = new Intent(this, StuInEd.class);
            startActivity(si);
        }
        return true;
    }
}