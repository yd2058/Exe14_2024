package com.example.exe14_2024.Helpers;

public class Student {
    private String id, privateName, lastName;
    private int grade, clss;
    private Vaccine vac1, vac2;
    private int vacs = 3;//3 = unvaccinable, 2 = unvacced, 1 = vacced once, 0= vacced twice
    private boolean canVaccinate;

    public Student(String id,String privateName, String lastName, int grade, int clss, Vaccine vac1, Vaccine vac2, boolean canVaccinate){
        this.id = id;
        this.privateName = privateName;
        this.lastName = lastName;
        this.grade = grade;
        this.clss = clss;
        this.vac1 = vac1;
        this.vac2 = vac2;
        this.canVaccinate = canVaccinate;
        if(canVaccinate){
            vacs--;
            if(vac1!=null){
                vacs--;
                if(vac2!=null){vacs--;}
            }
        }


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }
    public boolean getCanVaccinate() {
        return canVaccinate;
    }

    public void setCanVaccinate(boolean canVaccinate) {
        this.canVaccinate = canVaccinate;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClss() {
        return clss;
    }

    public void setClss(int clss) {
        this.clss = clss;
    }

    public Vaccine getVac1() {
        return vac1;
    }

    public void setVac1(Vaccine vac1) {
        this.vac1 = vac1;
        updvacs();
    }

    public Vaccine getVac2() {
        return vac2;
    }

    public void setVac2(Vaccine vac2) {
        this.vac2 = vac2;
        updvacs();
    }




    public int getVacs() {
        return vacs;
    }

    public void setVacs(int vacs) {
        this.vacs = vacs;
    }

    public Student(){}

    private void updvacs(){
        vacs = 3;
        if(canVaccinate){
            vacs--;
            if(vac1!=null){
                vacs--;
                if(vac2!=null){vacs--;}
            }
        }
    }
}
