package com.example.jou.gameproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main2Activity extends AppCompatActivity {

    Button submit;
    EditText title_edit,description_edit;
    ImageButton imageselect;
    static final int gallery_request =1;
    Uri imageuri = null;
    ProgressDialog progressdialog;
    StorageReference filepath;

    DatabaseReference database;

    StorageReference storage;//宣告storage的權限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        storage = FirebaseStorage.getInstance().getReference();//
        database = FirebaseDatabase.getInstance().getReference().child("blog");
        progressdialog =new ProgressDialog(this);
        imageselect =(ImageButton)findViewById(R.id.imageselect);
        title_edit =(EditText)findViewById(R.id.title_edit);
        description_edit =(EditText) findViewById(R.id.description_edit);


        imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,gallery_request);

            }});
        submit =(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startposting();

            }});
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == gallery_request && resultCode == RESULT_OK){

            imageuri = data.getData();
            imageselect.setImageURI(imageuri);

        }
    }

    private void startposting(){

        progressdialog.setMessage("請稍等");
        progressdialog.show();

        final String title_val =title_edit.getText().toString();
        final String description_val=description_edit.getText().toString();

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(description_val)){


                filepath = storage.child("blog_images").child(imageuri.getLastPathSegment());

                filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        @SuppressWarnings("VisibleForTests") Uri downloaduri = taskSnapshot.getDownloadUrl();

                        DatabaseReference newpost =database.push();

                        newpost.child("title").setValue(title_val);
                        newpost.child("description").setValue(description_val);
                        newpost.child("image").setValue(downloaduri.toString());

                        progressdialog.dismiss();

                        Intent it = new Intent();
                        it.setClass(Main2Activity.this,MainActivity.class);
                        startActivity(it);

                    }});


        }
    }
}
