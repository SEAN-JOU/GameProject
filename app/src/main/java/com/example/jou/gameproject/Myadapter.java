package com.example.jou.gameproject;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<Blog> blogs;
    private Context context;

    public Myadapter(List<Blog>blogs, Context context){

        this.blogs=blogs;
        this.context=context;

    }

    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);

        return  new ViewHolder(v);

    }
    @Override
    public void onBindViewHolder(Myadapter.ViewHolder holder, int position) {
        Blog blog =blogs.get(position);

        holder.adatitle.setText(blog.getTitle());
        holder.adadescription.setText(blog.getDescription());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
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
