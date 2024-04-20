package com.example.exe14_2024.Helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {

    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();

    public static DatabaseReference refvac2=FBDB.getReference("vac2");
    public static DatabaseReference refvac1=FBDB.getReference("vac1");
    public static DatabaseReference refunvacced=FBDB.getReference("unvacced");
    public static DatabaseReference refunvaccable=FBDB.getReference("unvaccable");

}
