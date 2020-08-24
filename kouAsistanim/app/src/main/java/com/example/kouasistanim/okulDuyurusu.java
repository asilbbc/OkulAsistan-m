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
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class okulDuyurusu extends AppCompatActivity {

    WebView webView;
    TextView ogrencininMailiOkulDuyurusu;
    TextView ogrencininBolumuOkulDuyurusu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okul_duyurusu);
        webView = (WebView)findViewById(R.id.webView);
        ogrencininMailiOkulDuyurusu=findViewById(R.id.ogrencininMailiOkulDuyurusu);
        ogrencininBolumuOkulDuyurusu=findViewById(R.id.ogrencininBolumuOkulDuyurusu);
        ogrencininBolumuOkulDuyurusu.setText(getIntent().getExtras().getString("Bolum"));
        ogrencininMailiOkulDuyurusu.setText(getIntent().getExtras().getString("Mail"));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.istanbul.edu.tr/tr/duyurular/1/1");
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void geriGitOkulDuyuru(View view){

        Intent intent =new Intent(okulDuyurusu.this,anaEkran.class);
        intent.putExtra("Mail",ogrencininMailiOkulDuyurusu.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuOkulDuyurusu.getText().toString());
        startActivity(intent);
    }

}
