package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sovietcity.Model.AuthorisationWorker;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorisationFragment extends Fragment {
    private AuthorisationWorker authorisationWorker;
    private MainMenuActivity mainMenuActivity;

    public AuthorisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_authorisation, container, false);
        final EditText nameText = (EditText) view.findViewById(R.id.auto_name);
        final EditText mailText = (EditText) view.findViewById(R.id.auto_mail);
        Button back = (Button)view.findViewById(R.id.auto_back);
        Button ok = (Button)view.findViewById(R.id.auto_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailText.getText().clearSpans();
                nameText.getText().clearSpans();

                if ((!nameText.getText().toString().isEmpty())&&(!mailText.getText().toString().isEmpty())) {
                    authorisationWorker.addUser(nameText.getText().toString(),mailText.getText().toString(),getActivity().getApplicationContext());


                    mainMenuActivity.deleteAllFragments();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuActivity.deleteAllFragments();

            }
        });
        return view;
    }

    public void setAuthorisationWorker(AuthorisationWorker authorisationWorker) {
        this.authorisationWorker = authorisationWorker;
    }

    public void setMainMenuActivity(MainMenuActivity mainMenuActivity) {
        this.mainMenuActivity = mainMenuActivity;
    }
}
