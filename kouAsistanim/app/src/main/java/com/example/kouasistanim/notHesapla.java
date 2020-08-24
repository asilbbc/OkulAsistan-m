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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class notHesapla extends AppCompatActivity {


    EditText vizeY,vizeN,finalY,finalN;
    TextView ogrencininMailiNotHesapla;
    TextView ogrencininBolumuNotHesapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_hesapla);
        vizeY=findViewById(R.id.vizeY);
        vizeN=findViewById(R.id.vizeN);
        finalY=findViewById(R.id.finalY);
        finalN=findViewById(R.id.finalN);
        ogrencininMailiNotHesapla = findViewById(R.id.ogrencininMailiNotHesapla);
        ogrencininBolumuNotHesapla = findViewById(R.id.ogrencininBolumuNotHesapla);
        ogrencininBolumuNotHesapla.setText(getIntent().getExtras().getString("Bolum"));

        ogrencininMailiNotHesapla.setText(getIntent().getExtras().getString("Mail"));
        final TextView durum = (TextView) findViewById(R.id.notD);
        Button notHesapla=(Button)findViewById(R.id.hesapla);
        notHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vizeN.getText().toString().trim().isEmpty()||vizeY.getText().toString().trim().isEmpty()||finalN.getText().toString().trim().isEmpty()||finalY.getText().toString().trim().isEmpty()){
                    Toast.makeText(notHesapla.this,"Herhangi bir alanı boş bırakamazsınız".toString(),Toast.LENGTH_LONG).show();
                    vizeY.setText(" ");
                    vizeN.setText(" ");
                    finalN.setText(" ");
                    finalY.setText(" ");
                }
                else {
                    float vizeYuzdesi = Float.parseFloat(vizeY.getText().toString());
                    float vizeNotu= Float.parseFloat(vizeN.getText().toString());
                    float finalYuzdesi = Float.parseFloat(finalY.getText().toString());
                    float finalNotu = Float.parseFloat(finalN.getText().toString());
                    if ((vizeYuzdesi + finalYuzdesi) != 100) {
                        Toast.makeText(notHesapla.this, "Vize ve Final Yuzdesi toplamı 100 olmak zorundadir.".toString(), Toast.LENGTH_LONG).show();
                        vizeY.setText(" ");
                        vizeN.setText(" ");
                        finalN.setText(" ");
                        finalY.setText(" ");
                    } else {

                        float sonuc = (vizeNotu * (vizeYuzdesi / 100)) + (finalNotu * (finalYuzdesi / 100));
                        System.out.println(sonuc);
                        //sonuc.setText(String.valueOf(sayi1float+sayi2float));
                        durum.setText(String.valueOf((vizeNotu * (vizeYuzdesi / 100)) + (finalNotu * (finalYuzdesi / 100))));
                    }
                }
            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void geriGitNotHesapla(View view){
        Intent intent =new Intent(notHesapla.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiNotHesapla.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuNotHesapla.getText().toString());

        startActivity(intent);
    }
}

