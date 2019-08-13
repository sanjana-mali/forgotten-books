package com.myapp.sanjana.firebasedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Second extends AppCompatActivity {

    TextView book_name,owner_name,price,contactno,emailid,address,desc;
    ImageView im;
    Uri image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle=getIntent().getExtras();

       im=findViewById(R.id.imgxml);
        book_name=findViewById(R.id.textName);
        owner_name=findViewById(R.id.textseller);
        price=findViewById(R.id.textPrice);
        contactno=findViewById(R.id.textcontact);
        emailid=findViewById(R.id.textemail);
        address=findViewById(R.id.textaddress);
        desc=findViewById(R.id.textdesc);

        if(bundle!=null)
        {
            book_name.setText(bundle.getString("title"));
            owner_name.setText(bundle.getString("name"));
            price.setText(bundle.getString("price"));
            contactno.setText(bundle.getString("contact"));
            emailid.setText(bundle.getString("email"));
            address.setText(bundle.getString("address"));
            desc.setText(bundle.getString("desc"));
            image=(Uri)bundle.get("image");
            Picasso.get()
                    .load(image)
                    .into(im);
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Nooooooooooooooooooooooooo", Toast.LENGTH_SHORT).show();
        }

    }
}
