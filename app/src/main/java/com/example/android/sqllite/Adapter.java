package com.example.android.sqllite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.DataViewHolder> {
    String [] data_set;

    public Adapter(String [] data){
        data_set = data;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_data_format, viewGroup, false);

        DataViewHolder dataViewHolder = new DataViewHolder(v);

        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, int i) {
        String data = data_set[i];

        dataViewHolder.tv_data.setText(data);
    }

    @Override
    public int getItemCount() {
        return data_set.length;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_data =itemView.findViewById(R.id.tv_show_data);
        }
    }
}
