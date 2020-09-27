package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovietcity.Adapters.AnswerAdapter;
import com.sovietcity.Model.EventGame;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    private EventGame eventGame;
    private MainActivity mainActivity;
    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.answer_list);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(mainActivity));
        AnswerAdapter answerAdapter = new AnswerAdapter(mainActivity,eventGame.getEventAnswers());

        listView.setAdapter(answerAdapter);

        TextView textView = (TextView)view.findViewById(R.id.event_text);
        textView.setText(eventGame.getText());
        textView.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setEventGame(EventGame eventGame) {
        this.eventGame = eventGame;
    }
}
