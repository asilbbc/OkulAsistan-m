package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class sifremiUnuttum extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);
        firebaseAuth = FirebaseAuth.getInstance();
        Button yeniParolaGonder = (Button)findViewById(R.id.yeniParolaGonder);
        email=findViewById(R.id.uyeEmail);
        yeniParolaGonder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String mail = email.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplication(), "Lütfen email adresinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(mail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(sifremiUnuttum.this, "Yeni parola için gerekli bağlantı adresinize gönderildi!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(sifremiUnuttum.this, "Mail gönderme hatası!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void geriGitSifremiUnuttum(View view) {

        Intent intent =new Intent(sifremiUnuttum.this,MainActivity.class);
        startActivity(intent);
    }



}
