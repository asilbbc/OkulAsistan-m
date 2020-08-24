package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class dersEkle extends AppCompatActivity {

    EditText ders;
    EditText dersakt;
    EditText dersS;
    EditText ogrencininMailiDersEkle;
    TextView ogrencininBolumuDersEkle;
    private CheckBox pazartesi;
    private CheckBox sali;
    private CheckBox carsamba;
    private CheckBox persembe;
    private CheckBox cuma;

    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_ekle);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ders = findViewById(R.id.dersAdi);
        dersakt=findViewById(R.id.dersAkts);
        pazartesi=findViewById(R.id.pazartesiCheckBox);
        sali=findViewById(R.id.saliCheckBox);
        carsamba=findViewById(R.id.carsambaCheckBox);
        persembe=findViewById(R.id.persembeCheckBox);
        cuma=findViewById(R.id.cumaCheckBox);
        dersS=findViewById(R.id.dersSaati);
        ogrencininBolumuDersEkle = findViewById(R.id.ogrencininBolumuDersEkle);
        ogrencininMailiDersEkle=findViewById(R.id.ogrencininMailiDersEkle);
        ogrencininMailiDersEkle.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuDersEkle.setText(getIntent().getExtras().getString("Bolum"));
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }
    public void dersKaydet(View view){

        String dersAdi = ders.getText().toString();
        String dersAkts = dersakt.getText().toString();
        String dersGunu="";
        String dersSaati = dersS.getText().toString();
       // String ogrMail=mail3.getText().toString();

        if(pazartesi.isChecked())
            dersGunu= " "+pazartesi.getText();
        if(sali.isChecked())
            dersGunu= " "+sali.getText();
        if(carsamba.isChecked())
            dersGunu= " "+carsamba.getText();
        if(persembe.isChecked())
            dersGunu= " "+persembe.getText();
        if(cuma.isChecked())
            dersGunu= " "+cuma.getText();

        System.out.println(dersAdi);

        HashMap<String, Object> dersler = new HashMap<>();
        //dersler.put("ogrMail",ogrMail);
        dersler.put("dersAdi",dersAdi);
        dersler.put("dersAkts",dersAkts);
        dersler.put("dersGunu",dersGunu);
        dersler.put("dersSaati",dersSaati);

        firebaseFirestore.collection("dersler").add(dersler).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(dersEkle.this,"Ekleme Basarılı.".toString(),Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(dersEkle.this,"Ekleme Gerceklestirilemedi.".toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void geriGitDersEkle(View view){
        Intent intent =new Intent(dersEkle.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiDersEkle.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuDersEkle.getText().toString());
        startActivity(intent);
    }
}
