package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sovietcity.Model.LessonMissionLoader;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LessonMissionFragment extends Fragment {
    private LessonMissionLoader lessonMissionLoader;
    private MainActivity mainActivity;

    public LessonMissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson_mission, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_mission);
        Button nextButton = (Button) view.findViewById(R.id.mission_next);
        Button backButton = (Button) view.findViewById(R.id.mission_back);
        Button completeButton = (Button) view.findViewById(R.id.complete_btn);
        if (lessonMissionLoader.getMissions().size() == (lessonMissionLoader.getCurrentId() + 1)) {
            nextButton.setVisibility(View.GONE);
            completeButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
            textView.setText(lessonMissionLoader.getMissions().get(lessonMissionLoader.getCurrentId()));
        }
        if (lessonMissionLoader.getMissions().size() <= lessonMissionLoader.getCurrentId()) {
            nextButton.setVisibility(View.GONE);
            completeButton.setVisibility(View.GONE);
            backButton.setVisibility(View.VISIBLE);
            textView.setText("Обучение пройдено!");
        }
        if (lessonMissionLoader.getMissions().size() > (lessonMissionLoader.getCurrentId() + 1)) {
            nextButton.setVisibility(View.VISIBLE);
            completeButton.setVisibility(View.GONE);
            backButton.setVisibility(View.VISIBLE);
            textView.setText(lessonMissionLoader.getMissions().get(lessonMissionLoader.getCurrentId()));
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonMissionLoader.increaseId();
                mainActivity.lessonMissionFragmentTransaction();
            }
        });
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonMissionLoader.increaseId();
                mainActivity.surfaceTransaction();
            }
        });
        textView.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    public void setLessonMissionLoader(LessonMissionLoader lessonMissionLoader) {
        this.lessonMissionLoader = lessonMissionLoader;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
