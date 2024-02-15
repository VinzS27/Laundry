package com.example.laundryvae.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryvae.HomeDryDetail;
import com.example.laundryvae.HomeIroningDetail;
import com.example.laundryvae.HomeWashDetail;
import com.example.laundryvae.R;
import com.example.laundryvae.model.Item;

import java.util.List;

public class HomeAdp extends RecyclerView.Adapter<HomeAdp.ViewHolder> {

    private Context context;
    private List<Item> list;

    public HomeAdp(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.washing_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdp.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImg());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getName().equals("Cleaning"))
                context.startActivity(new Intent(context, HomeWashDetail.class));
                if (list.get(position).getName().equals("Drying"))
                    context.startActivity(new Intent(context, HomeDryDetail.class));
                if (list.get(position).getName().equals("Ironing"))
                    context.startActivity(new Intent(context, HomeIroningDetail.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView container;
        private ImageView imageView;
        private TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.washingmachine_image);
            name=itemView.findViewById(R.id.washingmachine_image_name);
            price=itemView.findViewById(R.id.washingmachine_image_price);
            container= itemView.findViewById(R.id.card_container);
        }
    }
}
