package com.lex.alcon.alejandro.lex.firebasecc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    EditText etxtnom,etxtappat,etxtapmat;
    EditText id;
    long limit=0;
    String key="";
    String all="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtnom=findViewById(R.id.editText);
        etxtappat=findViewById(R.id.editText2);
        etxtapmat=findViewById(R.id.editText3);
        id=findViewById(R.id.editText4);

        mDatabase = FirebaseDatabase.getInstance().getReference();



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                limit=dataSnapshot.child("persona").getChildrenCount();



                for (int i=1;i<=limit;i++)
                {
                    all=all+dataSnapshot.child("persona").child(i+"").child("Nombre").getValue()+"\n";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });




    }
    public void guardar(View view)
    {
        Persona persona = new Persona(etxtnom.getText().toString(),
                etxtappat.getText().toString(),
                etxtapmat.getText().toString());
        limit++;
        mDatabase.child("persona").child(""+limit).setValue(persona);
        limpiar();
    }
    public void mostrar(View view)
    {
        Toast.makeText(getApplicationContext(),all,Toast.LENGTH_LONG).show();
    }
    public void modificar(View view)
    {
        Persona persona = new Persona(etxtnom.getText().toString(),
                etxtappat.getText().toString(),
                etxtapmat.getText().toString());
        mDatabase.child("persona").child(""+id.getText().toString()).setValue(persona);
        limpiar();
    }
    public void eliminar(View view)
    {
        mDatabase.child("persona").child(""+id.getText().toString()).removeValue();//.setValue(null);
        limpiar();
    }
    public void limpiar(View view)
    {

    }
    public void limpiar()
    {
        etxtnom.setText("");
        etxtappat.setText("");
        etxtapmat.setText("");
        id.setText("");
    }
}
