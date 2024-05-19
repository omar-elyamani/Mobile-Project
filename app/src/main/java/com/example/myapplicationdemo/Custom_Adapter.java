package com.example.myapplicationdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<String> offer_id, offer_title, offer_description;

    public Custom_Adapter(Context context, ArrayList<String> offer_id, ArrayList<String> offer_title, ArrayList<String> offer_description) {
        this.context = context;
        this.offer_id = offer_id;
        this.offer_title = offer_title;
        this.offer_description = offer_description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_offer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.offer_id_text.setText(offer_id.get(position));
        holder.offer_title_text.setText(offer_title.get(position));
        holder.offer_description_text.setText(offer_description.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update_Activity.class);
                intent.putExtra("title", offer_title.get(position));
                intent.putExtra("description", offer_description.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offer_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView offer_id_text, offer_title_text, offer_description_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            offer_id_text = itemView.findViewById(R.id.offer_id_txt);
            offer_title_text = itemView.findViewById(R.id.offer_title_txt);
            offer_description_text = itemView.findViewById(R.id.offer_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
