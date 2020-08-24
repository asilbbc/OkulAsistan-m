package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class yoklamaTakibi extends AppCompatActivity {


    TextView ogrencininMailiYoklamaTakibi;
    TextView ogrencininBolumuYoklamaTakibi;
    TextView dersAdiYT, dersGunuYT,dersSaatiYT;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoklama_takibi);
        ogrencininMailiYoklamaTakibi = findViewById(R.id.ogrencininMailiYoklamaTakibi);
        ogrencininBolumuYoklamaTakibi = findViewById(R.id.ogrencininBolumuYoklamaTakibi);
        dersAdiYT = findViewById(R.id.dersAdiYT);
        dersGunuYT = findViewById(R.id.dersGunuYT);
        dersSaatiYT  = findViewById(R.id.dersSaatiYT);
        ogrencininMailiYoklamaTakibi.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuYoklamaTakibi.setText(getIntent().getExtras().getString("Bolum"));
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFirestoreYT();
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void getDataFromFirestoreYT(){

        String mail = (String) ogrencininMailiYoklamaTakibi.getText().toString();
        System.out.println("MAİL :: : :: "+mail);

        CollectionReference collectionReference = firebaseFirestore.collection("dersler");
        collectionReference.whereEqualTo("ogrMail",mail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(yoklamaTakibi.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(queryDocumentSnapshots!=null) {
                    for(DocumentSnapshot snapshot :queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String dersinAdi = (String) data.get("dersAdi");
                        String dersinGunu = (String) data.get("dersGunu");
                        String dersinSaati = (String) data.get("dersSaati");

                        dersAdiYT.setText(dersinAdi);
                        dersGunuYT.setText(dersinGunu);
                        dersSaatiYT.setText(dersinSaati);

                    }
                }

            }
        });
    }

    public void derseGit(View view){

        Intent intent =new Intent(yoklamaTakibi.this,dersIcerik.class);
        intent.putExtra("dersAdi",dersAdiYT.getText().toString());
        intent.putExtra("dersGunu",dersGunuYT.getText().toString());
        intent.putExtra("dersSaati",dersSaatiYT.getText().toString());
        intent.putExtra("Mail",ogrencininMailiYoklamaTakibi.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuYoklamaTakibi.getText().toString());
        startActivity(intent);

    }

    public void geriGitYoklamaTakibi(View view){
        Intent intent =new Intent(yoklamaTakibi.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiYoklamaTakibi.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuYoklamaTakibi.getText().toString());
        startActivity(intent);
    }

}
