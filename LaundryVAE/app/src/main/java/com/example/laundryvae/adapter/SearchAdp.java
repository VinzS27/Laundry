package com.example.laundryvae.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryvae.ContactActivity;
import com.example.laundryvae.HomeDryDetail;
import com.example.laundryvae.HomeIroningDetail;
import com.example.laundryvae.HomeWashDetail;
import com.example.laundryvae.R;
import com.example.laundryvae.model.Search;

import java.util.List;

public class SearchAdp extends RecyclerView.Adapter<SearchAdp.ViewHolder> {

    private Context context;
    private List<Search> list;

    public SearchAdp(Context context, List<Search> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.position.setText(list.get(position).getPosition());
        holder.rate.setText(list.get(position).getRate());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getName().equals("Laundry VAE 1"))
                    context.startActivity(new Intent(context, ContactActivity.class));
                if (list.get(position).getName().equals("Laundry VAE 2"))
                    context.startActivity(new Intent(context, ContactActivity.class));
                if (list.get(position).getName().equals("Laundry VAE 3"))
                    context.startActivity(new Intent(context, ContactActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView container;
        TextView title,position,rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.map_title);
            position=itemView.findViewById(R.id.txt_location);
            rate=itemView.findViewById(R.id.map_rate);
            container= itemView.findViewById(R.id.card_container2);
        }
    }
}
