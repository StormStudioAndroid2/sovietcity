package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sovietcity.Adapters.RecordAdapter;
import com.sovietcity.Model.AuthorisationWorker;
import com.sovietcity.Model.Record;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {
    private MainMenuActivity mainMenuActivity;
    private AuthorisationWorker authorisationWorker;

    public RecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_records, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Record> records = authorisationWorker.getRecords();
        if (records != null) {
            RecordAdapter recordAdapter = new RecordAdapter(records);
            recyclerView.setAdapter(recordAdapter);
        }
        Button button = (Button) view.findViewById(R.id.record_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuActivity.deleteAllFragments();
            }
        });
        return view;
    }

    public void setMainMenuActivity(MainMenuActivity mainMenuActivity) {
        this.mainMenuActivity = mainMenuActivity;
    }

    public void setAuthorisationWorker(AuthorisationWorker authorisationWorker) {
        this.authorisationWorker = authorisationWorker;
    }
}
