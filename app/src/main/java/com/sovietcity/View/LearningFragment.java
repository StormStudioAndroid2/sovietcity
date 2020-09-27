package com.sovietcity.View;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sovietcity.Adapters.FactoryCard;
import com.sovietcity.Adapters.LessonAdapter;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningFragment extends Fragment {
    private MainActivity mainActivity;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<FactoryCard> arrayList = getCards();
        View view =  inflater.inflate(R.layout.fragment_learning, container, false);
        RecyclerView learnView = (RecyclerView) view.findViewById(R.id.learn_recycler);
        learnView.setHasFixedSize(true);
        learnView.setLayoutManager(new LinearLayoutManager(mainActivity));
        LessonAdapter factoryCardAdapter = new LessonAdapter(mainActivity,arrayList);
        learnView.setAdapter(factoryCardAdapter);
        return view;
    }
    public ArrayList<FactoryCard> getCards() {
        String[] name = getActivity().getApplicationContext().getResources().getStringArray(R.array.lesson_names);
        String[] describe = getActivity().getApplicationContext().getResources().getStringArray(R.array.lesson_description);
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.lesson_icon);
        ArrayList<FactoryCard> cards = new ArrayList<>();
        for (int i = 0; i<name.length;i++) {
            FactoryCard factoryCard = new FactoryCard();
            factoryCard.setImage(image);
            factoryCard.setFirstText(name[i]);
            factoryCard.setSecondText(describe[i]);
            cards.add(factoryCard);
        }
        return cards;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
