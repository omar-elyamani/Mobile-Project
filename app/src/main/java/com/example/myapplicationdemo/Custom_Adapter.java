package com.example.myapplicationdemo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Integer> offer_id;
    private final ArrayList<String> offer_title, offer_description;

    public Custom_Adapter(Context context, ArrayList<Integer> offer_id, ArrayList<String> offer_title, ArrayList<String> offer_description) {
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
    public void onBindViewHolder(@NonNull Custom_Adapter.MyViewHolder holder, int position) {
        holder.offer_id_text.setText(String.valueOf(offer_id.get(position)));
        holder.offer_title_text.setText(String.valueOf(offer_title.get(position)));
        holder.offer_description_text.setText(String.valueOf(offer_description.get(position)));
    }

    @Override
    public int getItemCount() {
        return offer_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView offer_id_text, offer_title_text, offer_description_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            offer_id_text = itemView.findViewById(R.id.offer_id_txt);
            offer_title_text = itemView.findViewById(R.id.offer_title_txt);
            offer_description_text = itemView.findViewById(R.id.offer_description_txt);
        }
    }

}