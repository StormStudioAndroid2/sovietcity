package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sovietcity.Adapters.SaveAdapter;
import com.sovietcity.Model.ISaveLoader;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment {
    private ISaveLoader iSaveLoader;
    private Saver saver;
    private String name;
    private boolean canSave;
    public SaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.save_view);
        recyclerView.setHasFixedSize(true);
        //    инфа о специальностях в списке
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SaveAdapter saveAdapter = new SaveAdapter(saver,iSaveLoader,getActivity().getApplicationContext());
        recyclerView.setAdapter(saveAdapter);
        Button button = (Button)view.findViewById(R.id.create_save);
        if (!canSave) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saver.writeSaveInFile(getActivity().getApplicationContext(),iSaveLoader.writeSave(),name);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        Button button1 = (Button)view.findViewById(R.id.save_back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSaveLoader.closeSaveMenu();
            }
        });
        return view;
    }

    public void setiSaveLoader(ISaveLoader iSaveLoader) {
        this.iSaveLoader = iSaveLoader;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanSave(boolean canSave) {
        this.canSave = canSave;
    }
}
