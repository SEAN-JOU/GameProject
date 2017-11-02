package com.example.jou.gameproject;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.squareup.picasso.Picasso;
        import java.util.List;


public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>  {

    List<Blog> blogs;
    Context context;
    DatabaseReference root;


    public Myadapter(List<Blog> blogs, Context context){

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
        Picasso.with(context)
                .load(blog.getImage())
                .into(holder.adaimage);

        holder.adaimage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                root = FirebaseDatabase.getInstance().getReference().child("blog");
                root.removeValue();

                return false;
            }});}
    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView adatitle,adadescription;
        public ImageView adaimage;
        public LinearLayout linearlayout;


        public ViewHolder(View itemView) {
            super(itemView);

            adadescription = itemView.findViewById(R.id.adadescription);
            adaimage = itemView.findViewById(R.id.adaimage);
            adatitle = itemView.findViewById(R.id.adatitle);
            linearlayout=itemView.findViewById(R.id.linearlayout);
        }

        public void setTitle(String title){adatitle.setText(title);}
        public void setAdadescription(String description){adadescription.setText(description);}
        public void setImage(String Image){adadescription.setText(Image);}
    }}
