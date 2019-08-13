package com.myapp.sanjana.firebasedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    Context mctx;
    List<DataModel> data = new ArrayList<>();
    public CustomerAdapter(Context mCtx, List<DataModel> data) {
        this.mctx = mCtx;
        this.data =data;
    }
    @NonNull
    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.card_items, null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.MyViewHolder myViewHolder, final int position) {
        final DataModel dataModel = data.get(position);
        myViewHolder.title.setText(dataModel.getName());
        myViewHolder.price.setText(dataModel.getVersion());
        Picasso.get()
                .load(dataModel.getImage())
                .into(myViewHolder.imageView);

        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),"Fetching "+dataModel.getOwner_name() , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mctx,Second.class);

                intent.putExtra("name",dataModel.getOwner_name());
                intent.putExtra("email",dataModel.getEmail());
                intent.putExtra("title",dataModel.getName());
                intent.putExtra("price",dataModel.getVersion());
                intent.putExtra("desc",dataModel.getDesc());
                intent.putExtra("contact",dataModel.getContact());
                intent.putExtra("address",dataModel.getAddress());
                intent.putExtra("image",dataModel.getImage());

                v.getContext().startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textViewName);
            price = itemView.findViewById(R.id.textViewVersion);
            imageView = itemView.findViewById(R.id.imageView23);
        }
    }
}