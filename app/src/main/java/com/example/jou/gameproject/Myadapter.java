package com.example.jou.gameproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);

        return  new ViewHolder(v);

    }
    @Override
    public void onBindViewHolder(Myadapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView adatitle,adadescription;
        public ImageView adaimage;


        public ViewHolder(View itemView) {
            super(itemView);
            adadescription = itemView.findViewById(R.id.adadescription);
            adaimage = itemView.findViewById(R.id.adaimage);
            adatitle = itemView.findViewById(R.id.adatitle);
        }
        public void setTitle(String title){
            adatitle.setText(title);
        }
        public void setAdadescription(String description){
            adadescription.setText(description);
        }
    }
}
