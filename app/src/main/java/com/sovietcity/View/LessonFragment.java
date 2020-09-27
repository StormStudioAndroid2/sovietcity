package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LessonFragment extends Fragment {
    private MainActivity mainActivity;
    private int id;

    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        String[] names = getResources().getStringArray(R.array.lesson_names);
        String[] texts = getResources().getStringArray(R.array.lesson_texts);
        TextView nameView = (TextView) view.findViewById(R.id.lesson_name);
        nameView.setText(names[id]);
        TextView textView = (TextView) view.findViewById(R.id.lesson_text);
        textView.setText(texts[id]);
        Button backButton = (Button)view.findViewById(R.id.lesson_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.learningFragmentTransaction();
            }
        });
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setId(int id) {
        this.id = id;
    }
}
