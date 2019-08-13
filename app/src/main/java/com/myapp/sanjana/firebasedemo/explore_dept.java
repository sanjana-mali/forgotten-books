package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class explore_dept extends AppCompatActivity {

    Button comp,it,entc,mech,civil,electrical;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_dept);

        comp=findViewById(R.id.comp);
        it=findViewById(R.id.it);
        entc=findViewById(R.id.entc);
        mech=findViewById(R.id.mech);
        civil=findViewById(R.id.civil);
        electrical=findViewById(R.id.electrical);

        i=new Intent(explore_dept.this,explore_sem.class);

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","comp");
                startActivity(i);
            }
        });

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","IT");
                startActivity(i);
            }
        });

        entc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","entc");
                startActivity(i);
            }
        });

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","civil");
                startActivity(i);
            }
        });

        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","mech");
                startActivity(i);
            }
        });

        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("dept","electrical");
                startActivity(i);
            }
        });
    }
}
