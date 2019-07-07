package com.lex.alcon.alejandro.lex.firebasecc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Persona {
    public String Nombre;
    public String ApPaterno;
    public String ApMaterno;
    private DatabaseReference mDatabase;
    public Persona()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public Persona(String nom,String appat,String apmat)
    {
        this.Nombre=nom;
        this.ApPaterno=appat;
        this.ApMaterno=apmat;
    }

}
