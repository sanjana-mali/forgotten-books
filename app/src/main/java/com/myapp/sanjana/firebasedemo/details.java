package com.myapp.sanjana.firebasedemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class details extends AppCompatActivity {


    public static final int PICK_PHOTO_FOR_AVATAR= 1;
    static final String TAG="Details";
    EditText nametxt,phtxt,addrtxt;
    FirebaseFirestore db;
    HashMap<String,String> map;
    Button go;
    String mail,email;
    Intent in,intent;
    Bundle ibundle;
    ImageView im;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        im=findViewById(R.id.imageView);
        nametxt = findViewById(R.id.nmtxt);
        phtxt = findViewById(R.id.phtxt);
        addrtxt = findViewById(R.id.addtxt);
        go=findViewById(R.id.button);
        db = FirebaseFirestore.getInstance();
        map = new HashMap<>();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        email=user.getEmail();
        in=new Intent(details.this,home.class);
        storage=FirebaseStorage.getInstance();
        storageRef=storage.getReference();
        go.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                storageRef.child("users/"+email).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        ibundle=getIntent().getExtras();
                        if(ibundle!=null)
                        {
                            mail=ibundle.getString("email");
                        }
                        else
                        {
                            Toast.makeText(details.this, "Not fetched!", Toast.LENGTH_SHORT).show();
                        }
                        map.put("name", nametxt.getText().toString());
                        map.put("email",mail);
                        map.put("phone_no", phtxt.getText().toString());
                        map.put("address", addrtxt.getText().toString());
                        map.put("image",uri.toString());

                        db.collection("users")
                                .add(map)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                                {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference)
                                    {
                                        Toast.makeText(details.this,"Saved!", Toast.LENGTH_SHORT).show();
                                        startActivity(in);
                                    }
                                });
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

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        super.onActivityResult(requestCode, resultCode, data);
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference mountainsRef = storageRef.child("users/"+email);

        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK)
        {
            if (data == null)
            {
                return;
            }
            try
            {
                Uri uri=data.getData();
                im.setImageURI(uri);
                InputStream stream =this.getContentResolver().openInputStream(data.getData());
                UploadTask uploadTask = mountainsRef.putStream(stream);
                uploadTask.addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception exception)
                    {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {

                        Toast.makeText(details.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
