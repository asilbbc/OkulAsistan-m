package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    EditText email,sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        sifre=findViewById(R.id.sifre);

        TextView tvHelloWorld = (TextView) findViewById(R.id.font2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts.Inria_Sans/LobsterTwo-Bold.ttf");
        tvHelloWorld.setTypeface(typeface);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void girisYap(View view){
        String Email= email.getText().toString();
        String password = sifre.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(Email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(getApplicationContext(),anaEkran.class);
                //intent.putExtra("sifre",sifre.getText().toString());
                intent.putExtra("Mail",email.getText().toString());
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void kayitOl(View view) {
        Intent intent = new Intent(getApplicationContext(),kayitOl.class);
        startActivity(intent);
    }
    public void sifremiUnuttum(View view){
        Intent intent = new Intent(getApplicationContext(),sifremiUnuttum.class);
        startActivity(intent);
    }
}
