package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class engg extends AppCompatActivity {

    Intent i,i1;
    Button button,other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engg);
        other=findViewById(R.id.otherbutton);
        i=new Intent(engg.this,dept.class);
        i1=new Intent(engg.this,other_sell.class);
        button=findViewById(R.id.enggbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i1);
            }
        });

    }
}
