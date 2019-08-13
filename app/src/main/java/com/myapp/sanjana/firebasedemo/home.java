package com.myapp.sanjana.firebasedemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

   // FirebaseAuth mAuth;
    Button logOut, explore, prof, sell;
    Intent in, i, i1, i2;
    private ActionBar toolbar;
    private String c_name;
    private String c_salary;
    private String uri;
    private String contact;
    private String  owner;
    private String desc;

    RecyclerView recyclerView;
    FirebaseFirestore db;
    List<DataModel> companylist;
    private String dept;
    private String sem;
    private  String email,address;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        in = new Intent(home.this, MainActivity.class);
        i = new Intent(home.this, explore_dept.class);
        i1 = new Intent(home.this, profile.class);
        i2 = new Intent(home.this, engg.class);
        logOut = findViewById(R.id.logOut);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        companylist = new ArrayList<>();
        final Context context = this;

        final CustomerAdapter customerAdapter = new CustomerAdapter(context,companylist);
        recyclerView.setAdapter(customerAdapter);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Forgotten Books");

        db =  FirebaseFirestore.getInstance();
        CollectionReference docRef = db.collection("other");

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
                                        email,desc,address
                                ));
                                recyclerView.setAdapter(customerAdapter);
                            }

                        }
                        else
                        {
                            Exception err=task.getException();
                            Toast.makeText(home.this,"Error"+err,Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    return true;
                case R.id.navigation_sell:
                    toolbar.setTitle("Home");
                    startActivity(i2);
                    return true;
                case R.id.navigation_categories:
                    toolbar.setTitle("Home");
                    startActivity(i);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Home");
                    startActivity(i1);
                    return true;

            }
            return false;
        }
    };
}