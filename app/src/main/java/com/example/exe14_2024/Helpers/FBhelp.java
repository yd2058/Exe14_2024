package com.example.exe14_2024.Helpers;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	1.1
 * @since		10/4/2024
 * activity used to assist student transfer between activities and to manage easily database references
 */
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBhelp {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference fbref = FBDB.getReference();

    public static Student tmpstrnsf;//temporary student for activity transfer




}
