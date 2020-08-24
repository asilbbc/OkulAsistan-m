package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class dersIcerik extends AppCompatActivity {

    TextView icerikDersAdi;
    TextView icerikDersGunu;
    TextView icerikDersSaati;
    TextView ogrencininMailiDersIcerik;
    TextView ogrencininBolumuDersIcerik;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_icerik);
        icerikDersAdi = findViewById(R.id.icerikAdi);
        icerikDersGunu=findViewById(R.id.icerikGunu);
        firebaseFirestore = FirebaseFirestore.getInstance();
        icerikDersSaati= findViewById(R.id.icerikSaati);
        ogrencininMailiDersIcerik = findViewById(R.id.ogrencininMailiDersIcerik);
        ogrencininBolumuDersIcerik = findViewById(R.id.ogrencininBolumuDersIcerik);
        ogrencininMailiDersIcerik.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuDersIcerik.setText(getIntent().getExtras().getString("Bolum"));
        icerikDersAdi.setText(getIntent().getExtras().getString("dersAdi"));
        icerikDersGunu.setText(getIntent().getExtras().getString("dersGunu"));
        icerikDersSaati.setText(getIntent().getExtras().getString("dersSaati"));

    }

    public void yoklamaTakipEvet(View view){
        String dersAdi = icerikDersAdi.getText().toString();
        String ogrMaili = ogrencininMailiDersIcerik.getText().toString();
        String katilimDurumu = "Katıldım";

        HashMap<String, Object> yoklamaTakibi = new HashMap<>();

        yoklamaTakibi.put("dersinAdi",dersAdi);
        yoklamaTakibi.put("ogrMaili",ogrMaili);
        yoklamaTakibi.put("katilimDurumu",katilimDurumu);


        firebaseFirestore.collection("yoklamaTakibi").add(yoklamaTakibi).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(dersIcerik.this,"Ekleme Basarılı.".toString(),Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dersIcerik.this,"Ekleme Gerceklestirilemedi.".toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void yoklamaTakipHayir(View view){

        String dersAdi = icerikDersAdi.getText().toString();
        String ogrMaili = ogrencininMailiDersIcerik.getText().toString();
        String katilimDurumu = "Katılmadım";

        HashMap<String, Object> yoklamaTakibi = new HashMap<>();

        yoklamaTakibi.put("dersinAdi",dersAdi);
        yoklamaTakibi.put("ogrMaili",ogrMaili);
        yoklamaTakibi.put("katilimDurumu",katilimDurumu);


        firebaseFirestore.collection("yoklamaTakibi").add(yoklamaTakibi).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(dersIcerik.this,"Ekleme Basarılı.".toString(),Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dersIcerik.this,"Ekleme Gerceklestirilemedi.".toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void geriGitDersIcerik(View view){

        Intent intent =new Intent(dersIcerik.this,yoklamaTakibi.class);
        intent.putExtra("Mail",ogrencininMailiDersIcerik.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuDersIcerik.getText().toString());
        startActivity(intent);
    }


}
