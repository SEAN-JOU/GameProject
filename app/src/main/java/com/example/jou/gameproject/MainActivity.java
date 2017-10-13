package com.example.jou.gameproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    RecyclerView bloglist;
    RecyclerView.Adapter firebaseadapter;

    List<Blog> blogs;
    String d,t,c;
    TextView txv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blogs = new ArrayList<>();
        bloglist = (RecyclerView)findViewById(R.id.recycle);
        bloglist.setHasFixedSize(true);
        bloglist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        bloglist.setAdapter(firebaseadapter);
        txv=(TextView)findViewById(R.id.txv);


        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("blog");
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }});}
    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i =dataSnapshot.getChildren().iterator();
        while (i.hasNext())  {
            t = (String)((DataSnapshot)i.next()).getValue();
            d =(String)((DataSnapshot)i.next()).getValue();
            c =(String)((DataSnapshot)i.next()).getValue();
            Blog blog =new Blog(t,c,"");
            blogs.add(blog);
            firebaseadapter =new Myadapter(blogs,this);
            bloglist.setAdapter(firebaseadapter);
        }}


    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.addpost){
            Intent it = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(it);
        }
        return  super.onOptionsItemSelected(item);
    }}

