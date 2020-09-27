package com.sovietcity.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sovietcity.Model.BuildManager;
import com.sovietcity.Model.BuildModel;
import com.sovietcity.Model.CashManager;
import com.sovietcity.R;
import com.sovietcity.View.ConstructorFragment;
import com.sovietcity.View.MainActivity;
import com.sovietcity.View.RoadConstructorFragment;

import java.util.ArrayList;


//    адаптер для зданий
public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BuildModel> buildModels;
    private MainActivity mainActivity;
    private BuildManager buildManager;
    private CashManager cashManager;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_build_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BuildAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(buildModels.get(position).getName());
        holder.describe.setText(buildModels.get(position).getFunctionComponent().toString()+". "+buildModels.get(position).getPrice());
        holder.imageView.setImageBitmap(BitmapFactory.decodeResource(holder.view.getResources()
                , buildModels.get(position).getIcon()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                BuildModel buildModel = buildModels.get(position);
                if (buildModel.isCustoming()) {
                    if (buildModel.isRoad()) {
                        mainActivity.deleteAllFragments();
                        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        RoadConstructorFragment roadConstructorFragment = new RoadConstructorFragment();
                        roadConstructorFragment.setMainActivity(mainActivity);
                        roadConstructorFragment.setBuildModel(buildModel);
                        fragmentTransaction.add(R.id.content_frame, roadConstructorFragment, "ROAD_FRAGMENT");
                        fragmentTransaction.addToBackStack("tolist");
                        fragmentTransaction.commit();
                    } else {
                        mainActivity.deleteAllFragments();
                        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        ConstructorFragment constructorFragment = new ConstructorFragment();
                        constructorFragment.setBuildModel(buildModel);
                        fragmentTransaction.add(R.id.content_frame, constructorFragment, "CONSTRUCT_FRAGMENT");
                        fragmentTransaction.addToBackStack("tolist");
                        fragmentTransaction.commit();
                    }
                } else {
                    buildManager.startBuild(buildModel, cashManager);
                    boolean b = buildModel.getBuildComponent().payForBuilding(cashManager);
                    if (b) {
                        mainActivity.surfaceTransaction();
                    } else
                        Toast.makeText(mainActivity.getApplicationContext(), "Не хватает денег!", Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mainActivity.buildDescribeTransaction(buildModels.get(position));
                return true;
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return buildModels.size();
    }


    public BuildAdapter(Context context, MainActivity mainActivity, ArrayList<BuildModel> buildModels) {
        this.context = context;
        this.buildModels = buildModels;
        this.buildManager = mainActivity.getWorld().getBuildManager();
        this.cashManager = mainActivity.getWorld().getCashManager();
        this.mainActivity = mainActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView name;
        public TextView describe;
        public ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.name_build);
            describe = (TextView) itemView.findViewById(R.id.build_describe);
            imageView = (ImageView) itemView.findViewById(R.id.build_icon);

        }
    }
}
