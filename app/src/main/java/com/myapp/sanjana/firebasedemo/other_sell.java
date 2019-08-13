package com.myapp.sanjana.firebasedemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

public class other_sell extends AppCompatActivity {

    public static final int PICK_PHOTO_FOR_AVATAR = 1;
    EditText desctxt, pricetxt, titletxt;
    Button post;
    String dept, sem, name, mail, cont, desc, addr, title;
    Bundle ib;
    ImageView im;
    FirebaseFirestore db;
    String price;
    CollectionReference enggRef, userRef;
    FirebaseUser user;
    FirebaseAuth auth;
    HashMap<String, String> hm;
    InputStream stream;
    UploadTask uploadTask;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();
    Uri uri1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_sell);
        im = findViewById(R.id.imageView3);

        desctxt = findViewById(R.id.desctxt);
        pricetxt = findViewById(R.id.pricetxt);
        titletxt = findViewById(R.id.titletxt);
        post = findViewById(R.id.post);
        hm = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        enggRef = db.collection("other");
        userRef = db.collection("users");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mail = user.getEmail();

        userRef.whereEqualTo("email", mail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                name = documentSnapshot.get("name").toString();
                                cont = documentSnapshot.get("phone_no").toString();
                                addr = documentSnapshot.get("address").toString();
                                title = titletxt.getText().toString();
                            }
                        }
                    }
                });


        post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                desc = desctxt.getText().toString();
                price = pricetxt.getText().toString();
                title = titletxt.getText().toString();

                storageRef.child("other/"+mail+":"+title).getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                hm.put("title", title);
                                hm.put("owner", name);
                                hm.put("description", desc);
                                hm.put("email", mail);
                                hm.put("contact_no", cont);
                                hm.put("address", addr);
                                hm.put("price", price+"₹");
                                hm.put("image",uri.toString());
                                enggRef.add(hm).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(other_sell.this, "Added!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                storageRef.child("other/"+mail+":"+title).getDownloadUrl()
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(other_sell.this, "Please Select image!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        title=titletxt.getText().toString();
        StorageReference mountainsRef = storageRef.child("other/"+mail+":"+title);


        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null)
            {
                return;
            }
            try
            {

                Uri uri=data.getData();
                im.setImageURI(uri);
                InputStream stream = this.getContentResolver().openInputStream(data.getData());

                UploadTask uploadTask = mountainsRef.putStream(stream);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {

                        Toast.makeText(other_sell.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}