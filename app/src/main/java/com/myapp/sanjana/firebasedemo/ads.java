package com.myapp.sanjana.firebasedemo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ads extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    String email;
    String id,dept,sem,title,id1;

    private String c_name;
    private String c_salary;
    private String uri;
    private String contact;
    private String  desc;
    private String owner,address;

    RecyclerView recyclerView;
    List<DataModel> companylist;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        /*db.collection("user_books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot doc:task.getResult())
                    {
                        id1=doc.getId();
                        Toast.makeText(ads.this,doc.getString("title"), Toast.LENGTH_SHORT).show();
                        dept=doc.getString("dept");
                        sem=doc.getString("sem");
                        db.collection("engg")
                                .document(dept)
                                .collection(sem)
                                .whereEqualTo("email",email)
                                .whereEqualTo("title",title)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            for(DocumentSnapshot doc:task.getResult())
                                            {
                                                id=doc.getId();
                                                Toast.makeText(ads.this, id, Toast.LENGTH_SHORT).show();
                                                db.collection("engg")
                                                        .document(dept)
                                                        .collection(sem)
                                                        .document(id)
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid)
                                                            {
                                                                Toast.makeText(ads.this, "Deleteddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }

                                    }
                                });
                    }
                }
            }
        });*/

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        email=user.getEmail();
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        companylist = new ArrayList<>();
        final Context context = this;

        final UserAdapter customerAdapter = new UserAdapter(context,companylist);
        recyclerView.setAdapter(customerAdapter);


        db.collection("user_books")
                .whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot document : task.getResult())
                            {
                                c_name = document.getString("title");
                                c_salary= document.getString("price");
                                uri=document.getString("image");
                                Uri u=Uri.parse(uri);
                                companylist.add(new DataModel(
                                        c_name,
                                        c_salary,
                                        u
                                ));
                                recyclerView.setAdapter(customerAdapter);
                            }

                        }
                        else
                        {
                            Exception err=task.getException();
                            Toast.makeText(ads.this,"Error"+err,Toast.LENGTH_LONG).show();
                        }
                    }
                });
        /*db.collection("user_books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot doc:task.getResult())
                    {
                        id1=doc.getId();
                        Toast.makeText(ads.this,doc.getString("title"), Toast.LENGTH_SHORT).show();
                        dept=doc.getString("dept");
                        sem=doc.getString("sem");
                        db.collection("engg")
                                .document(dept)
                                .collection(sem)
                                .whereEqualTo("email",email)
                                .whereEqualTo("title",title)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            for(DocumentSnapshot doc:task.getResult())
                                            {
                                                id=doc.getId();
                                                Toast.makeText(ads.this, id, Toast.LENGTH_SHORT).show();
                                                db.collection("engg")
                                                        .document(dept)
                                                        .collection(sem)
                                                        .document(id)
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid)
                                                            {
                                                                Toast.makeText(ads.this, "Deleteddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }

                                    }
                                });
                    }
                }
            }
        });*/
    }
}
