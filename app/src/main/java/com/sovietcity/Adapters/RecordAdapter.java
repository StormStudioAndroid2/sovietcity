package com.sovietcity.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovietcity.Model.Record;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * Created by Серёга on 05.07.2016.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private ArrayList<Record> records;

    @Override
    public RecordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecordAdapter.MyViewHolder holder, int position) {
        holder.recordName.setText(records.get(position).getName());
        holder.recordMoney.setText(records.get(position).getMoney() + "");
        holder.recordEmo.setText(records.get(position).getHappiness() + "");

    }

    @Override
    public int getItemCount() {
        return records.size();
    }
    public RecordAdapter(ArrayList<Record> records) {
        this.records = records;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView recordName;
        public TextView recordEmo;
        public TextView recordMoney;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            recordName = (TextView) itemView.findViewById(R.id.name_record);
            recordMoney = (TextView) itemView.findViewById(R.id.money_record);
            recordEmo = (TextView) itemView.findViewById(R.id.emo_record);

        }
    }
}
