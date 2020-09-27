package com.sovietcity.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.R;

import java.util.ArrayList;

//    адаптер для FactoryCard
public class FactoryCardAdapter extends RecyclerView.Adapter<FactoryCardAdapter.MyViewHolder> {
    private ArrayList<FactoryCard> factoryCards;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.factory_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textFirst.setText(factoryCards.get(position).getFirstText());
        holder.textSecond.setText(factoryCards.get(position).getSecondText());
        holder.imageView.setImageBitmap(factoryCards.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return factoryCards.size();
    }

    public FactoryCardAdapter(ArrayList<FactoryCard> factoryCards) {
        this.factoryCards = factoryCards;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView textFirst;
        public TextView textSecond;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textFirst = (TextView) itemView.findViewById(R.id.card_text_1);
            textSecond = (TextView) itemView.findViewById(R.id.card_text_2);
            imageView = (ImageView) itemView.findViewById(R.id.image_card);

        }
    }
}
