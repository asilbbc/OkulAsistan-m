package com.example.kouasistanim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class sifreYenile extends AppCompatActivity {

    EditText ogrencininMailiSifreYenile,ogrencininSifresiSifreYenile,yeniSifre;
    TextView ogrencininBolumuSifreYenile;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_yenile);
        firebaseAuth = FirebaseAuth.getInstance();
        ogrencininMailiSifreYenile=findViewById(R.id.ogrencininMailiSifreYenile);
        ogrencininSifresiSifreYenile=findViewById(R.id.ogrencininSifresiSifreYenile);
        firebaseFirestore = FirebaseFirestore.getInstance();
        yeniSifre=findViewById(R.id.yeniSifre);
        ogrencininBolumuSifreYenile =findViewById(R.id.ogrencininBolumuSifreYenile);
        ogrencininBolumuSifreYenile.setText(getIntent().getExtras().getString("Bolum"));
        ogrencininMailiSifreYenile.setText(getIntent().getExtras().getString("Mail"));
        getDataFromFirestore();
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void sifreYenile(View view)
    {
        user= FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(ogrencininMailiSifreYenile.getText().toString(), ogrencininSifresiSifreYenile.getText().toString());
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(yeniSifre.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(sifreYenile.this,"Parola olusturuldu.",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(sifreYenile.this,"Parola olusturulmadı.",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        } else {

                            Toast.makeText(sifreYenile.this,"ERROR!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void geriGitSifreYenile(View view){
        Intent intent =new Intent(sifreYenile.this,ayarlar.class);
        intent.putExtra("Mail",ogrencininMailiSifreYenile.getText().toString());
        intent.putExtra("Bolum",ogrencininBolumuSifreYenile.getText().toString());
        startActivity(intent);
    }


    public void getDataFromFirestore() {

        String mail = (String) ogrencininMailiSifreYenile.getText().toString();
        System.out.println("MAİL :: : :: "+mail);

        CollectionReference collectionReference = firebaseFirestore.collection("dersler");
        collectionReference.whereEqualTo("ogrMail",mail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(sifreYenile.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(queryDocumentSnapshots!=null) {
                    for(DocumentSnapshot snapshot :queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String sifre = (String) data.get("sifresi");
                        ogrencininSifresiSifreYenile.setText(sifre);
                    }
                }

            }
        });
    }


}
