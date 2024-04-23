package com.example.exe14_2024.Helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBhelp {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference fbref = FBDB.getReference();

    public static Student tmpstrnsf;//temporary student for activity transfer




}
