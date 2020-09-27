package com.sovietcity.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.R;
import com.sovietcity.View.MainActivity;

import java.util.ArrayList;

/**
 * Created by Серёга on 02.06.2016.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {
    private ArrayList<FactoryCard> factoryCards;
    private MainActivity mainActivity;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textFirst.setText(factoryCards.get(position).getFirstText());
        holder.textSecond.setText(factoryCards.get(position).getSecondText());
        holder.imageView.setImageBitmap(factoryCards.get(position).getImage());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.lessonFragmentTransaction(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return factoryCards.size();
    }

    public LessonAdapter(MainActivity mainActivity, ArrayList<FactoryCard> factoryCards) {
        this.factoryCards = factoryCards;
        this.mainActivity = mainActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView textFirst;
        public TextView textSecond;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textFirst = (TextView) itemView.findViewById(R.id.lesson_text_1);
            textSecond = (TextView) itemView.findViewById(R.id.lesson_text_2);
            imageView = (ImageView) itemView.findViewById(R.id.lesson_card);

        }
    }
}
