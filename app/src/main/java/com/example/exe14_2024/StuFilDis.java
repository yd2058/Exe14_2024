package com.example.exe14_2024;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	1.0
 * @since		9/4/2024
 * activity used to see students in the database with filtering abilities
 */
import static com.example.exe14_2024.Helpers.FBhelp.fbref;
import static com.example.exe14_2024.Helpers.FBhelp.tmpstrnsf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exe14_2024.Helpers.StuViewList;
import com.example.exe14_2024.Helpers.Student;
import com.example.exe14_2024.Helpers.Vaccine;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StuFilDis extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    ListView stulisfil;
    StuViewList stuadpfil;
    Student tmpst;
    int grdslc =0, filtslc = 0;
    private ArrayList<Student> students;
    Spinner filt, grade;
    EditText clsnumfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_fil_dis);
        Log.i("oncreate", "oncreate");
        stulisfil = findViewById(R.id.stulisfil);
        filt = findViewById(R.id.filt);
        grade = findViewById(R.id.grade);
        clsnumfil = findViewById(R.id.clsnumfil);

    }

    @Override
    protected void onResume() {
        Log.i("onres", "onres");
        super.onResume();
        students = new ArrayList<>();
        search(findViewById(R.id.srchbtn));
        stuadpfil = new StuViewList(this, students);
        stulisfil.setAdapter(stuadpfil);
        stulisfil.setOnItemClickListener(this);
        filt.setOnItemSelectedListener(this);
        grade.setOnItemSelectedListener(this);
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
    /**
     * reads a whole class in the school.
     * <p>
     *
     * @param	ds refers to current class being read.
     * @param unvacableonly refers to whether to return only students that cannot be vaccinated.
     */

    public void readcls(DataSnapshot ds, boolean unvacableonly){
        Query query = ds.getRef().orderByChild("lastName");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot kid : snapshot.getChildren()) {
                    tmpst = kid.getValue(Student.class);
                    tmpst.setVac1(kid.child("vac1").getValue(Vaccine.class));
                    tmpst.setVac2(kid.child("vac2").getValue(Vaccine.class));
                    if (unvacableonly) {
                        if (tmpst.getVacs() == 3) students.add(tmpst);
                    } else {
                        students.add(tmpst);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        }

    /**
     * reads a whole grade in the school.
     * <p>
     *
     * @param	ds refers to current class being read.
     * @param unvacableonly refers to whether to return only students that cannot be vaccinated.
     */
    public void readgrd(DataSnapshot ds, boolean unvacableonly){
        for (DataSnapshot cls: ds.getChildren()){readcls(cls, unvacableonly);}
    }
    /**
     * reads a whole school.
     * <p>
     *
     * @param	ds refers to current class being read.
     * @param unvacableonly refers to whether to return only students that cannot be vaccinated.
     */
    public void readschool(DataSnapshot ds, boolean unvacableonly){for(DataSnapshot grd: ds.getChildren()){readgrd(grd,unvacableonly);}}
    /**
     * reacts to selection of grade in spinner.
     * <p>
     *
     * @param	adapterView Description	refers to which spinner was used.
     * @param	view Description	refers t which cell within the spinner was selected.
     * @param	i Description	refers to position in list selected.
     * @param	l Description	refers to cell id.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.i("selecfilt", String.valueOf(adapterView.getId()==R.id.filt));
        Log.i("selecgrd", String.valueOf(adapterView.getId()==R.id.grade));
        if(adapterView.getId()==R.id.filt){filtslc = i;Log.i("filtslc", String.valueOf(filtslc));
        switch (filtslc){
            case 0: grade.setForeground(getDrawable(R.drawable.foreground)); grade.setEnabled(false);clsnumfil.setForeground(getDrawable(R.drawable.foreground));clsnumfil.setEnabled(false);break;
            case 1: grade.setForeground(null); grade.setEnabled(true);clsnumfil.setForeground(getDrawable(R.drawable.foreground));clsnumfil.setEnabled(false);break;
            case 2: grade.setForeground(null); grade.setEnabled(true);clsnumfil.setForeground(null);clsnumfil.setEnabled(true);break;
            case 3:grade.setForeground(getDrawable(R.drawable.foreground)); grade.setEnabled(false);clsnumfil.setForeground(getDrawable(R.drawable.foreground));clsnumfil.setEnabled(false);break;
        }
        }
        if(adapterView.getId()==R.id.grade){grdslc = i+6;Log.i("grdslc", String.valueOf(grdslc));}
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /**
     * applies reading filter.
     * <p>
     *
     * @param	view Description	refers to view element clicked.
     */
    public void search(View view) {
        Log.i("search", "search");
        fbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                students.clear();
                Log.i("search","read");
                switch(filtslc){
                    case 0: readschool(ds, false) ; break;// all school
                    case 1: if(grdslc>=7&&grdslc<=12){readgrd(ds.child(grdslc+""), false);} break;// specific grade
                    case 2: if(grdslc>=7&&grdslc<=12&&clsnumfil.getText().toString().matches("\\d+")){readcls(ds.child(grdslc+"").child(clsnumfil.getText().toString()), false);} break;// specific class
                    case 3:readschool(ds, true); break;// only those who cant be vaccinated
                }
                stuadpfil.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /**
     * reacts to selection of student from listview to edit/delete.
     * <p>
     *
     * @param	adapterView Description	refers to which spinner was used.
     * @param	view Description	refers t which cell within the spinner was selected.
     * @param	i Description	refers to position in list selected.
     * @param	l Description	refers to cell id.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent si = new Intent(this, StuInEd.class);
        si.putExtra("edit", true);
        tmpstrnsf = students.get(i);
        startActivity(si);

    }

}
