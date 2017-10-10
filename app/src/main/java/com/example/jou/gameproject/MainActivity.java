package com.example.jou.gameproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    RecyclerView bloglist;
    RecyclerView.Adapter firebaseadapter;
    DatabaseReference root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bloglist = (RecyclerView)findViewById(R.id.recycle);
        bloglist.setHasFixedSize(true);
        bloglist.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        root = FirebaseDatabase.getInstance().getReference().child("child");
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseadapter = new Myadapter();
        bloglist.setAdapter(firebaseadapter);
    }

    String title,description;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i =dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            title = (String)((DataSnapshot)i.next()).getValue();
            description =(String)((DataSnapshot)i.next()).getValue();

        }

    }


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
    }


}

