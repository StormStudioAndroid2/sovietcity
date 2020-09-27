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
public class GameMenuFragment extends Fragment {

    private MainActivity mainActivity;

    public GameMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_menu, container, false);
//        игровое меню. save пока не работает
        Button resumeButton = (Button) view.findViewById(R.id.resume_game);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        Button saveButton = (Button) view.findViewById(R.id.save_game);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.saveFragmentTransaction();

            }
        });
        Button quitButton = (Button) view.findViewById(R.id.quit_to_menu_game);
        quitButton.setOnClickListener(new View.OnClickListener() {
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
