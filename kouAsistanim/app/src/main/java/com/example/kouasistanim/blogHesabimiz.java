package com.example.kouasistanim;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class blogHesabimiz extends AppCompatActivity {
    WebView webView1;
    TextView ogrencininMailiBlogHesabimiz;
    TextView ogrencininBolumuBlogHesabimiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_hesabimiz);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        webView1 = (WebView)findViewById(R.id.webView1);
        ogrencininMailiBlogHesabimiz = findViewById(R.id.ogrencininMailiBlogHesabimiz);
        ogrencininBolumuBlogHesabimiz = findViewById(R.id.ogrencininBolumuBlogHesabimiz);
        ogrencininBolumuBlogHesabimiz.setText(getIntent().getExtras().getString("Bolum"));

        ogrencininMailiBlogHesabimiz.setText(getIntent().getExtras().getString("Mail"));
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://okulasistanim.blogspot.com/");
    }


    public void geriGitBlogHesabimiz(View view) {
        Intent intent =new Intent(blogHesabimiz.this,ayarlar.class);
        intent.putExtra("Mail",ogrencininMailiBlogHesabimiz.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuBlogHesabimiz.getText().toString());
        startActivity(intent);
    }
}
