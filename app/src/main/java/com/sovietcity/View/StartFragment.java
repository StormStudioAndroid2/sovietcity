package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {
    private MainActivity mainActivity;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button startButton = (Button) view.findViewById(R.id.start_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        Button trainButton = (Button) view.findViewById(R.id.training);
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.lessonMissionFragmentTransaction();

            }
        });
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
