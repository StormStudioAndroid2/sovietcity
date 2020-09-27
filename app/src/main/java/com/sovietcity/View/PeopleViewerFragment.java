package com.sovietcity.View;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PeopleViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PeopleViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeopleViewerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainActivity mainActivity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeopleViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PeopleViewerFragment newInstance(String param1, String param2) {
        PeopleViewerFragment fragment = new PeopleViewerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PeopleViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_viewer, container, false);
        createTable(view);
        Button specialtyButton = (Button) view.findViewById(R.id.more_data);
        specialtyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.populationDataTransaction();
            }
        });
        return view;
    }

    public void createTable(View view) {
        //     создание таблицы о населении
        TextView txt = (TextView) view.findViewById(R.id.group_quantity2);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getChildrenGroup().getPopulation() + "");
        txt = (TextView) view.findViewById(R.id.group_emo2);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getChildrenGroup().getWealth() + "");
        txt = (TextView) view.findViewById(R.id.group_quantity3);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getAdultGroup().getPopulation() + "");
        txt = (TextView) view.findViewById(R.id.group_emo3);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getAdultGroup().getWealth() + "");
        txt = (TextView) view.findViewById(R.id.group_quantity4);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getPensionerGroup().getPopulation() + "");
        txt = (TextView) view.findViewById(R.id.group_emo4);
        txt.setText(this.mainActivity.getWorld().getPopulationManager().getPopulation().getPensionerGroup().getWealth() + "");
        txt = (TextView) view.findViewById(R.id.group_name1);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt = (TextView) view.findViewById(R.id.group_name2);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt = (TextView) view.findViewById(R.id.group_name3);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt = (TextView) view.findViewById(R.id.group_name4);
        txt.setMovementMethod(new ScrollingMovementMethod());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
