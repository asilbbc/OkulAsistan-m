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

public class dersler extends AppCompatActivity {
    EditText ogrencininMailiDersler;
    TextView ogrencininBolumuDersler;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> dersAdiFromFb;
    ArrayList<String> dersGunuFromFb;
    ArrayList<String> dersSaatiFromFb;
    derslerrecyleadaptor derslerrecyleadaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);
        dersAdiFromFb = new ArrayList<>();
        dersGunuFromFb=new ArrayList<>();
        dersSaatiFromFb=new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        derslerrecyleadaptor = new derslerrecyleadaptor(dersAdiFromFb,dersGunuFromFb,dersSaatiFromFb);
        recyclerView.setAdapter(derslerrecyleadaptor);
        ogrencininMailiDersler=findViewById(R.id.ogrencininMailiDersler);
        ogrencininBolumuDersler = findViewById(R.id.ogrencininBolumuDersler);
        ogrencininMailiDersler.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuDersler.setText(getIntent().getExtras().getString("Bolum"));
        getDataFromFirestore();
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void getDataFromFirestore() {

        String mail = (String) ogrencininMailiDersler.getText().toString();
        System.out.println("MAİL :: : :: "+mail);

        CollectionReference collectionReference = firebaseFirestore.collection("dersler");
        collectionReference.whereEqualTo("ogrMail",mail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(dersler.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(queryDocumentSnapshots!=null) {
                    for(DocumentSnapshot snapshot :queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        String dersAdi = (String) data.get("dersAdi");
                        String dersGunu=(String)data.get("dersGunu");
                        String dersSaati=(String) data.get("dersSaati");
                        System.out.println(dersAdi);
                        dersAdiFromFb.add(dersAdi);
                        dersGunuFromFb.add(dersGunu);
                        dersSaatiFromFb.add(dersSaati);
                        derslerrecyleadaptor.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    public void geriGitDersler(View view) {
        Intent intent =new Intent(dersler.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiDersler.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuDersler.getText().toString());

        startActivity(intent);
    }

}
