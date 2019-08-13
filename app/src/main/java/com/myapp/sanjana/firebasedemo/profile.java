package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Uri uri;
    String email,phno,addr,name,u;
    CollectionReference userRef;
    FirebaseFirestore db;
    TextView phtxt,addrtxt,nametxt,emailtxt;
    Button logOut,ads;
    Intent in,in1;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        in=new Intent(profile.this,MainActivity.class);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        email=user.getEmail();
        userRef=db.collection("users");
        logOut=findViewById(R.id.logOut);
        phtxt=findViewById(R.id.cnttxt);
        addrtxt=findViewById(R.id.addtxt);
        nametxt=findViewById(R.id.nmtxt);
        emailtxt=findViewById(R.id.mailtxt);
        im=findViewById(R.id.im);
        ads=findViewById(R.id.ads);
        in1=new Intent(profile.this,ads.class);


        //in1=new Intent(profile.this,ads.class);

        userRef.whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {

                            for(QueryDocumentSnapshot documentSnapshot:task.getResult())
                            {
                                name=documentSnapshot.get("name").toString();
                                phno=documentSnapshot.get("phone_no").toString();
                                addr=documentSnapshot.get("address").toString();
                                u=documentSnapshot.get("image").toString();
                                uri=Uri.parse(u);
                            }
                            phtxt.setText(phno);
                            addrtxt.setText(addr);
                            nametxt.setText(name);
                            emailtxt.setText(email);
                            Picasso.get()
                                    .load(uri)
                                    .into(im);
                           // im.setImageURI(uri);

                        }
                    }
                });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Toast.makeText(profile.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(in);
            }
        });

        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(in1);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
