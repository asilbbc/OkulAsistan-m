package com.example.kouasistanim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class anaEkran extends AppCompatActivity {

    TextView ogrencininMailiAnaEkran;
    private FirebaseFirestore firebaseFirestore;
    TextView ogrencininBolumuAnaEkran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        TextView tvHelloWorld = (TextView) findViewById(R.id.font);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts.Inria_Sans/RussoOne-Regular.ttf");
        tvHelloWorld.setTypeface(typeface);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ogrencininMailiAnaEkran=findViewById(R.id.ogrencininMailiAnaEkran);
        ogrencininBolumuAnaEkran=findViewById(R.id.ogrencininBolumuAnaEkran);
        ogrencininMailiAnaEkran.setText(getIntent().getExtras().getString("Mail"));
        ActionBar actionBar=getSupportActionBar();
        getDataFromFirestore();
        actionBar.hide();
    }

    public void ayarlar (View view){
        Intent intent = new Intent(getApplicationContext(),ayarlar.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }
    public void dersleriEkle(View view){
        Intent intent = new Intent(getApplicationContext(),dersEkle.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }
    public void dersleriGoruntule(View view) {
        Intent intent = new Intent(getApplicationContext(),dersler.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }
    public void okulDuyurularınaGit(View view){
        Intent intent = new Intent(getApplicationContext(),okulDuyurusu.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }
    public void bolumDuyurularınaGit(View view){
        Intent intent = new Intent(getApplicationContext(),bolumDuyuru.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }

    public void yemekListesineGit(View view){
        Intent intent= new Intent(getApplicationContext(),yemekListesi.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }
    public void yoklamaTakibineGit(View view){
        Intent intent= new Intent(getApplicationContext(),yoklamaTakibi.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }

    public void notHesapla (View view) {
        Intent intent = new Intent(getApplicationContext(),notHesapla.class);
        intent.putExtra("Bolum",ogrencininBolumuAnaEkran.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAnaEkran.getText().toString());
        startActivity(intent);
    }



    public void getDataFromFirestore() {

        String mail = (String) ogrencininMailiAnaEkran.getText().toString();
        System.out.println("MAİL :: : :: "+mail);

        CollectionReference collectionReference = firebaseFirestore.collection("dersler");
        collectionReference.whereEqualTo("ogrMail",mail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(anaEkran.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(queryDocumentSnapshots!=null) {
                    for(DocumentSnapshot snapshot :queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String ogrBolumu = (String) data.get("bolumun");
                        ogrencininBolumuAnaEkran.setText(ogrBolumu);
                    }
                }

            }
        });
    }



}

