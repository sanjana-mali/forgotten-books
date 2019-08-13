package com.myapp.sanjana.firebasedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class explored extends AppCompatActivity {

    private String c_name;
    private String c_salary;
    private String uri;
    private String contact;
    private String  desc;
    private String owner;

    RecyclerView recyclerView;
    FirebaseFirestore db;
    List<DataModel> companylist;
    private String dept;
    private String sem;
    private  String email,address;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explored);

        Bundle extras = getIntent().getExtras();
        dept = extras.getString("dept");
        sem = extras.getString("sem");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        companylist = new ArrayList<>();
        final Context context = this;

        final CustomerAdapter customerAdapter = new CustomerAdapter(context,companylist);
        recyclerView.setAdapter(customerAdapter);


        db =  FirebaseFirestore.getInstance();
        CollectionReference docRef = db.collection("engg").document(dept).collection(sem);

        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot document : task.getResult())
                            {
                                c_name = document.getString("title");
                                c_salary= document.getString("price");
                                email = document.getString("email");
                                uri=document.getString("image");
                                Uri u=Uri.parse(uri);
                                contact=document.getString("contact_no");
                                desc=document.getString("description");
                                owner=document.getString("owner");
                                address=document.getString("address");
                                        companylist.add(new DataModel(
                                        c_name,
                                        c_salary,
                                        u,
                                        owner,
                                        contact,
                                        email,desc,
                                                address
                                ));
                                recyclerView.setAdapter(customerAdapter);
                            }

                        }
                        else
                            {
                            Exception err=task.getException();
                            Toast.makeText(explored.this,"Error"+err,Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }


}