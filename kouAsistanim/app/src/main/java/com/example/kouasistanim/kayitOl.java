package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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

public class kayitOl extends AppCompatActivity {


    EditText mail,parola;
    private FirebaseAuth firebaseAuth;
    TextView bolum;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        firebaseFirestore = FirebaseFirestore.getInstance();
        TextView tvHelloWorld = (TextView) findViewById(R.id.fontHos);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts.Inria_Sans/RussoOne-Regular.ttf");
        tvHelloWorld.setTypeface(typeface);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        mail = findViewById(R.id.kayitEmail);
        parola = findViewById(R.id.kayitSifre);
        bolum = findViewById(R.id.secilenBolum);
        TextView tv1=(TextView)findViewById(R.id.boluum);
        registerForContextMenu(tv1);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.bolum,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.bilgMuh)
        {
            bolum.setText("Bilgisar Mühendisliği");
        }

        if(item.getItemId()==R.id.cevreMuh)
        {
            bolum.setText("Çevre Mühendisliği");
        }

        if(item.getItemId()==R.id.denizMuh)
        {
            bolum.setText("Deniz Ulaştırma İşletme Mühendisliği");
        }

        if(item.getItemId()==R.id.elektrikMuh)
        {
            bolum.setText("Elektrik Elektronik Mühendisliği");
        }

        if(item.getItemId()==R.id.endMuh)
        {
            bolum.setText("Endüstri Mühendisliği");
        }

        if(item.getItemId()==R.id.insaatMuh)
        {
            bolum.setText("İnşaat Mühendisliği");
        }

        if(item.getItemId()==R.id.jeofizikMuh)
        {
            bolum.setText("Jeofizik Mühendisliği");
        }

        if(item.getItemId()==R.id.jeolojiMuh)
        {
            bolum.setText("Jeoloji Mühendisliği");
        }

        if(item.getItemId()==R.id.kimyaMuh)
        {
            bolum.setText("Kimya Mühendisliği");
        }

        if(item.getItemId()==R.id.madenMuh)
        {
            bolum.setText("Maden Mühendisliği");
        }

        if(item.getItemId()==R.id.makineMuh)
        {
            bolum.setText("Makine Mühendisliği");
        }

        if(item.getItemId()==R.id.malzemeMuh)
        {
            bolum.setText("Metalurji Ve Malzeme Mühendisliği");
        }

        return super.onContextItemSelected(item);
    }

    public void sistemeKayit(View view){
        String Email= mail.getText().toString();
        String password = parola.getText().toString();

        if(Email.matches("")){
            Toast.makeText(kayitOl.this,"Mail Alanı Boş Bırakılamaz...".toString(),Toast.LENGTH_LONG).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(kayitOl.this,"Kullanici Olusturuldu.".toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(kayitOl.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });



        String ogrBolumu = bolum.getText().toString();

        HashMap<String, Object> dersler = new HashMap<>();

        dersler.put("ogrMail",Email);
        dersler.put("bolumun",ogrBolumu);
        dersler.put("sifresi",password);

        firebaseFirestore.collection("dersler").add(dersler).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(kayitOl.this,"Ekleme Basarılı.".toString(),Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(kayitOl.this,"Ekleme Gerceklestirilemedi.".toString(),Toast.LENGTH_LONG).show();
            }
        });


    }

    public void geriGitKayitOl(View view){
        Intent intent =new Intent(kayitOl.this,MainActivity.class);
        startActivity(intent);
    }


}
