package com.sovietcity.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovietcity.Model.EventAnswer;
import com.sovietcity.R;
import com.sovietcity.View.MainActivity;

import java.util.ArrayList;

/**
 * Created by Серёга on 12.06.2016.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {
        private ArrayList<EventAnswer> eventAnswers;
        private MainActivity mainActivity;
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textFirst.setText(eventAnswers.get(position).getText());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventAnswers.get(position).function();
                    mainActivity.surfaceTransaction();
                }
            });
        }

        @Override
        public int getItemCount() {
            return eventAnswers.size();
        }

        public AnswerAdapter(MainActivity mainActivity,ArrayList<EventAnswer> eventAnswers) {
            this.eventAnswers = eventAnswers;
            this.mainActivity = mainActivity;
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            public View view;
            public TextView textFirst;


            public MyViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                textFirst = (TextView) itemView.findViewById(R.id.answer_text);
            }
        }
    }


