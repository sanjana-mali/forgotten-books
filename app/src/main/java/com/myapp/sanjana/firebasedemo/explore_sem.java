package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class explore_sem extends AppCompatActivity {


    Bundle ib;
    Button sem1,sem3,sem4,sem5,sem6,sem7,sem2,sem8;
    String dept;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_sem);
        ib=getIntent().getExtras();
        if(ib!=null)
            dept=ib.getString("dept");


        sem1=findViewById(R.id.sem1);
        sem2=findViewById(R.id.sem2);
        sem3=findViewById(R.id.sem3);
        sem4=findViewById(R.id.sem4);
        sem5=findViewById(R.id.sem5);
        sem6=findViewById(R.id.sem6);
        sem7=findViewById(R.id.sem7);
        sem8=findViewById(R.id.sem8);


        i=new Intent(explore_sem.this,explored.class);

        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem1");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });


        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem2");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem3");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem4");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem5");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem6");
                i.putExtra("dept",dept);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem7");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("sem","sem8");
                i.putExtra("dept",dept);
                startActivity(i);
            }
        });


    }
}
