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
public class LoseFragment extends Fragment {
    private MainActivity mainActivity;

    public LoseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lose, container, false);
        Button exitButton = (Button) view.findViewById(R.id.lose_btn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.mainMenuIntent();

            }
        });
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
