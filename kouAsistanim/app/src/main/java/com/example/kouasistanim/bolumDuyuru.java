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

public class bolumDuyuru extends AppCompatActivity {
    WebView webView;
    TextView ogrencininMailiBolumDuyuru;
    TextView ogrencininBolumuBolumDuyuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolum_duyuru);
        webView = (WebView)findViewById(R.id.webViewBolumDuyuru);
        ogrencininMailiBolumDuyuru = findViewById(R.id.ogrencininMailiBolumDuyuru);
        ogrencininBolumuBolumDuyuru = findViewById(R.id.ogrencininBolumuBolumDuyuru);
        ogrencininMailiBolumDuyuru.setText(getIntent().getExtras().getString("Mail"));
        ogrencininBolumuBolumDuyuru.setText(getIntent().getExtras().getString("Bolum"));

        String ogrencininBolumu  = ogrencininBolumuBolumDuyuru.getText().toString();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if(ogrencininBolumu.equals("Bilgisayar Mühendisliği")) {
            webView.loadUrl("https://bilgisayarmuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");
        }

        if(ogrencininBolumu.equals("Çevre Mühendisliği")){
            webView.loadUrl("https://cevremuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");
        }

        if(ogrencininBolumu.equals("Deniz Ulaştırma İşletme Mühendisliği")){
            webView.loadUrl("https://denizulastirmamuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");


        }
        if(ogrencininBolumu.equals("Elektrik Elektronik Mühendisliği")){
            webView.loadUrl("https://elektrikelektronikmuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");


        }
        if(ogrencininBolumu.equals("Endüstri Mühendisliği")){
            webView.loadUrl("https://endustrimuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");


        }
        if(ogrencininBolumu.equals("İnşaat Mühendisliği")){
            webView.loadUrl("https://insaatmuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");
        }
        if(ogrencininBolumu.equals("Jeofizik Mühendisliği")){

            webView.loadUrl("https://jeofizikmuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }
        if(ogrencininBolumu.equals("Jeoloji Mühendisliği")){
            webView.loadUrl("https://jeolojimuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }
        if(ogrencininBolumu.equals("Kimya Mühendisliği")){
            webView.loadUrl("https://kimyamuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }
        if(ogrencininBolumu.equals("Maden Mühendisliği")){
            webView.loadUrl("https://madenmuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }
        if(ogrencininBolumu.equals("Makine Mühendisliği")){
            webView.loadUrl("https://makinemuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }
        if(ogrencininBolumu.equals("Metalurji Ve Malzeme Mühendisliği")){
            webView.loadUrl("https://metalurjimuhendislik.istanbulc.edu.tr/tr/duyurular/1/1");

        }

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void geriGitBolumDuyuru(View view){

        Intent intent =new Intent(bolumDuyuru.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiBolumDuyuru.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuBolumDuyuru.getText().toString());
        startActivity(intent);
    }
}
