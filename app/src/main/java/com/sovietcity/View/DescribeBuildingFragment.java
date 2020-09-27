package com.sovietcity.View;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.Model.BuildModel;
import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescribeBuildingFragment extends Fragment {
    private BuildModel buildModel;
    private MainActivity mainActivity;
    private DrawerManager drawerManager;

    public DescribeBuildingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_describe_building, container, false);
        TextView textView = (TextView) view.findViewById(R.id.build_describe_history);
        textView.setText(buildModel.getDescribe());
        ImageView imageView = (ImageView) view.findViewById(R.id.build_icon_describe);
        imageView.setImageBitmap(drawerManager.setSize(BitmapFactory.decodeResource(getResources(), buildModel.getIcon())));
        TextView nameText = (TextView) view.findViewById(R.id.build_describe_name);
        nameText.setText(buildModel.getName());
        Button button = (Button)view.findViewById(R.id.describe_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.buildTransaction();
            }
        });
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    public void setDrawerManager(DrawerManager drawerManager) {
        this.drawerManager = drawerManager;
    }
}
