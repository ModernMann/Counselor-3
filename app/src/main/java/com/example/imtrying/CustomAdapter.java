package com.example.imtrying;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private ArrayList game_name, game_description, game_type, game_time, game_year;
    CustomAdapter(Context context,
                  ArrayList game_name,
                  ArrayList game_description,
                  ArrayList game_type,
                  ArrayList game_time,
                  ArrayList game_year){
        this.context = context;
        this.game_name = game_name;
        this.game_description = game_description;
        this.game_type = game_type;
        this.game_time = game_time;
        this.game_year = game_year;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.game_name_txt.setText(String.valueOf(game_name.get(position)));
        holder.game_description_txt.setText(String.valueOf(game_description.get(position)));
        holder.game_time_txt.setText(String.valueOf(game_time.get(position)));
        holder.game_type_txt.setText(String.valueOf(game_type.get(position)));
        holder.game_year_txt.setText(String.valueOf(game_year.get(position)));
    }

    @Override
    public int getItemCount() {
        return game_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  game_name_txt, game_description_txt, game_time_txt, game_type_txt, game_year_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            game_name_txt = itemView.findViewById(R.id.game_name_txt);
            game_time_txt = itemView.findViewById(R.id.game_time_txt);
            game_description_txt = itemView.findViewById(R.id.game_description_txt);
            game_type_txt = itemView.findViewById(R.id.game_type_txt);
            game_year_txt = itemView.findViewById(R.id.game_year_txt);
        }
    }
}
