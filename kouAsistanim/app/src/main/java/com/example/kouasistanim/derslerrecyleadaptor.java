package com.example.kouasistanim;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;


public class derslerrecyleadaptor extends RecyclerView.Adapter<derslerrecyleadaptor.PostHolder > {

    private ArrayList<String> dersAdiList;
    private ArrayList<String> dersGunuList;
    private ArrayList<String> dersSaatiList;

    public derslerrecyleadaptor(ArrayList<String> dersAdiList,ArrayList<String>dersGunuList,ArrayList<String>dersSaatiList) {
        this.dersAdiList = dersAdiList;
        this.dersGunuList=dersGunuList;
        this.dersSaatiList=dersSaatiList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.recyle_row,parent,false);
        return new PostHolder(view);
    }

    @Override
    public int getItemCount() {

        return dersAdiList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, final int position) {

        holder.dersAdi.setText(dersAdiList.get(position));
        holder.dersGunu.setText(dersGunuList.get(position));
        holder.dersSaati.setText(dersSaatiList.get(position));

        holder.relative.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                Context context = view.getContext();

                Intent intent = new Intent(context, dersIcerik.class);
                intent.putExtra("dersAdi",dersAdiList.get(position));
                intent.putExtra("dersGunu",dersGunuList.get(position));
                intent.putExtra("dersSaati",dersSaatiList.get(position));
                context.startActivity(intent);
            }
        });
    }

    class PostHolder extends RecyclerView.ViewHolder{
        TextView dersAdi;
        TextView dersGunu;
        TextView dersSaati;
        RelativeLayout relative;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            dersAdi=itemView.findViewById(R.id.recyle_row_dersAdiText);
            dersGunu=itemView.findViewById(R.id.recyle_row_dersGunuText);
            dersSaati=itemView.findViewById(R.id.recyle_row_dersSaatiText);
            relative=itemView.findViewById(R.id.relative);
        }
    }
}
