package com.example.jou.gameproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION_CAMERA = 101;
    RecyclerView bloglist;
    RecyclerView.Adapter firebaseadapter;
    List<Blog> blogs;
    String tx,im,dc;
    final int REQUEST_EXTERNAL_STORAGE = 321;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blogs = new ArrayList<>();
        bloglist = (RecyclerView)findViewById(R.id.recycle);
        bloglist.setHasFixedSize(true);
        bloglist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        bloglist.setAdapter(firebaseadapter);
        setPermission();
        setCamera();


    }

    private void setCamera() {

        int camerapermission = ActivityCompat.checkSelfPermission(MainActivity.this, CAMERA);

        if(camerapermission != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{CAMERA},
                    REQUEST_PERMISSION_CAMERA);

        }
    }

    protected void onResume(){
        super.onResume();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("blog");
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
                append_chat_conversation(dataSnapshot);

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});
    }


        public void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i =dataSnapshot.getChildren().iterator()/*.next().getChildren().iterator()*/;
        while (i.hasNext())  {
            try{
            dc = (String)((DataSnapshot)i.next()).getValue();
            im =(String)((DataSnapshot)i.next()).getValue();
            tx =(String)((DataSnapshot)i.next()).getValue();
            Blog blog =new Blog(tx,im,dc);
            blogs.add(blog);
            firebaseadapter =new Myadapter(blogs,this);
            bloglist.setAdapter(firebaseadapter);
            firebaseadapter.notifyDataSetChanged();}
            catch (Exception e){
                Log.d("123","123");
            }}}

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

    public void setPermission(){

        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE);

       if (permission != PackageManager.PERMISSION_GRANTED) {
        //未取得權限，向使用者要求允許權
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                REQUEST_EXTERNAL_STORAGE);
       }}

}
        //已有權限，可進行檔案存取




