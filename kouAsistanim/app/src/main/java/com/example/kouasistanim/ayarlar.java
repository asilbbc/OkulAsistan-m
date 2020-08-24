package com.example.kouasistanim;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ayarlar extends AppCompatActivity {
    TextView ogrencininMailiAyarlar;
    TextView ogrencininBolumuAyarlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        TextView tvHelloWorld = (TextView) findViewById(R.id.hakkimizda);
        ogrencininMailiAyarlar = findViewById(R.id.ogrencininMailiAyarlar);
        ogrencininMailiAyarlar.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuAyarlar = findViewById(R.id.ogrencininBolumuAyarlar);
        ogrencininBolumuAyarlar.setText(getIntent().getExtras().getString("Bolum"));

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts.Inria_Sans/InriaSans-Bold.ttf");
        tvHelloWorld.setTypeface(typeface);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }
    public void sifremiYenile(View view) {

        Intent intent =new Intent(ayarlar.this,sifreYenile.class);
        intent.putExtra("Bolum",ogrencininMailiAyarlar.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAyarlar.getText().toString());
        startActivity(intent);
    }

    public void cikisimiYap(View view){
        Intent intent =new Intent(ayarlar.this,cikisYap.class);
        startActivity(intent);
    }
    public void blogHesaba(View view){
        Intent intent =new Intent(ayarlar.this,blogHesabimiz.class);
        intent.putExtra("Bolum",ogrencininMailiAyarlar.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAyarlar.getText().toString());
        startActivity(intent);
    }
    public void geriGitAyarlar(View view){
        Intent intent =new Intent(ayarlar.this,anaEkran.class);
        intent.putExtra("Bolum",ogrencininMailiAyarlar.getText().toString());
        intent.putExtra("Mail",ogrencininMailiAyarlar.getText().toString());
        startActivity(intent);
    }
}
