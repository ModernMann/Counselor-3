package com.example.imtrying.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imtrying.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterGame extends FirebaseRecyclerAdapter<Game, AdapterGame.MyViewHolder> {

    public AdapterGame(@NonNull FirebaseRecyclerOptions<Game> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Game model) {

        holder.gameName.setText(model.getName());
        holder.gameDescription.setText(model.getDescription());
        holder.gameType.setText(model.getType());
        holder.gameTime.setText(model.getTime());
        holder.gameYear.setText(model.getYear());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView gameType, gameYear, gameTime, gameName, gameDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gameType = (TextView)itemView.findViewById(R.id.game_type_txt);
            gameYear = (TextView)itemView.findViewById(R.id.game_year_txt);
            gameTime = (TextView)itemView.findViewById(R.id.game_time_txt);
            gameName = (TextView)itemView.findViewById(R.id.game_name_txt);
            gameDescription = (TextView)itemView.findViewById(R.id.game_description_txt);
        }
    }
}
