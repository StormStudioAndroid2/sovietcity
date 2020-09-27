package com.sovietcity.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.Model.Specialty;
import com.sovietcity.R;

import java.util.ArrayList;

//    адаптер о специальностях
public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.MyViewHolder> {
    private ArrayList<Specialty> specialties;

    @Override
    public SpecialtyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialty_item, parent, false);
        return new MyViewHolder(v);    }

    @Override
    public void onBindViewHolder(SpecialtyAdapter.MyViewHolder holder, int position) {
        holder.name.setText(specialties.get(position).getName());
        holder.imageView.setImageBitmap(specialties.get(position).getImage());
        holder.worlpaces.setText("Нашли работу/Всего людей/всего раб.мест в данной специальности : " + specialties.get(position).getBusyWorkplaces() + "/" + specialties.get(position).getAllWorkplaces()+"/"+specialties.get(position).getiContainerWorkplaces());
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView name;
        public TextView worlpaces;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.name_specialty);
            worlpaces = (TextView) itemView.findViewById(R.id.specialty_workplaces);
            imageView = (ImageView) itemView.findViewById(R.id.specialty_icon);

        }

    }

    public SpecialtyAdapter(ArrayList<Specialty> specialties) {
        this.specialties = specialties;
    }
}
