package com.sovietcity.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.R;
import com.sovietcity.Model.Resource;
import com.sovietcity.View.StockResourceShooserFragment;

import java.util.ArrayList;

//    адаптер, отображающий инфу о ресурсах
public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.MyViewHolder> {
    private StockResourceShooserFragment stockResourceShooserFragment;
    private Context context;
    private ArrayList<Resource> resources;

    @Override
    public ResourceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ResourceAdapter.MyViewHolder holder, final int position) {
        holder.name.setText("Ресурс: "+resources.get(position).getName());
        holder.price.setText("Цена: "+Integer.toString(resources.get(position).getPrice()));
        holder.imageView.setImageBitmap(resources.get(position).getImage());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockResourceShooserFragment.chooseResource(resources.get(position));
            }
        });
    }

    public ResourceAdapter(ArrayList<Resource> resources, StockResourceShooserFragment stockResourceShooserFragment1) {
        this.stockResourceShooserFragment = stockResourceShooserFragment1;
        this.resources = resources;
        this.context = stockResourceShooserFragment1.getActivity().getApplicationContext();
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView name;
        public TextView price;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.name_resource);
            price = (TextView) itemView.findViewById(R.id.resource_price);
            imageView = (ImageView) itemView.findViewById(R.id.resource_icon);

        }
    }
}
