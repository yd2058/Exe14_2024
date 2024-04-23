package com.example.exe14_2024;

import static com.example.exe14_2024.Helpers.FBhelp.tmpstrnsf;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exe14_2024.Helpers.StuViewList;
import com.example.exe14_2024.Helpers.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StuDis extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemSelectedListener {
    ListView stulis;
    StuViewList stuadp;
    final public FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    private ArrayList<Student> students;
    ValueEventListener stulisten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_dis);

        stulis = findViewById(R.id.stulis);


    }

    @Override
    protected void onResume() {
        super.onResume();
        readstu();
        stuadp = new StuViewList(this, students);
        stulis.setAdapter(stuadp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(stulisten!=null){
            FBDB.getReference().removeEventListener(stulisten);
        }
    }

    private void readstu() {
        stulisten = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                students.clear();
                for(DataSnapshot grades : dS.getChildren()) {
                    for(DataSnapshot classes : grades.getChildren()){
                        for(DataSnapshot kid: classes.getChildren()){
                            students.add(kid.getValue(Student.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StuDis.this, "An error has occurred, please make sure you are connected to the internet", Toast.LENGTH_SHORT).show();
            }
        };
        FBDB.getReference().addValueEventListener(stulisten);
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
        else if (id ==R.id.cmsi) {
            Intent si = new Intent(this, StuInEd.class);
            startActivity(si);
        }
        else if (id ==R.id.cmsfv) {
            Intent si = new Intent(this, StuFilDis.class);
            startActivity(si);
        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Intent si = new Intent(this, StuInEd.class);
        si.putExtra("edit", true);
        tmpstrnsf = students.get(i);
        startActivity(si);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}