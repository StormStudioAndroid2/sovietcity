package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sovietcity.Adapters.SpecialtyAdapter;
import com.sovietcity.Model.Specialty;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialtyFragment extends Fragment {

    private MainActivity mainActivity;
    private ArrayList<Specialty> specialties;
    public SpecialtyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specialty, container, false);
        RecyclerView specialtyView = (RecyclerView)view.findViewById(R.id.specialty_view);
        specialtyView.setHasFixedSize(true);
        //    инфа о специальностях в списке
        specialtyView.setLayoutManager(new LinearLayoutManager(mainActivity));
        SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(specialties);
        specialtyView.setAdapter(specialtyAdapter);
        Button backButton = (Button)view.findViewById(R.id.specialty_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        return view;
    }

    public void setSpecialties(ArrayList<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
