package com.sovietcity.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovietcity.Model.ISaveLoader;
import com.sovietcity.R;
import com.sovietcity.View.Saver;

/**
 * Created by Серёга on 03.07.2016.
 */
public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.MyViewHolder> {
    private Context context;
    private ISaveLoader iSaveLoader;
    private Saver saver;

    @Override
    public SaveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_layout, parent, false);
        return new MyViewHolder(v);    }


    @Override
    public void onBindViewHolder(SaveAdapter.MyViewHolder holder, final int position) {
        holder.date.setText(this.saver.getSaves().get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSaveLoader.loadSave(saver.loadSaveFromFile(position));

            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                saver.deleteSave(context,position);
                notifyDataSetChanged();
                return true;
            }
        });

    }

    public SaveAdapter(Saver saver, ISaveLoader iSaveLoader, Context context) {
        this.iSaveLoader = iSaveLoader;
        this.saver = saver;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return saver.getSize();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            date = (TextView) itemView.findViewById(R.id.save_text_2);

        }
    }
}
