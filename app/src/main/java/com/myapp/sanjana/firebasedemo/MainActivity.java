package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {

    public FirebaseFirestore db;
    Intent i;
    private static final String TAG = "SignUpActivity";
    Button signup,login;
    EditText emailedit,pswdedit;
    Intent in;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       signup=findViewById(R.id.signup);
       login=findViewById(R.id.login);
       emailedit=findViewById(R.id.emailedit);
       pswdedit=findViewById(R.id.pswdedit);
       auth=FirebaseAuth.getInstance();
        in=new Intent(MainActivity.this,home.class);
       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               i=new Intent(MainActivity.this,signup_activity.class);
               startActivity(i);
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               signIn(emailedit.getText().toString(),pswdedit.getText().toString());
           }
       });
    }


    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);




        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Welcome "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(in);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(in);
        }
    }
}
