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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class yemekListesi extends AppCompatActivity {

    WebView webView1;
    TextView ogrencininMailiYemekListesi;
    TextView ogrencininBolumuYemekListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_listesi);
        webView1 = (WebView)findViewById(R.id.webViewYemekListesi);
        ogrencininMailiYemekListesi=findViewById(R.id.ogrencininMailiYemekListesi);
        ogrencininBolumuYemekListesi = findViewById(R.id.ogrencininBolumuYemekListesi);
        ogrencininMailiYemekListesi.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuYemekListesi.setText(getIntent().getExtras().getString("Bolum"));
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://sks.istanbul.edu.tr/tr/yemeklistesi");
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }
    public void geriGitYemekListesi(View view){
        Intent intent =new Intent(yemekListesi.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiYemekListesi.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuYemekListesi.getText().toString());

        startActivity(intent);
    }
}
