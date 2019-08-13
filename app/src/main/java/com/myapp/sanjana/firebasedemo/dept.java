package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dept extends AppCompatActivity {

    Button comp,it,entc,civil,mech;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        comp=findViewById(R.id.comp);
        it=findViewById(R.id.it);
        entc=findViewById(R.id.entc);
        civil=findViewById(R.id.civil);
        mech=findViewById(R.id.mech);

        i=new Intent(dept.this,sem.class);

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
    }
}
