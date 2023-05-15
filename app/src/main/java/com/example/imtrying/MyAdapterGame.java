package com.example.imtrying;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterGame extends RecyclerView.Adapter<MyViewHolderGame>{

    private Context context;
    private List<DataClassGame> dataList;

    public MyAdapterGame(Context context, List<DataClassGame> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderGame onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_game,parent,false);
        return new MyViewHolderGame(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderGame holder, int position) {
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        holder.recTime.setText(dataList.get(position).getDataTime());
        holder.recType.setText(dataList.get(position).getDataType());
        holder.recYear.setText(dataList.get(position).getDataYear());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivityGame.class);
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Time", dataList.get(holder.getAdapterPosition()).getDataTime());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Type", dataList.get(holder.getAdapterPosition()).getDataType());
                intent.putExtra("Year", dataList.get(holder.getAdapterPosition()).getDataYear());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataListGame(ArrayList<DataClassGame> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolderGame extends RecyclerView.ViewHolder{

    TextView recTitle, recDesc, recTime, recType, recYear;
    CardView recCard;

    public MyViewHolderGame(@NonNull View itemView) {
        super(itemView);

        recCard = itemView.findViewById(R.id.recCardGame);
        recTitle = itemView.findViewById(R.id.recTitleGame);
        recDesc = itemView.findViewById(R.id.recDescGame);
        recTime = itemView.findViewById(R.id.recTimeGame);
        recType = itemView.findViewById(R.id.recTypeGame);
        recYear = itemView.findViewById(R.id.recYearGame);
    }
}
